package ar.edu.cursadaspoo.notificaciones.model;

/** Puerto que representa cualquier medio capaz de enviar un mensaje. */
@FunctionalInterface
public interface Mensajeria {

    void enviar(String mensaje);
}
