# AGENTS.md — infrastructure

> Archivo **vivo**. Actualizar al añadir/quitar adaptadores de infraestructura.

## Propósito

Adaptadores e implementaciones de infraestructura: persistencia, mensajería, HTTP, caching, configuración. Todas las **dependencias externas** se aíslan aquí.

## Qué va aquí

- `persistence/` — repositorios concretos, esquemas, migraciones, ORM/DB.
- `messaging/` — colas, buses de eventos, publishers de eventos de dominio.
- `http/` — controladores, rutas, middlewares, serializers de la capa de transporte.
- `config/` — carga de variables de entorno (`.env`), proveedores de configuración.
- `external/` — clientes de servicios de terceros (APIs, email, storage).

## Reglas

- **DIP:** las implementaciones de infraestructura **implementan** los contratos definidos en `shared/contracts` (o en los puertos de cada módulo). Nunca al revés: los módulos de negocio NO importan infraestructura.
- Mantener cada adaptador detrás de una **interfaz/port** para poder sustituirlo (test doubles, otra BD, etc.).
- Los secretos viven en `.env` (no versionado); usar `.env.example` como plantilla.

## Decisiones registradas

- _(ninguna por ahora)_
