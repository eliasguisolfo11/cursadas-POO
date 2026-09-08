# AGENTS.md — app (composición y arranque)

> Archivo **vivo**. Actualizar al modificar el wiring o el ciclo de vida de la aplicación.

## Propósito

Capa de composición: une los módulos con la infraestructura. Aquí se **cablean (wiring)** las dependencias y se define el arranque. No contiene lógica de negocio.

## Qué va aquí

- `bootstrap/` — orden de arranque, registro de módulos, inicialización.
- `composition-root/` — contenedor de DI, fábrica que resuelve las dependencias concretas de cada interfaz.
- `modules-registry/` — registro central de módulos instalados en el monolito.

## Reglas

- Es el **único lugar** donde se conocen las implementaciones concretas (Composition Root). El resto del código depende solo de abstracciones.
- El arranque debe ser explícito y en un orden claro (config → logging → infraestructura → módulos → http).
- Mantener desacoplado del framework: la composición puede hacerse manualmente o con contenedor de DI.

## Decisiones registradas

- _(ninguna por ahora)_
