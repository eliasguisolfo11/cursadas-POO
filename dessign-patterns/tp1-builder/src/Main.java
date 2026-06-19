import modelo.*;
import builder.*;

public class Main {
    public static void main(String[] args) {

        CuentaBancaria cuentaBasica = new CuentaBancariaBuilder()
                .setTitular("Fede")
                .setDni("12345678")
                .setTipoCuenta(TipoCuenta.CAJA_AHORRO)
                .setMoneda(Moneda.ARS)
                .setHomeBanking(true)
                .setTarjetaDebito(true)
                .setLimiteExtraccion(50000)
                .build();

        CuentaBancaria cuentaPremium = new CuentaBancariaBuilder()
                .setTitular("Fede")
                .setDni("12345678")
                .setTipoCuenta(TipoCuenta.CUENTA_CORRIENTE)
                .setMoneda(Moneda.USD)
                .setHomeBanking(true)
                .setTarjetaDebito(true)
                .setChequera(true)
                .setLimiteExtraccion(2000000)
                .build();

        cuentaBasica.mostrarDatos();
        cuentaPremium.mostrarDatos();
    }
}
