import { Inject, Injectable } from '@nestjs/common';

import type { User } from '../domain/entities/user.entity';
import type { UserRegistrationData } from '../domain/value-objects/registration-data';
import {
  USER_REGISTRATION_CHAIN,
} from '../pipelines/registration-chain';
import { RegistrationContext } from '../pipelines/registration-context';
import type { RegistrationHandler } from '../pipelines/registration-handler.abstract';

@Injectable()
export class RegisterUserUseCase {
  constructor(
    @Inject(USER_REGISTRATION_CHAIN)
    private readonly chain: RegistrationHandler,
  ) {}

  async execute(input: UserRegistrationData): Promise<User> {
    const context = new RegistrationContext(input);
    await this.chain.handle(context);

    if (context.user === null) {
      throw new Error('Registration pipeline finished without creating a user');
    }

    return context.user;
  }
}