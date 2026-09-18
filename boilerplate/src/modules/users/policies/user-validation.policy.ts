import type { UserRegistrationData } from '../domain/value-objects/registration-data';

export const USER_VALIDATION_POLICY = Symbol('USER_VALIDATION_POLICY');

export interface ValidationResult {
  errors: string[];
}

export interface UserValidationPolicy {
  validate(data: UserRegistrationData): ValidationResult;
}