import type { RegistrationContext } from './registration-context';

export interface RegistrationHandler {
  handle(context: RegistrationContext): Promise<void>;
  setNext(next: RegistrationHandler): RegistrationHandler;
}

export abstract class BaseRegistrationHandler {
  private next: RegistrationHandler | null = null;

  setNext(next: RegistrationHandler): RegistrationHandler {
    this.next = next;
    return next;
  }

  async handle(context: RegistrationContext): Promise<void> {
    await this.process(context);
    if (this.next) {
      await this.next.handle(context);
    }
  }

  protected abstract process(context: RegistrationContext): Promise<void>;
}