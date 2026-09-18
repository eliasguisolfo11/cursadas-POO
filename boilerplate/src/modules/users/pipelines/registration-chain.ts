import type { IdGenerator } from '../../../shared/contracts/id-generator.contract';
import type { UserRepository } from '../ports/user-repository.port';
import type { UserValidationPolicy } from '../policies/user-validation.policy';
import { DuplicateCheckHandler } from './duplicate-check.handler';
import { PersistHandler } from './persist.handler';
import type { RegistrationHandler } from './registration-handler.abstract';
import { ValidationHandler } from './validation.handler';

export const USER_REGISTRATION_CHAIN = Symbol('USER_REGISTRATION_CHAIN');

export interface RegistrationChainDependencies {
  policy: UserValidationPolicy;
  repository: UserRepository;
  idGenerator: IdGenerator;
}

export function buildRegistrationChain({
  policy,
  repository,
  idGenerator,
}: RegistrationChainDependencies): RegistrationHandler {
  return new ValidationHandler(policy)
    .setNext(new DuplicateCheckHandler(repository))
    .setNext(new PersistHandler(idGenerator, repository));
}