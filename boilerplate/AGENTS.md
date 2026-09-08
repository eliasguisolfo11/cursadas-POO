# AGENTS.md — Boilerplate de Monolito Modular

> Archivo **vivo**: edítalo cuando se tomen decisiones de arquitectura, patrones o convenciones. Mantenlo conciso y accionable para futuros agentes.

## Propósito

Este repositorio es una **maqueta (boilerplate)** de un **monolito modular**, agnóstica a lenguaje/framework. Se usa como punto de partida para iniciar proyectos nuevos copiando esta carpeta y adaptando cada módulo al stack elegido.

## Principios globales (NO negociables)

1. **SOLID siempre.** Cada archivo/clase debe respetar SRP, OCP, LSP, ISP, DIP.
2. **Sin cadenas de `if/else` para decisiones de negocio.** Para eliminar *if chains* usar, según corresponda:
   - **Strategy**: cuando hay una única estrategia válida a ejecutar elegida en tiempo de ejecución (comportamiento intercambiable).
   - **Chain of Responsibility**: cuando hay múltiples manejadores en cadena, cada uno decide si procesa o pasa al siguiente, en orden.
3. **Dependencias** siempre apuntan hacia **abstracciones (interfaces)**, no implementaciones concretas. Usar inyección de dependencias.
4. **Cada módulo importante** tiene su propio `AGENTS.md` que documenta su contexto.
5. **Multi-lenguaje agnóstico:** las carpetas son conceptuales; al instanciar un proyecto, mapear a la estructura idiomática del lenguaje elegido.

## Arquitectura (monolito modular)

```
boilerplate/
├── AGENTS.md                 <- este archivo
├── modules/                  <- módulos de negocio (Bounded Contexts)
│   └── users/                <- ejemplo de módulo
├── shared/                   <- núcleo compartido (kernel)
├── infrastructure/           <- persistencia, mensajería, http, config
├── app/                      <- composición, arranque, wiring de dependencias
└── tests/                    <- tests por módulo y de integración
```

Ver `shared/AGENTS.md`, `infrastructure/AGENTS.md`, `app/AGENTS.md` y el `modules/users/AGENTS.md`.

## Reglas para tomar decisiones de arquitectura

- Si se introduce un **patrón nuevo** o un **cambio de diseño estructural**, actualizar este archivo y el `AGENTS.md` del módulo/área afectada.
- Si se introduce una **dependencia de infraestructura** (BD, cola, cache), documentarla en `infrastructure/AGENTS.md`.
- Ante **ambigüedad entre Strategy y Chain of Responsibility**, preguntar: ¿hay un único handler final o varios pueden participar? Varios participantes en orden → **Chain**. Uno solo elegido → **Strategy**.

## Pendiente / decisiones abiertas

_(Registrar aquí decisiones aún no resueltas para que los agentes futuros las cierren.)_

- Ninguna por ahora.
