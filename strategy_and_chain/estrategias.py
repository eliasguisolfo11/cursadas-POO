from abc import ABC, abstractmethod
from decimal import Decimal

from modelo import Compra


class EstrategiaPrecio(ABC):
    @property
    @abstractmethod
    def nombre(self) -> str:
        """Nombre descriptivo de la regla aplicada."""

    @abstractmethod
    def aplicar(self, compra: Compra, precio_actual: Decimal) -> Decimal:
        """Devuelve el precio luego de aplicar esta regla."""


class DescuentoComun(EstrategiaPrecio):
    @property
    def nombre(self) -> str:
        return "Cliente común (0%)"

    def aplicar(self, compra: Compra, precio_actual: Decimal) -> Decimal:
        return precio_actual


class DescuentoPremium(EstrategiaPrecio):
    @property
    def nombre(self) -> str:
        return "Cliente Premium (-10%)"

    def aplicar(self, compra: Compra, precio_actual: Decimal) -> Decimal:
        return precio_actual * Decimal("0.90")


class DescuentoVIP(EstrategiaPrecio):
    @property
    def nombre(self) -> str:
        return "Cliente VIP (-15%)"

    def aplicar(self, compra: Compra, precio_actual: Decimal) -> Decimal:
        return precio_actual * Decimal("0.85")


class DescuentoPorCantidad(EstrategiaPrecio):
    @property
    def nombre(self) -> str:
        return "Descuento por cantidad (>10: -10%; >5: -5%)"

    def aplicar(self, compra: Compra, precio_actual: Decimal) -> Decimal:
        if compra.cantidad_productos > 10:
            return precio_actual * Decimal("0.90")
        if compra.cantidad_productos > 5:
            return precio_actual * Decimal("0.95")
        return precio_actual


class DescuentoPorcentaje(EstrategiaPrecio):
    def __init__(self, nombre: str, porcentaje: Decimal) -> None:
        if not Decimal("0") <= porcentaje <= Decimal("100"):
            raise ValueError("El porcentaje debe estar entre 0 y 100.")
        self._nombre = nombre
        self._porcentaje = porcentaje

    @property
    def nombre(self) -> str:
        porcentaje = format(self._porcentaje.normalize(), "f")
        return f"{self._nombre} (-{porcentaje}%)"

    def aplicar(self, compra: Compra, precio_actual: Decimal) -> Decimal:
        return precio_actual * (Decimal("1") - self._porcentaje / Decimal("100"))


class DescuentoBlackFriday(DescuentoPorcentaje):
    def __init__(self) -> None:
        super().__init__("Black Friday", Decimal("10"))


class RetiroEnSucursal(EstrategiaPrecio):
    @property
    def nombre(self) -> str:
        return "Retiro en sucursal (+$0)"

    def aplicar(self, compra: Compra, precio_actual: Decimal) -> Decimal:
        return precio_actual


class EnvioNormal(EstrategiaPrecio):
    @property
    def nombre(self) -> str:
        return "Envío normal (+$5.000)"

    def aplicar(self, compra: Compra, precio_actual: Decimal) -> Decimal:
        return precio_actual + Decimal("5000")


class EnvioExpress(EstrategiaPrecio):
    @property
    def nombre(self) -> str:
        return "Envío express (+$10.000)"

    def aplicar(self, compra: Compra, precio_actual: Decimal) -> Decimal:
        return precio_actual + Decimal("10000")
