# AGENTS.md — módulo Users

> Archivo **vivo**. Actualizar al introducir/cambiar casos de uso, entidades o patrones dentro del módulo.

## Propósito

Módulo de ejemplo que gestiona usuarios. Es la **plantilla** para crear nuevos módulos del monolito: demuestra DDD-lite + SOLID + Strategy + Chain of Responsibility sin cadenas de `if`, manteniendo el dominio agnóstico al framework.

## Estructura interna (convención para TODO módulo)

```
users/
├── AGENTS.md
├── dto/                 # validación del transporte (class-validator)
├── domain/
│   ├── entities/        # entidades puras, sin decoradores
│   └── value-objects/   # VO inmutables con su invariante (Email, RegistrationData)
├── ports/               # interfaces + tokens DI que el módulo define (DIP)
├── policies/            # patrón Strategy (políticas intercambiables) `+ token`
├── pipelines/           # patrón Chain of Responsibility (pasos ordenados con corte)
├── use-cases/           # orquestan dominio + puertos + políticas (reglas de aplicación)
├── users.controller.ts  # transporte HTTP (traduce HTTP ↔ use-cases, sin reglas)
└── users.module.ts      # agrupa providers; binding de tokens concreta aquí
```

## Reglas de negocio (ejemplo)

- Un `User` se crea con: `id` (generado por `IdGenerator`), `email` válido (VO), `username` opcional y `status`.
- La **validación** varía según el contexto (registro vs. importación masiva) → **Strategy** (`UserValidationPolicy`).
- El **registro** pasa por pasos en orden y puede cortarse → **Chain of Responsibility**: `validate → duplicate-check → persist`.
- Un email ya registrado es **conflicto** (`409`); entradas inválidas o invariantes rotas son **validación** (`400`); id inexistente es **404**.

## Patrones (NO violar)

1. **Strategy** — `policies/user-validation.policy.ts` (token `USER_VALIDATION_POLICY` + interfaz) e implementación `RegistrationValidationPolicy`; se inyecta al chain vía DI. Agregar una política nueva NO modifica el chain ni el caso de uso.
2. **Chain of Responsibility** — `pipelines/`: `ValidationHandler → DuplicateCheckHandler → PersistHandler`. Cada handler procesa y, si falla, **corta** la cadena lanzando un `AppError`. La composición de la cadena está en `pipelines/registration-chain.ts` (`buildRegistrationChain` usado por el provider `USER_REGISTRATION_CHAIN`).

## Decisiones registradas

- **Tokens compartidos con infraestructura**: `USER_REPOSITORY` se exporta desde `ports/user-repository.port.ts`; el adaptador `TypeOrmUserRepository` lo implementa y el binding global vive en `infrastructure/persistence/database.module.ts`. El módulo solo ve la interfaz.
- El **domain** (`User`, `Email`) no tiene decoradores: la entidad de persistencia `TypeOrmUser` (con decoradores) es aparte y el mapeo vive en el repositorio.
- `RegistrationData` es un VO de entrada simple (interface) definido en `domain/value-objects/`.

## Cómo clonar este módulo

1. Copiar `users/` con un nuevo nombre (`orders/`, `billing/`, etc.).
2. Renombrar entidades, VOs, casos de uso y el controller/DTO.
3. Ajustar `AGENTS.md` al nuevo dominio.
4. Si el módulo necesita datos de OTRO módulo: definir un puerto propio en `ports/` y su adaptador en `infrastructure/`, NUNCA importar al otro módulo.