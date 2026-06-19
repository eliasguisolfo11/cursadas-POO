package service;

import logger.Logger;

public class TransferService {
    private Logger logger = Logger.getInstance();

    public void transfer(double amount) {
        logger.log("Transferencia realizada por $" + amount);
    }
}
