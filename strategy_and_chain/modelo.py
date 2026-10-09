from dataclasses import dataclass
from decimal import Decimal


@dataclass(frozen=True)
class Compra:
    cliente: str
    cantidad_productos: int
    subtotal: Decimal

    def __post_init__(self) -> None:
        if not self.cliente.strip():
            raise ValueError("El cliente es obligatorio.")
        if self.cantidad_productos < 0:
            raise ValueError("La cantidad de productos no puede ser negativa.")
        if self.subtotal < Decimal("0"):
            raise ValueError("El subtotal no puede ser negativo.")


@dataclass(frozen=True)
class Pedido:
    numero: str
    compra: Compra
    precio_final: Decimal
    cliente_valido: bool
    stock_disponible: bool
    pago_aprobado: bool
    limite_compra: Decimal
