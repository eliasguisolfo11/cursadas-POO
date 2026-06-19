package builder;

import model.*;

public class BankAccountBuilder {
    private String holder;
    private String dni;
    private AccountType accountType;
    private Currency currency;
    private boolean debitCard;
    private boolean checkbook;
    private boolean homeBanking;
    private double withdrawalLimit;

    public BankAccountBuilder setHolder(String holder) {
        this.holder = holder;
        return this;
    }

    public BankAccountBuilder setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public BankAccountBuilder setAccountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public BankAccountBuilder setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public BankAccountBuilder setDebitCard(boolean debitCard) {
        this.debitCard = debitCard;
        return this;
    }

    public BankAccountBuilder setCheckbook(boolean checkbook) {
        this.checkbook = checkbook;
        return this;
    }

    public BankAccountBuilder setHomeBanking(boolean homeBanking) {
        this.homeBanking = homeBanking;
        return this;
    }

    public BankAccountBuilder setWithdrawalLimit(double withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
        return this;
    }

    public BankAccount build() {
        return new BankAccount(holder, dni, accountType, currency,
                               debitCard, checkbook, homeBanking, withdrawalLimit);
    }
}
