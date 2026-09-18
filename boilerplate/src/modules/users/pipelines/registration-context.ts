import type { User } from '../domain/entities/user.entity';
import type { UserRegistrationData } from '../domain/value-objects/registration-data';

export class RegistrationContext {
  user: User | null = null;

  constructor(readonly input: UserRegistrationData) {}
}