package ar.edu.cursadaspoo.notificaciones.model;

import java.time.Instant;
import java.util.Objects;

/** Registro de una solicitud de notificación enviada. */
public record Notificacion(String mensaje, Instant creadaEn) {

    public Notificacion(String mensaje) {
        this(mensaje, Instant.now());
    }

    public Notificacion {
        Objects.requireNonNull(mensaje, "mensaje no puede ser null");
        Objects.requireNonNull(creadaEn, "creadaEn no puede ser null");
    }
}
