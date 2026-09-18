import { Global, Module } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';

import { USER_REPOSITORY } from '../../../modules/users/ports/user-repository.port';
import { buildTypeOrmOptions } from '../config/database.config';
import { TypeOrmUser } from './typeorm/typeorm-user.entity';
import { TypeOrmUserRepository } from './typeorm/typeorm-user.repository';

@Global()
@Module({
  imports: [
    TypeOrmModule.forRootAsync({
      inject: [ConfigService],
      useFactory: (config: ConfigService) => buildTypeOrmOptions(config),
    }),
    TypeOrmModule.forFeature([TypeOrmUser]),
  ],
  providers: [{ provide: USER_REPOSITORY, useClass: TypeOrmUserRepository }],
  exports: [USER_REPOSITORY],
})
export class DatabaseModule {}