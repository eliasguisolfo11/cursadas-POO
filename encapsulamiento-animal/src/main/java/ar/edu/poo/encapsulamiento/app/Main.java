package ar.edu.poo.encapsulamiento.app;

import ar.edu.poo.encapsulamiento.domain.Animal;

/**
 * Ejecuta una demostración de encapsulamiento y modificación mediante setters.
 */
public class Main {

    public static void main(String[] args) {
        Animal primerAnimal = new Animal("Luna", "Gata");
        Animal segundoAnimal = new Animal("Toby", "Perro");

        System.out.println("Valores iniciales:");
        System.out.println(primerAnimal);
        System.out.println(segundoAnimal);

        primerAnimal.setNombre("Mora");
        primerAnimal.setEspecie("Felino");

        segundoAnimal.setNombre("Simba");
        segundoAnimal.setEspecie("León");

        System.out.println("\nValores después de utilizar los setters:");
        System.out.println(primerAnimal);
        System.out.println(segundoAnimal);
    }
}
