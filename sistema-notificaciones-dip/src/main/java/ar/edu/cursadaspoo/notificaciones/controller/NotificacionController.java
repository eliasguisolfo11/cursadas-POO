package ar.edu.cursadaspoo.notificaciones.controller;

import ar.edu.cursadaspoo.notificaciones.model.Notificacion;
import ar.edu.cursadaspoo.notificaciones.service.NotificacionService;
import ar.edu.cursadaspoo.notificaciones.view.ConsolaView;

import java.util.Objects;

/** Recibe las acciones de la aplicación y coordina modelo/servicio y vista. */
public final class NotificacionController {

    private final NotificacionService notificacionService;
    private final ConsolaView consolaView;

    public NotificacionController(NotificacionService notificacionService, ConsolaView consolaView) {
        this.notificacionService = Objects.requireNonNull(notificacionService, "notificacionService no puede ser null");
        this.consolaView = Objects.requireNonNull(consolaView, "consolaView no puede ser null");
    }

    public void enviar(String mensaje) {
        Notificacion notificacion = notificacionService.notificar(mensaje);
        consolaView.mostrarRegistro(notificacion);
    }

    public void mostrarHistorial() {
        consolaView.mostrarHistorial(notificacionService.obtenerHistorial());
    }
}
