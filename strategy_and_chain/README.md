# TP: Strategy + Chain of Responsibility

Implementación didáctica en Python 3.11, sin dependencias externas.

## Ejecutar

Desde esta carpeta:

```powershell
python main.py
```

La salida muestra cada estrategia y el precio antes/después, seguida por los pasos de validación. Los descuentos se aplican uno después del otro sobre el precio vigente, en el orden de la lista; el envío suma su importe cuando aparece.

## Diseño

- **Strategy:** `EstrategiaPrecio` define el contrato. `CalculadoraPrecio` recibe una lista de estrategias y las aplica sin conocer sus clases concretas. Así se combinan descuento del cliente, cantidad, promociones y envío. Para incorporar otra regla se agrega otra implementación y se incorpora a la lista.
- **Chain of Responsibility:** `ValidadorPedido` define el contrato de los handlers y `ValidadorBase` mantiene el siguiente. Cada handler valida una condición; si falla, devuelve `False` y la llamada no avanza. La cadena se arma en `main.py` como Cliente → Stock → Pago → Límite de compra.
- **Desafío:** `DescuentoBlackFriday` agrega una estrategia concreta reutilizando la regla porcentual. `ValidarLimiteCompra` agrega un validador concreto. Ambos se incorporan en `main.py`.

## Casos de demostración

- P-001: aprobado.
- P-002: rechazado por falta de stock; pago y límite no se ejecutan.
- P-003: rechazado por el pago; el límite no se ejecuta.

La compra de demostración parte de $100.000 y tiene 12 productos: VIP (-15%), cantidad (>10: -10%), promoción especial (-5%), Black Friday (-10%) y envío express (+$10.000). Como el enunciado no fija otro método de combinación, cada descuento porcentual se calcula sobre el importe vigente al llegar a esa estrategia; el orden queda explícito en la lista.

## Respuestas

1. **¿Qué problema resuelve Strategy?** Encapsula reglas intercambiables detrás de una interfaz común, evitando concentrarlas en condicionales de una clase y permitiendo extenderlas.
2. **¿Por qué una compra puede necesitar varias estrategias?** Porque el precio puede depender a la vez del tipo de cliente, la cantidad, promociones y método de entrega; la calculadora compone sus efectos en orden.
3. **¿Qué problema resuelve Chain of Responsibility?** Desacopla quien inicia el procesamiento de los validadores concretos, enlazando controles independientes en una secuencia.
4. **¿Qué sucede cuando un elemento rechaza el pedido?** Informa el motivo, detiene la cadena y los handlers posteriores no se ejecutan.

## Diagrama

El diagrama UML está en [`docs/diagrama-uml.puml`](docs/diagrama-uml.puml) y puede renderizarse con PlantUML.

## Documentación consultada

- [Strategy — Refactoring.Guru](https://refactoring.guru/design-patterns/strategy): estrategias concretas comparten una interfaz y el contexto trabaja sin acoplarse a sus implementaciones.
- [Chain of Responsibility — Refactoring.Guru](https://refactoring.guru/design-patterns/chain-of-responsibility): handlers enlazados deciden si pasan la solicitud al siguiente o detienen el procesamiento.
