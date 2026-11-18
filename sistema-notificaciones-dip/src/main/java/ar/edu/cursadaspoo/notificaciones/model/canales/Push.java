package ar.edu.cursadaspoo.notificaciones.model.canales;

import ar.edu.cursadaspoo.notificaciones.model.Mensajeria;

/** Adaptador de demostración para notificaciones push. */
public final class Push implements Mensajeria {

    @Override
    public void enviar(String mensaje) {
        System.out.printf("[PUSH] %s%n", mensaje);
    }
}
