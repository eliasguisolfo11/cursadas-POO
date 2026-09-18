import { ValidationError } from '../../../shared/errors/app-error';
import type { UserValidationPolicy } from '../policies/user-validation.policy';
import { BaseRegistrationHandler } from './registration-handler.abstract';
import type { RegistrationContext } from './registration-context';

export class ValidationHandler extends BaseRegistrationHandler {
  constructor(private readonly policy: UserValidationPolicy) {
    super();
  }

  protected async process(context: RegistrationContext): Promise<void> {
    const result = this.policy.validate(context.input);
    if (result.errors.length > 0) {
      throw new ValidationError(result.errors.join('; '));
    }
  }
}