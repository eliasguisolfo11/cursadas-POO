import { INestApplication, ValidationPipe } from '@nestjs/common';
import { Test } from '@nestjs/testing';
import request from 'supertest';

import { AppModule } from '../src/app.module';

describe('Users (e2e)', () => {
  let app: INestApplication;

  beforeAll(async () => {
    process.env.DB_TYPE = 'better-sqlite3';
    process.env.DB_DATABASE = ':memory:';

    const moduleRef = await Test.createTestingModule({ imports: [AppModule] }).compile();

    app = moduleRef.createNestApplication();
    app.useGlobalPipes(new ValidationPipe({ whitelist: true, transform: true }));
    await app.init();
  });

  afterAll(async () => {
    await app.close();
  });

  it('registers a user and retrieves it by id', async () => {
    const registered = await request(app.getHttpServer())
      .post('/users')
      .send({ email: 'nina@example.com', username: 'nina' })
      .expect(201);

    expect(registered.body).toMatchObject({
      email: 'nina@example.com',
      username: 'nina',
      status: 'ACTIVE',
    });
    expect(registered.body.id).toBeTruthy();

    const fetched = await request(app.getHttpServer())
      .get(`/users/${registered.body.id}`)
      .expect(200);

    expect(fetched.body).toEqual(registered.body);
  });

  it('rejects a duplicate email with 409', async () => {
    const payload = { email: 'dup@example.com', username: 'dup_user' };

    await request(app.getHttpServer()).post('/users').send(payload).expect(201);
    await request(app.getHttpServer()).post('/users').send(payload).expect(409);
  });

  it('rejects an invalid payload with 400', async () => {
    await request(app.getHttpServer())
      .post('/users')
      .send({ email: 'not-an-email', username: 'x' })
      .expect(400);
  });

  it('returns 404 for an unknown user id', async () => {
    await request(app.getHttpServer())
      .get('/users/00000000-0000-4000-8000-000000000000')
      .expect(404);
  });
});