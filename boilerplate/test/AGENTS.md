# AGENTS.md — test

> Archivo **vivo**. Actualizar al cambiar la estrategia de testing.

## Propósito

Tests del boilerplate. Los **unitarios viven colocalizados** como `*.spec.ts` junto al código que prueban (convención NestJS); los **e2e** viven en `test/`.

## Cómo correr

- `npm test` — unitarios (Jest, `rootDir: src`, patrón `*.spec.ts`).
- `npm run test:e2e` — e2e completos (config `test/jest-e2e.json`, patrón `*.e2e-spec.ts`).
- `npm run test:cov` — cobertura.

## Reglas

- Probar **comportamiento**, no implementación. En unitarios, aislar los puertos con **fakes** (probar contracts, no TypeORM).
- **Strategy**: probar cada política por separado (entrada válida, cada caso inválido).
- **Chain of Responsibility**: probar la cadena completa — happy path, corte por validación y corte por conflicto — verificando que los handlers posteriores NO se ejecuten.
- E2e usa SQLite en memoria (`DB_DATABASE=:memory:`); no depender de servicios externos ni de red.
- Los `AppError` se traducen a HTTP: validación `400`, conflicto `409`, no encontrado `404`.

## Decisiones registradas

- Los e2e bootean `AppModule` real (con `DatabaseModule` global) sobre SQLite en memoria; `ValidationPipe` global se repite en el test para imitar `main.ts`.