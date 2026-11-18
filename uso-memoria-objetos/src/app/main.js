import { Config } from "../config/Config.js";
import { Dog } from "../domain/Dog.js";
import { User } from "../domain/User.js";

function mostrarTitulo(titulo) {
  console.log(`\n${titulo}`);
  console.log("-".repeat(titulo.length));
}

function demostrarPrimitivos() {
  mostrarTitulo("Primitivos y objetos envoltorio");

  const nombre = "fede";
  const edad = 30;
  const activo = true;

  console.log(`typeof nombre: ${typeof nombre}`);
  console.log(`typeof edad: ${typeof edad}`);
  console.log(`typeof activo: ${typeof activo}`);
  console.log("Se usan valores primitivos, sin crear String, Number o Boolean.");
}

function demostrarUsuarios() {
  mostrarTitulo("Refactor de User: dos instancias y nombres primitivos");

  const user1 = new User("Ana");
  const user2 = new User("Ana");

  console.log(`user1 y user2 son la misma instancia: ${user1 === user2}`);
  console.log(`Nombres: ${user1.name}, ${user2.name}`);
}

function demostrarInstancias() {
  mostrarTitulo("Dos perros con los mismos datos");

  const dog1 = new Dog("Rex");
  const dog2 = new Dog("Rex");

  console.log(`dog1 y dog2 son la misma instancia: ${dog1 === dog2}`);
  console.log(`Antes del cambio: dog1=${dog1.name}, dog2=${dog2.name}`);

  dog1.name = "Max";
  console.log(`Después del cambio: dog1=${dog1.name}, dog2=${dog2.name}`);
}

function demostrarSingleton() {
  mostrarTitulo("Singleton Config");

  const configDelServidor = Config.getInstance({
    url: "https://api.ejemplo.com",
    port: 8080,
  });
  const configDeOtroModulo = Config.getInstance({
    url: "https://otra-url-ignorada.com",
    port: 9999,
  });

  console.log(
    `Ambas referencias apuntan a la misma instancia: ${configDelServidor === configDeOtroModulo}`,
  );
  console.log(`URL: ${configDeOtroModulo.url}`);
  console.log(`Puerto: ${configDeOtroModulo.port}`);
}

console.log("TP: Uso de memoria en objetos e instancias");
demostrarPrimitivos();
demostrarUsuarios();
demostrarInstancias();
demostrarSingleton();
