# AGENTS.md — módulo Users

> Archivo **vivo**. Actualizar al introducir/cambiar casos de uso, entidades o patrones dentro del módulo.

## Propósito

Módulo de ejemplo que gestiona usuarios (agregado `User`). Sirve como **plantilla** para crear nuevos módulos del monolito. Demuestra estructura de DDD + SOLID + patrones Strategy/Chain of Responsibility sin cadenas de `if`.

## Estructura interna (convención para TODO módulo)

```
users/
├── AGENTS.md
├── domain/          # entidades, value objects, errores de dominio, eventos
│   ├── entities/
│   ├── value-objects/
│   ├── events/
│   └── errors/
├── ports/           # interfaces (puertos) que el módulo define y necesita
│   ├── UserRepository.ts      # puerto de persistencia (DIP)
│   └── ...
├── use-cases/       # casos de uso / aplicación (orquestan dominio + puertos)
├── domain-services/ # lógica que no pertenece a una sola entidad
└── policies/        # patrón Strategy (políticas intercambiables)
    └── chains/      # patrón Chain of Responsibility (pipelines ordenadas)
```

## Patrones usados aquí (ejemplos que NUNCA deben violar)

1. **Strategy** — `policies/UserValidationPolicy` (interfaz) + `UsernamePolicy`, `EmailPolicy`. Se elige UNA estrategia en tiempo de ejecución/inyección. Sin `if/else` encadenados.
2. **Chain of Responsibility** — `chains/UserRegistrationHandler` con handlers encadenados (p.ej. `ValidateHandler -> DomainCheckHandler -> PersistHandler`). Cada handler decide si procesa o delega al siguiente.

## Reglas de negocio (ejemplo)

- Un `User` se crea siempre con `id` (value object) y `email` válido.
- La validación es **distinta según la política** (registro vs. importación masiva) → se resuelve con **Strategy**.
- El flujo de registro pasa por **pasos encadenados y en orden** → **Chain of Responsibility**.

## Decisiones registradas

- Se eligió **Strategy** para validación (una sola política activa por contexto) y **Chain of Responsibility** para el pipeline de registro (pasos secuenciales con la posibilidad de cortar la cadena).
- Los puertos viven dentro del módulo (`users/ports`) porque solo este módulo los usa. Si otro módulo los reutiliza, mover a `shared/contracts`.

## Cómo clonar este módulo

1. Copiar `users/` con un nuevo nombre (`orders/`, `billing/`, etc.).
2. Renombrar entidades, value objects y casos de uso.
3. Actualizar este `AGENTS.md` con el nuevo dominio.
