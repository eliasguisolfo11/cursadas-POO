import { Body, Controller, Get, Param, Post } from '@nestjs/common';

import type { RegisterUserDto } from './dto/register-user.dto';
import type { User } from './domain/entities/user.entity';
import { GetUserByIdUseCase } from './use-cases/get-user-by-id.use-case';
import { RegisterUserUseCase } from './use-cases/register-user.use-case';

@Controller('users')
export class UsersController {
  constructor(
    private readonly registerUser: RegisterUserUseCase,
    private readonly getUserById: GetUserByIdUseCase,
  ) {}

  @Post()
  async register(@Body() dto: RegisterUserDto) {
    const user = await this.registerUser.execute({
      email: dto.email,
      username: dto.username,
    });
    return UsersController.toResponse(user);
  }

  @Get(':id')
  async getById(@Param('id') id: string) {
    const user = await this.getUserById.execute(id);
    return UsersController.toResponse(user);
  }

  private static toResponse(user: User) {
    return {
      id: user.id,
      email: user.email.value,
      username: user.username,
      status: user.status,
    };
  }
}