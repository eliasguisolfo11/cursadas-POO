package ar.edu.cursadaspoo.notificaciones.app;

import ar.edu.cursadaspoo.notificaciones.parte1.NotificacionService;

/** Ejecuta la implementación inicial de la Parte 1. */
public final class MainParte1 {

    private MainParte1() {
    }

    public static void main(String[] args) {
        System.out.println("=== PARTE 1: IMPLEMENTACION INICIAL ===");
        new NotificacionService().enviarNotificacion("Tu pedido esta listo para retirar.");
    }
}
