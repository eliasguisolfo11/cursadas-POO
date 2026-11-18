package ar.edu.cursadaspoo.notificaciones.service;

import ar.edu.cursadaspoo.notificaciones.dao.NotificacionDAO;
import ar.edu.cursadaspoo.notificaciones.model.Mensajeria;
import ar.edu.cursadaspoo.notificaciones.model.Notificacion;

import java.util.List;
import java.util.Objects;

/** Coordina el caso de uso sin conocer ningún canal concreto. */
public final class NotificacionService {

    private final Mensajeria mensajeria;
    private final NotificacionDAO notificacionDAO;

    public NotificacionService(Mensajeria mensajeria, NotificacionDAO notificacionDAO) {
        this.mensajeria = Objects.requireNonNull(mensajeria, "mensajeria no puede ser null");
        this.notificacionDAO = Objects.requireNonNull(notificacionDAO, "notificacionDAO no puede ser null");
    }

    public Notificacion notificar(String mensaje) {
        mensajeria.enviar(mensaje);
        Notificacion notificacion = new Notificacion(mensaje);
        notificacionDAO.guardar(notificacion);
        return notificacion;
    }

    public List<Notificacion> obtenerHistorial() {
        return notificacionDAO.listar();
    }
}
