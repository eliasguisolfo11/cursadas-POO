import java.util.Scanner;
import model.*;
import builder.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del titular: ");
        String holder = sc.nextLine();
        sc.close();

        BankAccount basicAccount = new BankAccountBuilder()
                .setHolder(holder)
                .setDni("12345678")
                .setAccountType(AccountType.SAVINGS)
                .setCurrency(Currency.ARS)
                .setHomeBanking(true)
                .setDebitCard(true)
                .setWithdrawalLimit(50000)
                .build();

        BankAccount premiumAccount = new BankAccountBuilder()
                .setHolder(holder)
                .setDni("12345678")
                .setAccountType(AccountType.CHECKING)
                .setCurrency(Currency.USD)
                .setHomeBanking(true)
                .setDebitCard(true)
                .setCheckbook(true)
                .setWithdrawalLimit(2000000)
                .build();

        basicAccount.showDetails();
        premiumAccount.showDetails();
    }
}
