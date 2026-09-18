import { Module } from '@nestjs/common';

import { ID_GENERATOR } from '../../shared/contracts/id-generator.contract';
import { UuidIdGenerator } from '../../shared/utils/uuid-id-generator';
import { RegistrationValidationPolicy } from './policies/registration-validation.policy';
import { USER_VALIDATION_POLICY } from './policies/user-validation.policy';
import { USER_REPOSITORY } from './ports/user-repository.port';
import {
  buildRegistrationChain,
  USER_REGISTRATION_CHAIN,
} from './pipelines/registration-chain';
import { GetUserByIdUseCase } from './use-cases/get-user-by-id.use-case';
import { RegisterUserUseCase } from './use-cases/register-user.use-case';
import { UsersController } from './users.controller';

@Module({
  controllers: [UsersController],
  providers: [
    RegisterUserUseCase,
    GetUserByIdUseCase,
    { provide: ID_GENERATOR, useClass: UuidIdGenerator },
    { provide: USER_VALIDATION_POLICY, useClass: RegistrationValidationPolicy },
    {
      provide: USER_REGISTRATION_CHAIN,
      inject: [USER_VALIDATION_POLICY, USER_REPOSITORY, ID_GENERATOR],
      useFactory: (policy, repository, idGenerator) =>
        buildRegistrationChain({ policy, repository, idGenerator }),
    },
  ],
})
export class UsersModule {}