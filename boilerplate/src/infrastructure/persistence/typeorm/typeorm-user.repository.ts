import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import type { Repository } from 'typeorm';

import { User, UserStatus } from '../../../modules/users/domain/entities/user.entity';
import { Email } from '../../../modules/users/domain/value-objects/email.value-object';
import type { UserRepository } from '../../../modules/users/ports/user-repository.port';
import { TypeOrmUser } from './typeorm-user.entity';

@Injectable()
export class TypeOrmUserRepository implements UserRepository {
  constructor(
    @InjectRepository(TypeOrmUser)
    private readonly repo: Repository<TypeOrmUser>,
  ) {}

  async save(user: User): Promise<void> {
    await this.repo.save({
      id: user.id,
      email: user.email.value,
      username: user.username,
      status: user.status,
    });
  }

  async findById(id: string): Promise<User | null> {
    const entity = await this.repo.findOne({ where: { id } });
    return entity ? this.toDomain(entity) : null;
  }

  async findByEmail(email: Email): Promise<User | null> {
    const entity = await this.repo.findOne({ where: { email: email.value } });
    return entity ? this.toDomain(entity) : null;
  }

  private toDomain(entity: TypeOrmUser): User {
    return new User(
      entity.id,
      new Email(entity.email),
      entity.username,
      entity.status as UserStatus,
    );
  }
}