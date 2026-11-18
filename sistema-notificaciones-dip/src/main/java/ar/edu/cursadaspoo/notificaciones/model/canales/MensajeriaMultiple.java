package ar.edu.cursadaspoo.notificaciones.model.canales;

import ar.edu.cursadaspoo.notificaciones.model.Mensajeria;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Composición opcional que distribuye el mismo mensaje por varios canales
 * concurrentemente. También se puede inyectar en NotificacionService.
 */
public final class MensajeriaMultiple implements Mensajeria {

    private final List<Mensajeria> canales;
    private final Executor executor;

    public MensajeriaMultiple(List<Mensajeria> canales, Executor executor) {
        this.canales = List.copyOf(Objects.requireNonNull(canales, "canales no puede ser null"));
        if (this.canales.isEmpty()) {
            throw new IllegalArgumentException("Debe configurarse al menos un canal.");
        }
        this.executor = Objects.requireNonNull(executor, "executor no puede ser null");
    }

    @Override
    public void enviar(String mensaje) {
        CompletableFuture<?>[] envios = canales.stream()
                .map(canal -> CompletableFuture.runAsync(() -> canal.enviar(mensaje), executor))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(envios).join();
    }
}
