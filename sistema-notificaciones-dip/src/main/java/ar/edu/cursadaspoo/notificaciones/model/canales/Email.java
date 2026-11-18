package ar.edu.cursadaspoo.notificaciones.model.canales;

import ar.edu.cursadaspoo.notificaciones.model.Mensajeria;

/** Adaptador de demostración para correo electrónico. */
public final class Email implements Mensajeria {

    @Override
    public void enviar(String mensaje) {
        System.out.printf("[EMAIL] %s%n", mensaje);
    }
}
