# AGENTS.md — modules (bounded contexts)

> Archivo **vivo**. Actualizar al agregar, eliminar o renombrar módulos.

## Propósito

Cada subcarpeta es un **bounded context** independiente. Un módulo encapsula su dominio, sus puertos y sus casos de uso. No conoce a los demás módulos por referencia directa.

## Reglas del ecosistema

1. **Cada módulo tiene su propio `AGENTS.md`**: propósito, estructura, reglas de negocio, patrones y decisiones registradas.
2. **Dirección de dependencias:** un módulo solo importa `shared/` y sus propios archivos. NUNCA importa `infrastructure/` ni otro módulo. El wiring con adaptadores se resuelve por **DI + tokens** (provistos por módulos de infra `@Global`).
3. **Comunicación entre módulos** (cuando exista más de uno):
   - **Puertos propios del consumidor**: el módulo que NECESITA define la interfaz; un adaptador en infra la implementa usando el módulo proveedor. Todo se cablea en el composition root.
   - **Eventos de dominio**: suscripción a eventos publicados (ej. `UserRegistered`), sin importarse clases.
4. **El dominio es agnóstico al framework**: entidades y value objects SIN decoradores de NestJS/TypeORM. Los decoradores solo aparecen en `infrastructure/persistence`.
5. **Cero `if/else` en cascada para decisiones de negocio**: validaciones intercambiables → Strategy; pasos secuenciales con corte → Chain of Responsibility (ver `users/`).

## Módulos actuales

- `users/` — módulo de ejemplo autónomo: registración + consulta por id. Demuestra Strategy (validación) y Chain of Responsibility (pipeline de registro).

## Decisiones registradas

- (ninguna por ahora)