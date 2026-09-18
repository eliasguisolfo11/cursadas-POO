import { Inject, Injectable } from '@nestjs/common';

import { NotFoundError } from '../../../shared/errors/app-error';
import type { User } from '../domain/entities/user.entity';
import { USER_REPOSITORY, type UserRepository } from '../ports/user-repository.port';

@Injectable()
export class GetUserByIdUseCase {
  constructor(
    @Inject(USER_REPOSITORY)
    private readonly repository: UserRepository,
  ) {}

  async execute(id: string): Promise<User> {
    const user = await this.repository.findById(id);
    if (user === null) {
      throw new NotFoundError(`User "${id}" not found`);
    }
    return user;
  }
}