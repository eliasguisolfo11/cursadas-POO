import { NotFoundError } from '../../../shared/errors/app-error';
import { User, UserStatus } from '../domain/entities/user.entity';
import { Email } from '../domain/value-objects/email.value-object';
import type { UserRepository } from '../ports/user-repository.port';
import { GetUserByIdUseCase } from './get-user-by-id.use-case';

describe('GetUserByIdUseCase', () => {
  const existing = new User('user-1', new Email('ana@example.com'), 'ana', UserStatus.ACTIVE);

  it('returns the user when found', async () => {
    const repository: UserRepository = {
      save: jest.fn(),
      findById: jest.fn().mockResolvedValue(existing),
      findByEmail: jest.fn(),
    };

    const useCase = new GetUserByIdUseCase(repository);
    await expect(useCase.execute('user-1')).resolves.toBe(existing);
  });

  it('throws NotFoundError when the user is missing', async () => {
    const repository: UserRepository = {
      save: jest.fn(),
      findById: jest.fn().mockResolvedValue(null),
      findByEmail: jest.fn(),
    };

    const useCase = new GetUserByIdUseCase(repository);
    await expect(useCase.execute('missing')).rejects.toBeInstanceOf(NotFoundError);
  });
});