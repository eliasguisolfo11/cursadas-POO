import { Injectable } from '@nestjs/common';

import type { UserRegistrationData } from '../domain/value-objects/registration-data';
import { Email } from '../domain/value-objects/email.value-object';
import {
  type UserValidationPolicy,
  type ValidationResult,
} from './user-validation.policy';

@Injectable()
export class RegistrationValidationPolicy implements UserValidationPolicy {
  validate(data: UserRegistrationData): ValidationResult {
    const errors: string[] = [];

    const username = data.username?.trim();
    if (!username || username.length < 3 || username.length > 20) {
      errors.push('Username must be between 3 and 20 characters');
    }

    try {
      new Email(data.email);
    } catch {
      errors.push(`Invalid email: "${data.email}"`);
    }

    return { errors };
  }
}