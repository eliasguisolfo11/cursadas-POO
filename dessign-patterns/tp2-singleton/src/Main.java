import java.util.Scanner;
import logger.Logger;
import servicio.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del usuario: ");
        String usuario = sc.nextLine();
        sc.close();

        LoginService loginService = new LoginService();
        TransferService transferService = new TransferService();
        AccountService accountService = new AccountService();

        loginService.iniciarSesion(usuario);
        transferService.transferir(50000);
        accountService.consultarSaldo();
        loginService.cerrarSesion(usuario);

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        System.out.println("\n¿Misma instancia? " + (logger1 == logger2));
    }
}
