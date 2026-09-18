import { ConflictError, ValidationError } from '../../../shared/errors/app-error';
import { User, UserStatus } from '../domain/entities/user.entity';
import { Email } from '../domain/value-objects/email.value-object';
import type { UserRegistrationData } from '../domain/value-objects/registration-data';
import type { UserRepository } from '../ports/user-repository.port';
import { RegistrationValidationPolicy } from '../policies/registration-validation.policy';
import { buildRegistrationChain } from './registration-chain';
import { RegistrationContext } from './registration-context';

describe('Registration chain', () => {
  const validInput: UserRegistrationData = { email: 'ana@example.com', username: 'ana' };

  function createHarness() {
    const repository: UserRepository = {
      save: jest.fn(),
      findById: jest.fn(),
      findByEmail: jest.fn().mockResolvedValue(null),
    };
    const idGenerator = { generate: (): string => 'user-1' };
    const chain = buildRegistrationChain({
      policy: new RegistrationValidationPolicy(),
      repository,
      idGenerator,
    });
    return { chain, repository };
  }

  it('creates the user when input is valid and unique', async () => {
    const { chain, repository } = createHarness();
    const context = new RegistrationContext(validInput);

    await chain.handle(context);

    expect(context.user).toBeInstanceOf(User);
    expect(context.user?.email.value).toBe('ana@example.com');
    expect(repository.save).toHaveBeenCalledTimes(1);
  });

  it('cuts the chain on a validation error (persist never runs)', async () => {
    const { chain, repository } = createHarness();
    const context = new RegistrationContext({ email: 'bad', username: 'x' });

    await expect(chain.handle(context)).rejects.toBeInstanceOf(ValidationError);
    expect(repository.save).not.toHaveBeenCalled();
  });

  it('cuts the chain when the email is already taken (persist never runs)', async () => {
    const { chain, repository } = createHarness();
    const existing = new User('old', new Email(validInput.email), null, UserStatus.ACTIVE);
    (repository.findByEmail as jest.Mock).mockResolvedValue(existing);
    const context = new RegistrationContext(validInput);

    await expect(chain.handle(context)).rejects.toBeInstanceOf(ConflictError);
    expect(repository.save).not.toHaveBeenCalled();
  });
});