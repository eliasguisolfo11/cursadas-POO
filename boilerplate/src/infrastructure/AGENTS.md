# AGENTS.md — infrastructure (adaptadores)

> Archivo **vivo**. Actualizar al añadir/quitar adaptadores o dependencias externas.

## Propósito

Implementaciones de todo lo externo: **persistencia (TypeORM)**, **http** (filtros de errores, transporte) y **config**. Aquí se aíslan las dependencias concretas. `modules/` y `shared/` NUNCA importan esta carpeta.

## Qué va aquí

- `config/` — construcción de opciones de TypeORM a partir de `ConfigService` + `.env`.
- `persistence/typeorm/` — **entidad de persistencia** (`TypeOrmUser`) y **adaptadores que implementan los puertos** (`TypeOrmUserRepository implements UserRepository`).
- `http/` — filtros de excepciones que traducen `AppError` del dominio a respuestas HTTP con el status adecuado.

## Reglas

- **DIP:** los adaptadores `implement` los puertos (interfaces) que los módulos definen; el binding se hace por **token** (`Symbol`) vía DI, nunca importando aquí desde el módulo.
- **Mapeo dominio ↔ persistencia** explícito: la entidad TypeORM (con decoradores) es distinta de la entidad de dominio (pura). El mapeo vive en el repositorio. No usar entidades de dominio como tablas.
- **Secrets** solo en `.env` (no versionado); `.env.example` es la plantilla.
- Cambiar de BD (sqlite → postgres) se hace en `.env` (`DB_TYPE`), NO en código. Si una BD nueva necesita configuración distinta, agregarla a `config/database.config.ts` y documentarla aquí.

## Decisiones registradas

- **SQLite** (`better-sqlite3`) como default para desarrollo (cero setup) y **Postgres** como opción, seleccionable vía `DB_TYPE`.
- `synchronize: true` solo en entornos no-producción (definido por `APP_ENV`); en producción usar migraciones.
- El `ExceptionFilter` de `AppError` registrado como global en `app.module.ts` (token `APP_FILTER`).