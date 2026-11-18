package ar.edu.cursadaspoo.notificaciones.parte1;

/** Simula el servicio de correo de la implementación inicial. */
public final class EmailService {

    public void enviar(String mensaje) {
        System.out.printf("[EMAIL] %s%n", mensaje);
    }
}
