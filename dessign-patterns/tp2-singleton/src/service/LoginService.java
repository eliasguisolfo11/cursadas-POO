package service;

import logger.Logger;

public class LoginService {
    private Logger logger = Logger.getInstance();

    public void login(String username) {
        logger.log("Usuario " + username + " inició sesión");
    }

    public void logout(String username) {
        logger.log("Usuario " + username + " cerró sesión");
    }
}
