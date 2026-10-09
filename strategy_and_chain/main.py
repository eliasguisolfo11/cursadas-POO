from decimal import Decimal
from typing import Sequence

from calculadora import CalculadoraPrecio
from estrategias import (
    DescuentoBlackFriday,
    DescuentoPorCantidad,
    DescuentoPorcentaje,
    DescuentoVIP,
    EnvioExpress,
    EstrategiaPrecio,
)
from modelo import Compra, Pedido
from validadores import (
    ValidarCliente,
    ValidarLimiteCompra,
    ValidarPago,
    ValidarStock,
    ValidadorPedido,
)


CALCULADORA = CalculadoraPrecio()


def crear_cadena() -> ValidadorPedido:
    cliente = ValidarCliente()
    stock = ValidarStock()
    pago = ValidarPago()
    limite = ValidarLimiteCompra()
    cliente.set_siguiente(stock).set_siguiente(pago).set_siguiente(limite)
    return cliente


def procesar(numero: str, cliente_valido: bool, stock: bool, pago: bool) -> None:
    print(f"\n--- {numero} ---")
    compra = Compra(f"Cliente {numero}", 12, Decimal("100000"))
    estrategias: Sequence[EstrategiaPrecio] = [
        DescuentoVIP(),
        DescuentoPorCantidad(),
        DescuentoPorcentaje("Promoción especial", Decimal("5")),
        DescuentoBlackFriday(),
        EnvioExpress(),
    ]
    precio_final = CALCULADORA.calcular(compra, estrategias)
    pedido = Pedido(
        numero=numero,
        compra=compra,
        precio_final=precio_final,
        cliente_valido=cliente_valido,
        stock_disponible=stock,
        pago_aprobado=pago,
        limite_compra=Decimal("100000"),
    )

    print("  Cadena de validación:")
    aprobado = crear_cadena().validar(pedido)
    resultado = "APROBADO" if aprobado else "RECHAZADO"
    print(f"  Resultado: pedido {resultado}")


def main() -> None:
    print("=== Strategy + Chain of Responsibility ===")
    procesar("P-001 aprobado", cliente_valido=True, stock=True, pago=True)
    procesar("P-002 sin stock", cliente_valido=True, stock=False, pago=True)
    procesar("P-003 pago rechazado", cliente_valido=True, stock=True, pago=False)


if __name__ == "__main__":
    main()
