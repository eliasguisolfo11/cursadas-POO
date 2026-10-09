from decimal import Decimal, ROUND_HALF_UP
from typing import Sequence

from estrategias import EstrategiaPrecio
from modelo import Compra


def formatear_pesos(valor: Decimal) -> str:
    numero = f"{valor:,.2f}"
    numero = numero.replace(",", "#").replace(".", ",").replace("#", ".")
    return f"${numero}"


class CalculadoraPrecio:
    def calcular(self, compra: Compra, estrategias: Sequence[EstrategiaPrecio]) -> Decimal:
        precio = compra.subtotal
        print(f"  Subtotal: {formatear_pesos(precio)}")
        for estrategia in estrategias:
            anterior = precio
            precio = estrategia.aplicar(compra, precio).quantize(Decimal("0.01"), rounding=ROUND_HALF_UP)
            print(f"  {estrategia.nombre:<38} {formatear_pesos(anterior)} -> {formatear_pesos(precio)}")
        print(f"  Precio final: {formatear_pesos(precio)}")
        return precio
