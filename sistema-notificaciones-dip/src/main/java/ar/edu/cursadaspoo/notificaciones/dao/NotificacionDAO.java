package ar.edu.cursadaspoo.notificaciones.dao;

import ar.edu.cursadaspoo.notificaciones.model.Notificacion;

import java.util.List;

/** Contrato de acceso y persistencia del historial de notificaciones. */
public interface NotificacionDAO {

    void guardar(Notificacion notificacion);

    List<Notificacion> listar();
}
