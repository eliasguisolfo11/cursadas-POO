from abc import ABC, abstractmethod
from typing import Optional

from calculadora import formatear_pesos
from modelo import Pedido


class ValidadorPedido(ABC):
    @abstractmethod
    def set_siguiente(self, siguiente: "ValidadorPedido") -> "ValidadorPedido":
        """Enlaza el siguiente handler de la cadena."""

    @abstractmethod
    def validar(self, pedido: Pedido) -> bool:
        """Valida el pedido y continúa solo si el control pasa."""

    @property
    @abstractmethod
    def nombre(self) -> str:
        """Nombre del control para la salida por consola."""

    @abstractmethod
    def revisar(self, pedido: Pedido) -> Optional[str]:
        """Devuelve el motivo del rechazo o None si la validación pasa."""


class ValidadorBase(ValidadorPedido):
    def __init__(self) -> None:
        self._siguiente: Optional[ValidadorPedido] = None

    def set_siguiente(self, siguiente: ValidadorPedido) -> ValidadorPedido:
        self._siguiente = siguiente
        return siguiente

    def validar(self, pedido: Pedido) -> bool:
        error = self.revisar(pedido)
        if error is not None:
            print(f"  {self.nombre} -> ERROR: {error}. Se detiene la cadena.")
            return False
        print(f"  {self.nombre} -> OK")
        return self._siguiente is None or self._siguiente.validar(pedido)


class ValidarCliente(ValidadorBase):
    @property
    def nombre(self) -> str:
        return "Validar cliente"

    def revisar(self, pedido: Pedido) -> Optional[str]:
        return None if pedido.cliente_valido else "el cliente no está habilitado"


class ValidarStock(ValidadorBase):
    @property
    def nombre(self) -> str:
        return "Validar stock"

    def revisar(self, pedido: Pedido) -> Optional[str]:
        return None if pedido.stock_disponible else "no hay stock suficiente"


class ValidarPago(ValidadorBase):
    @property
    def nombre(self) -> str:
        return "Validar pago"

    def revisar(self, pedido: Pedido) -> Optional[str]:
        return None if pedido.pago_aprobado else "el medio de pago fue rechazado"


class ValidarLimiteCompra(ValidadorBase):
    @property
    def nombre(self) -> str:
        return "Validar límite de compra"

    def revisar(self, pedido: Pedido) -> Optional[str]:
        if pedido.precio_final <= pedido.limite_compra:
            return None
        limite = formatear_pesos(pedido.limite_compra)
        return f"el precio supera el límite de compra de {limite}"
