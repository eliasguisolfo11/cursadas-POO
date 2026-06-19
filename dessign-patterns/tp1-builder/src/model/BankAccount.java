package model;

public class BankAccount {
    private String holder;
    private String dni;
    private AccountType accountType;
    private Currency currency;
    private boolean debitCard;
    private boolean checkbook;
    private boolean homeBanking;
    private double withdrawalLimit;

    public BankAccount(String holder, String dni, AccountType accountType, Currency currency,
                       boolean debitCard, boolean checkbook, boolean homeBanking, double withdrawalLimit) {
        this.holder = holder;
        this.dni = dni;
        this.accountType = accountType;
        this.currency = currency;
        this.debitCard = debitCard;
        this.checkbook = checkbook;
        this.homeBanking = homeBanking;
        this.withdrawalLimit = withdrawalLimit;
    }

    public void showDetails() {
        System.out.println("===== DATOS DE LA CUENTA =====");
        System.out.println("Titular: " + holder);
        System.out.println("DNI: " + dni);
        System.out.println("Tipo de cuenta: " + accountType);
        System.out.println("Moneda: " + currency);
        System.out.println("Tarjeta de débito: " + (debitCard ? "Sí" : "No"));
        System.out.println("Chequera: " + (checkbook ? "Sí" : "No"));
        System.out.println("Home Banking: " + (homeBanking ? "Sí" : "No"));
        System.out.println("Límite de extracción: $" + withdrawalLimit);
        System.out.println("==============================\n");
    }
}
