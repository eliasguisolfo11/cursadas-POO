# AGENTS.md — tests

> Archivo **vivo**. Actualizar al cambiar la estrategia de testing.

## Propósito

Tests del monolito, organizados por módulo e integración. Cada módulo trae sus propios tests.

## Estructura

- `unit/` — tests unitarios de dominio y casos de uso (sin infraestructura, con mocks/stubs de los puertos).
- `integration/` — tests que cruzan módulos o involucran infraestructura real (BD en memoria/testcontainer).
- `e2e/` — flujos completos a través de la capa HTTP/transporte.

## Reglas

- Probar **comportamiento**, no implementación. Usar los puertos/interfaces para aislar.
- Los patrón Strategy/Chain of Responsibility deben probarse por separado: una estrategia por caso y la cadena completa con casos de orden/parada.
- No depender de red ni servicios externos en tests unitarios.

## Decisiones registradas

- _(ninguna por ahora)_
