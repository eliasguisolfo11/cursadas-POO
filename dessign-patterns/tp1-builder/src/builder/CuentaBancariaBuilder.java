package builder;

import modelo.*;

public class CuentaBancariaBuilder {
    private String titular;
    private String dni;
    private TipoCuenta tipoCuenta;
    private Moneda moneda;
    private boolean tarjetaDebito;
    private boolean chequera;
    private boolean homeBanking;
    private double limiteExtraccion;

    public CuentaBancariaBuilder setTitular(String titular) {
        this.titular = titular;
        return this;
    }

    public CuentaBancariaBuilder setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public CuentaBancariaBuilder setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
        return this;
    }

    public CuentaBancariaBuilder setMoneda(Moneda moneda) {
        this.moneda = moneda;
        return this;
    }

    public CuentaBancariaBuilder setTarjetaDebito(boolean tarjetaDebito) {
        this.tarjetaDebito = tarjetaDebito;
        return this;
    }

    public CuentaBancariaBuilder setChequera(boolean chequera) {
        this.chequera = chequera;
        return this;
    }

    public CuentaBancariaBuilder setHomeBanking(boolean homeBanking) {
        this.homeBanking = homeBanking;
        return this;
    }

    public CuentaBancariaBuilder setLimiteExtraccion(double limiteExtraccion) {
        this.limiteExtraccion = limiteExtraccion;
        return this;
    }

    public CuentaBancaria build() {
        return new CuentaBancaria(titular, dni, tipoCuenta, moneda,
                                  tarjetaDebito, chequera, homeBanking, limiteExtraccion);
    }
}
