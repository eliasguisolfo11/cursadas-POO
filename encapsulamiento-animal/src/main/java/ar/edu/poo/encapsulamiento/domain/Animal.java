package ar.edu.poo.encapsulamiento.domain;

/**
 * Representa un animal y encapsula sus datos para controlar su acceso.
 */
public class Animal {

    private String nombre;
    private String especie;

    public Animal(String nombre, String especie) {
        this.nombre = nombre;
        this.especie = especie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return "Animal{nombre='%s', especie='%s'}".formatted(nombre, especie);
    }
}
