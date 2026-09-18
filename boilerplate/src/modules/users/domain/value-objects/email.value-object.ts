import { ValidationError } from '../../../shared/errors/app-error';

export class Email {
  private static readonly PATTERN = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  constructor(public readonly value: string) {
    if (!Email.PATTERN.test(value)) {
      throw new ValidationError(`Invalid email: "${value}"`);
    }
  }
}