import { RegistrationValidationPolicy } from './registration-validation.policy';

describe('RegistrationValidationPolicy', () => {
  const policy = new RegistrationValidationPolicy();

  it('accepts a valid registration', () => {
    const result = policy.validate({
      email: 'john@example.com',
      username: 'john_doe',
    });
    expect(result.errors).toEqual([]);
  });

  it('rejects an invalid email', () => {
    const result = policy.validate({
      email: 'not-an-email',
      username: 'john_doe',
    });
    expect(result.errors).toHaveLength(1);
    expect(result.errors[0]).toContain('email');
  });

  it('rejects a username that is too short', () => {
    const result = policy.validate({
      email: 'john@example.com',
      username: 'jd',
    });
    expect(result.errors).toHaveLength(1);
    expect(result.errors[0]).toContain('Username');
  });

  it('rejects a username that is too long', () => {
    const result = policy.validate({
      email: 'john@example.com',
      username: 'a'.repeat(21),
    });
    expect(result.errors).toHaveLength(1);
  });

  it('collects multiple errors at once', () => {
    const result = policy.validate({ email: 'bad', username: 'x' });
    expect(result.errors).toHaveLength(2);
  });
});