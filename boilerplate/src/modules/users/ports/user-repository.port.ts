import type { User } from '../domain/entities/user.entity';
import type { Email } from '../domain/value-objects/email.value-object';

export const USER_REPOSITORY = Symbol('USER_REPOSITORY');

export interface UserRepository {
  save(user: User): Promise<void>;
  findById(id: string): Promise<User | null>;
  findByEmail(email: Email): Promise<User | null>;
}