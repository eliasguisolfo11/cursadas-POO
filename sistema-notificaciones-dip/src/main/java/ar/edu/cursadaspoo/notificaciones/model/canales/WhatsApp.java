package ar.edu.cursadaspoo.notificaciones.model.canales;

import ar.edu.cursadaspoo.notificaciones.model.Mensajeria;

/** Adaptador de demostración para WhatsApp. */
public final class WhatsApp implements Mensajeria {

    @Override
    public void enviar(String mensaje) {
        System.out.printf("[WHATSAPP] %s%n", mensaje);
    }
}
