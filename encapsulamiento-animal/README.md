# POO: Encapsulamiento con `Animal`

Ejercicio breve de Programación Orientada a Objetos. La clase `Animal` mantiene
`nombre` y `especie` como atributos privados, recibe sus valores iniciales por
constructor y permite modificarlos mediante `setNombre` y `setEspecie`.

## Requisitos

- JDK 17 o posterior.
- Maven 3.9 o posterior.

## Ejecutar

Desde esta carpeta:

```bash
mvn clean package
java -cp target/classes ar.edu.poo.encapsulamiento.app.Main
```

La salida muestra dos animales antes y después de modificar sus propiedades con
los setters.

## Estructura

```text
encapsulamiento-animal/
├── pom.xml
├── README.md
├── .gitignore
└── src/main/java/ar/edu/poo/encapsulamiento/
    ├── app/
    │   └── Main.java
    └── domain/
        └── Animal.java
```

La clase del dominio está separada del punto de entrada. Esta consigna no
requiere persistencia ni un flujo de interfaz/controlador, por eso no agrega
capas DAO o MVC que no aportarían comportamiento al ejercicio.
