import logger.Logger;
import servicio.*;

public class Main {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        TransferService transferService = new TransferService();
        AccountService accountService = new AccountService();

        loginService.iniciarSesion("fede");
        transferService.transferir(50000);
        accountService.consultarSaldo();
        loginService.cerrarSesion("fede");

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        System.out.println("\n¿Misma instancia? " + (logger1 == logger2));
    }
}
