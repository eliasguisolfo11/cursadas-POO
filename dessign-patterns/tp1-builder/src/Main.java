import java.util.Scanner;
import modelo.*;
import builder.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del titular: ");
        String titular = sc.nextLine();
        sc.close();

        CuentaBancaria cuentaBasica = new CuentaBancariaBuilder()
                .setTitular(titular)
                .setDni("12345678")
                .setTipoCuenta(TipoCuenta.CAJA_AHORRO)
                .setMoneda(Moneda.ARS)
                .setHomeBanking(true)
                .setTarjetaDebito(true)
                .setLimiteExtraccion(50000)
                .build();

        CuentaBancaria cuentaPremium = new CuentaBancariaBuilder()
                .setTitular(titular)
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
