# AGENTS.md — shared (núcleo compartido / kernel)

> Archivo **vivo**. Actualizar al introducir/eliminar contratos, errores o utilidades compartidas.

## Propósito

Código compartido entre todos los módulos: **contratos (interfaces), jerarquía de errores y helpers puros**. NO contiene lógica de negocio de un dominio específico.

## Qué va aquí

- `contracts/` — interfaces/tokens que cualquier capa consume (ej. `IdGenerator`, `Clock`, `Logger`). Cada archivo exporta su **token `Symbol`** + la **interfaz**.
- `errors/` — jerarquía base: `AppError` → `DomainError` → `ValidationError | NotFoundError | ConflictError`. Los casos de uso lanzan estos tipos; `infrastructure/http` los traduce a HTTP.
- `utils/` — helpers puros sin estado y sin dependencias externas (ej. `UuidIdGenerator`).

## Qué NO va aquí

- Lógica de negocio de un módulo (va en `src/modules/<modulo>/`).
- Implementaciones de infraestructura (van en `src/infrastructure/`). `utils` solo contiene cosas sin estado ni efectos.

## Reglas

- **SOLID**: primitivas pequeñas, una única responsabilidad.
- **Sin dependencias** de NestJS, TypeORM ni frameworks. Este paquete debe poder compilarse y probarse aislado.
- Un contrato que usen 2+ módulos vive aquí; uno que use un solo módulo vive en `modules/<modulo>/ports/`.
- El **token** se exporta junto a la interfaz para que módulo e infra compartan el mismo `Symbol`.

## Decisiones registradas

- Se exporta `ID_GENERATOR` desde `contracts/id-generator.contract.ts` y su implementación `UuidIdGenerator` está en `utils/` (usa `node:crypto`, sin deps externas). El binding del token vive en `modules/users/users.module.ts`.