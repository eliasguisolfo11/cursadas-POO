package ar.edu.cursadaspoo.notificaciones.dao.impl;

import ar.edu.cursadaspoo.notificaciones.dao.NotificacionDAO;
import ar.edu.cursadaspoo.notificaciones.model.Notificacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** DAO de demostración; mantiene los registros mientras el proceso está activo. */
public final class NotificacionDAOMemoria implements NotificacionDAO {

    private final List<Notificacion> notificaciones = new ArrayList<>();

    @Override
    public synchronized void guardar(Notificacion notificacion) {
        notificaciones.add(Objects.requireNonNull(notificacion, "notificacion no puede ser null"));
    }

    @Override
    public synchronized List<Notificacion> listar() {
        return List.copyOf(notificaciones);
    }
}
