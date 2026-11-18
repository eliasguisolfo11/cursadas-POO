package ar.edu.cursadaspoo.notificaciones.model.canales;

import ar.edu.cursadaspoo.notificaciones.model.Mensajeria;

/** Adaptador de demostración para mensajes SMS. */
public final class SMS implements Mensajeria {

    @Override
    public void enviar(String mensaje) {
        System.out.printf("[SMS] %s%n", mensaje);
    }
}
