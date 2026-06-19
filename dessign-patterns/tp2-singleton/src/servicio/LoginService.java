package servicio;

import logger.Logger;

public class LoginService {
    private Logger logger = Logger.getInstance();

    public void iniciarSesion(String usuario) {
        logger.log("Usuario " + usuario + " inició sesión");
    }

    public void cerrarSesion(String usuario) {
        logger.log("Usuario " + usuario + " cerró sesión");
    }
}
