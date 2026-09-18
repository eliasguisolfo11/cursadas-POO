import { ConflictError } from '../../../shared/errors/app-error';
import { Email } from '../domain/value-objects/email.value-object';
import type { UserRepository } from '../ports/user-repository.port';
import { BaseRegistrationHandler } from './registration-handler.abstract';
import type { RegistrationContext } from './registration-context';

export class DuplicateCheckHandler extends BaseRegistrationHandler {
  constructor(private readonly repository: UserRepository) {
    super();
  }

  protected async process(context: RegistrationContext): Promise<void> {
    const existing = await this.repository.findByEmail(new Email(context.input.email));
    if (existing) {
      throw new ConflictError(`Email already registered: "${context.input.email}"`);
    }
  }
}