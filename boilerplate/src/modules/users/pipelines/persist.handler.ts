import type { IdGenerator } from '../../../shared/contracts/id-generator.contract';
import { User, UserStatus } from '../domain/entities/user.entity';
import { Email } from '../domain/value-objects/email.value-object';
import type { UserRepository } from '../ports/user-repository.port';
import { BaseRegistrationHandler } from './registration-handler.abstract';
import type { RegistrationContext } from './registration-context';

export class PersistHandler extends BaseRegistrationHandler {
  constructor(
    private readonly idGenerator: IdGenerator,
    private readonly repository: UserRepository,
  ) {
    super();
  }

  protected async process(context: RegistrationContext): Promise<void> {
    const user = new User(
      this.idGenerator.generate(),
      new Email(context.input.email),
      context.input.username,
      UserStatus.ACTIVE,
    );
    await this.repository.save(user);
    context.user = user;
  }
}