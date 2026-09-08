# AGENTS.md — shared (núcleo compartido / kernel)

> Archivo **vivo**. Actualizar al introducir/eliminar primitivas compartidas.

## Propósito

Código compartido entre todos los módulos. Aquí viven **contratos, tipos base y utilidades transversales**, NO lógica de negocio de un dominio específico.

## Qué va aquí

- `contracts/` — interfaces/puertos públicos que los módulos consumen (ej. `Logger`, `Clock`, `IdGenerator`, `EventBus`).
- `kernel/` — primitivas de dominio: Value Objects genéricos (`Id`, `Money`, `Email`), entidades base, `DomainEvent` base.
- `errors/` — jerarquía de errores de dominio/uso (`DomainError`, `NotFoundError`, `ValidationError`).
- `utils/` — helpers puros sin estado ni dependencias externas.

## Qué NO va aquí

- Lógica de negocio específica de un módulo (va en `modules/<modulo>/`).
- Implementaciones de infraestructura (va en `infrastructure/`).

## Reglas

- **SOLID:** mantener primitivas pequeñas y con una única responsabilidad.
- No introducir dependencias concretas de framework aquí; mantener lenguaje-agnóstico.
- Si un contrato empieza a usarse en más de 2 módulos, considerar moverlo aquí. Si solo lo usa un módulo, mantenerlo dentro de ese módulo.

## Decisiones registradas

- _(ninguna por ahora)_
