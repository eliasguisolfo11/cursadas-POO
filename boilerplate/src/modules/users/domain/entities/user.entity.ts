import type { Email } from '../value-objects/email.value-object';

export enum UserStatus {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE',
}

export class User {
  constructor(
    public readonly id: string,
    public readonly email: Email,
    public readonly username: string | null,
    public readonly status: UserStatus,
  ) {}

  isActive(): boolean {
    return this.status === UserStatus.ACTIVE;
  }
}