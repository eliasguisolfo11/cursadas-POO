package service;

import logger.Logger;

public class AccountService {
    private Logger logger = Logger.getInstance();

    public void checkBalance() {
        logger.log("Consulta de saldo realizada");
    }
}
