package ar.edu.cursadaspoo.notificaciones.app;

import ar.edu.cursadaspoo.notificaciones.controller.NotificacionController;
import ar.edu.cursadaspoo.notificaciones.dao.NotificacionDAO;
import ar.edu.cursadaspoo.notificaciones.dao.impl.NotificacionDAOMemoria;
import ar.edu.cursadaspoo.notificaciones.model.Mensajeria;
import ar.edu.cursadaspoo.notificaciones.model.canales.Email;
import ar.edu.cursadaspoo.notificaciones.model.canales.MensajeriaMultiple;
import ar.edu.cursadaspoo.notificaciones.model.canales.Push;
import ar.edu.cursadaspoo.notificaciones.model.canales.SMS;
import ar.edu.cursadaspoo.notificaciones.model.canales.WhatsApp;
import ar.edu.cursadaspoo.notificaciones.service.NotificacionService;
import ar.edu.cursadaspoo.notificaciones.view.ConsolaView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Punto de composición: elige las implementaciones y las inyecta. */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        List<Mensajeria> canales = List.of(
                new Email(),
                new WhatsApp(),
                new SMS(),
                new Push()
        );

        NotificacionDAO historialDAO = new NotificacionDAOMemoria();
        ConsolaView vista = new ConsolaView();
        String mensaje = "Tu pedido esta listo para retirar.";
        vista.mostrarTitulo("PARTE 2 Y 3: CANALES INTERCAMBIABLES");
        for (Mensajeria canal : canales) {
            NotificacionService servicio = new NotificacionService(canal, historialDAO);
            new NotificacionController(servicio, vista).enviar(mensaje);
        }

        vista.mostrarTitulo("DESAFIO OPCIONAL: ENVIO MULTIPLE CONCURRENTE");
        ExecutorService executor = Executors.newFixedThreadPool(canales.size());
        try {
            Mensajeria mensajeriaMultiple = new MensajeriaMultiple(canales, executor);
            NotificacionService servicioMultiple = new NotificacionService(mensajeriaMultiple, historialDAO);
            NotificacionController controladorMultiple = new NotificacionController(servicioMultiple, vista);
            controladorMultiple.enviar("Aviso enviado por todos los canales configurados.");
            controladorMultiple.mostrarHistorial();
        } finally {
            executor.shutdown();
        }
    }
}
