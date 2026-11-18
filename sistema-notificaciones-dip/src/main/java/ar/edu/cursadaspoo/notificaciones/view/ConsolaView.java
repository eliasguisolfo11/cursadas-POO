package ar.edu.cursadaspoo.notificaciones.view;

import ar.edu.cursadaspoo.notificaciones.model.Notificacion;

import java.util.List;

/** Presenta en consola los mensajes y el historial entregados por el controlador. */
public final class ConsolaView {

    public void mostrarTitulo(String titulo) {
        System.out.printf("%n=== %s ===%n", titulo);
    }

    public void mostrarRegistro(Notificacion notificacion) {
        System.out.printf("[DAO] Notificacion registrada en el historial: %s%n", notificacion.mensaje());
    }

    public void mostrarHistorial(List<Notificacion> historial) {
        System.out.printf("%n[DAO] Historial: %d registro(s)%n", historial.size());
        for (int indice = 0; indice < historial.size(); indice++) {
            System.out.printf("  %d. %s%n", indice + 1, historial.get(indice).mensaje());
        }
    }
}
