package modelo;

public class CuentaBancaria {
    private String titular;
    private String dni;
    private TipoCuenta tipoCuenta;
    private Moneda moneda;
    private boolean tarjetaDebito;
    private boolean chequera;
    private boolean homeBanking;
    private double limiteExtraccion;

    public CuentaBancaria(String titular, String dni, TipoCuenta tipoCuenta, Moneda moneda,
                   boolean tarjetaDebito, boolean chequera, boolean homeBanking, double limiteExtraccion) {
        this.titular = titular;
        this.dni = dni;
        this.tipoCuenta = tipoCuenta;
        this.moneda = moneda;
        this.tarjetaDebito = tarjetaDebito;
        this.chequera = chequera;
        this.homeBanking = homeBanking;
        this.limiteExtraccion = limiteExtraccion;
    }

    public void mostrarDatos() {
        System.out.println("===== DATOS DE LA CUENTA =====");
        System.out.println("Titular: " + titular);
        System.out.println("DNI: " + dni);
        System.out.println("Tipo de cuenta: " + tipoCuenta);
        System.out.println("Moneda: " + moneda);
        System.out.println("Tarjeta de débito: " + (tarjetaDebito ? "Sí" : "No"));
        System.out.println("Chequera: " + (chequera ? "Sí" : "No"));
        System.out.println("Home Banking: " + (homeBanking ? "Sí" : "No"));
        System.out.println("Límite de extracción: $" + limiteExtraccion);
        System.out.println("==============================\n");
    }
}
