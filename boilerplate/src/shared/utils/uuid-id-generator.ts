import { randomUUID } from 'node:crypto';

import type { IdGenerator } from '../contracts/id-generator.contract';

export class UuidIdGenerator implements IdGenerator {
  generate(): string {
    return randomUUID();
  }
}