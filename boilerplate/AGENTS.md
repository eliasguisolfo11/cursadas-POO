# AGENTS.md — Boilerplate de Monolito Modular (NestJS)

> Archivo **vivo**: edítalo cuando se tomen decisiones de arquitectura, patrones o convenciones. Mantenlo conciso y accionable para futuros agentes.

## Propósito

Boilerplate **NestJS/TypeScript** de un **monolito modular**: módulos de negocio independientes (bounded contexts), núcleo compartido y adaptadores de infraestructura aislados detrás de **puertos (DIP)**. Punto de partida para proyectos nuevos: copiar, renombrar y adaptar cada módulo.

## Stack

- **NestJS 11** + **TypeScript** + **DI nativo** (composition root = `app.module.ts`).
- **TypeORM**: SQLite por defecto (cero setup) o Postgres según `.env`.
- **@nestjs/config** para configuración, **class-validator** en el transporte.
- **Jest** (unit colocalizados `*.spec.ts` + e2e en `test/`), **ESLint + Prettier**.

## Principios globales (NO negociables)

1. **SOLID siempre.** Cada archivo/clase respeta SRP, OCP, LSP, ISP, DIP.
2. **Dependencias hacia abstracciones.** `modules/` y `shared/` dependen de **interfaces (ports/contracts)**, nunca de implementaciones. Las implementaciones viven en `infrastructure/` y se cablean con DI (tokens `Symbol`).
3. **Dirección de dependencias (regla de la flecha):** `app` → `modules` → `shared` y `app` → `infrastructure` → `shared`. **`modules` NUNCA importa `infrastructure`.** El dominio NUNCA importa NestJS ni TypeORM.
4. **Sin cadenas de `if/else` para decisiones de negocio.** Según corresponda:
   - **Strategy**: una única estrategia válida elegida en runtime (ej. políticas de validación).
   - **Chain of Responsibility**: varios manejadores en orden, cada uno decide si procesa o corta (ej. pipeline de registro).
5. **Cada módulo** tiene su propio `AGENTS.md` (contexto, estructura, reglas, decisiones registradas). Lo mismo `shared/` e `infrastructure/`.
6. **Un módulo NO conoce a otro módulo por referencia directa.** Se comunica por **puertos propios** o **eventos de dominio**; la integración se cablea en el composition root.

## Arquitectura

```
boilerplate/
├── AGENTS.md                    <- este archivo
├── src/
│   ├── app.module.ts            <- composition root: wiring de puertos -> adaptadores
│   ├── main.ts
│   ├── modules/                 <- bounded contexts (uno por carpeta)
│   │   └── users/               <- módulo de ejemplo (con su AGENTS.md)
│   ├── shared/                  <- kernel compartido: contracts, errors, utils
│   └── infrastructure/          <- adaptadores: TypeORM, http (exception filter), config
└── test/                        <- e2e (los unit viven colocalizados como *.spec.ts)
```

Referencias: `src/modules/users/AGENTS.md`, `src/shared/AGENTS.md`, `src/infrastructure/AGENTS.md`, `test/AGENTS.md`.

## Reglas para tomar decisiones de arquitectura

- Si se introduce un **patrón nuevo** o un **cambio de diseño estructural**, actualizar este archivo y el `AGENTS.md` de la capa afectada.
- Si se introduce una **dependencia de infraestructura** (otra BD, cola, cache), documentarla en `src/infrastructure/AGENTS.md` y añadir su variable al `.env.example`.
- Si un **punto de extensión** (interfaz) lo usa un solo módulo, vive dentro de ese módulo (`ports/`). Si lo usan 2+ módulos, mover a `shared/contracts`.
- Ante **ambigüedad Strategy vs Chain of Responsibility**: ¿un único handler final o varios en orden con corte? Varios en orden → **Chain**. Uno elegido → **Strategy**.
- Los **tokens de DI** se exportan desde el archivo donde se define la interfaz (`export const USER_REPOSITORY = Symbol('USER_REPOSITORY')`), para que infra y módulo los compartan sin acoplarse.

## Pendiente / decisiones abiertas

- `users` es el único módulo por ahora. El próximo módulo de ejemplo (ej. `orders`) debería **consumir `users` mediante un puerto** para demostrar integración entre bounded contexts.
- No hay autenticación real (JWT/session): registración y consulta por id son los casos de uso demo.