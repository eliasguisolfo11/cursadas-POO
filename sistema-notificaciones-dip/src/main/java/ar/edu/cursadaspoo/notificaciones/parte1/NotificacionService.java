package ar.edu.cursadaspoo.notificaciones.parte1;

/**
 * Versión inicial: conoce y crea directamente el servicio concreto de correo.
 * Se conserva para comparar el acoplamiento con la versión refactorizada.
 */
public final class NotificacionService {

    private final EmailService emailService = new EmailService();

    public void enviarNotificacion(String mensaje) {
        emailService.enviar(mensaje);
    }
}
