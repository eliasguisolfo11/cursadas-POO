package servicio;

import logger.Logger;

public class AccountService {
    private Logger logger = Logger.getInstance();

    public void consultarSaldo() {
        logger.log("Consulta de saldo realizada");
    }
}
