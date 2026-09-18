import type { ConfigService } from '@nestjs/config';
import type { TypeOrmModuleOptions } from '@nestjs/typeorm';

export function buildTypeOrmOptions(config: ConfigService): TypeOrmModuleOptions {
  const type = config.get<string>('DB_TYPE', 'better-sqlite3');
  const synchronize = config.get<string>('APP_ENV', 'development') !== 'production';

  if (type === 'postgres') {
    return {
      type: 'postgres',
      host: config.get<string>('DB_HOST', 'localhost'),
      port: Number(config.get<string>('DB_PORT', '5432')),
      username: config.get<string>('DB_USER', 'root'),
      password: config.get<string>('DB_PASSWORD', 'secret'),
      database: config.get<string>('DB_NAME', 'boilerplate'),
      autoLoadEntities: true,
      synchronize,
    };
  }

  return {
    type: 'better-sqlite3',
    database: config.get<string>('DB_DATABASE', 'boilerplate.sqlite'),
    autoLoadEntities: true,
    synchronize,
  };
}