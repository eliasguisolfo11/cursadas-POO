package servicio;

import logger.Logger;

public class TransferService {
    private Logger logger = Logger.getInstance();

    public void transferir(double monto) {
        logger.log("Transferencia realizada por $" + monto);
    }
}
