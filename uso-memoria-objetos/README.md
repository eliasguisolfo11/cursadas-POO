# Trabajo Práctico: Uso de Memoria en Objetos e Instancias

Proyecto didáctico en JavaScript y Node.js. Incluye el análisis conceptual,
ejemplos ejecutables de identidad de objetos y un `Config` Singleton.

## Requisitos y ejecución

- Node.js 18 o posterior.
- No requiere instalar dependencias.

```bash
npm start
```

## Estructura

```text
uso-memoria-objetos/
├── package.json
├── README.md
└── src/
    ├── app/
    │   └── main.js
    ├── config/
    │   └── Config.js
    └── domain/
        ├── Dog.js
        └── User.js
```

El punto de entrada solo ejecuta las demostraciones. `Dog` y `User` modelan
entidades sencillas; `Config` mantiene la única instancia de configuración.

## Parte 1: análisis de memoria

### ¿Qué pasa al ejecutar `new Dog("Rex")`?

JavaScript crea una nueva instancia de `Dog`, inicializa su propiedad `name` y
devuelve una referencia a ese objeto. El entorno de ejecución administra la
memoria de los objetos y puede recuperarla mediante el recolector de basura
cuando ya no hay referencias alcanzables hacia ellos.

### ¿Por qué `dog1` y `dog2` ocupan espacios distintos?

Cada expresión `new Dog("Rex")` crea una instancia con identidad propia. Aunque
ambas tengan el mismo nombre, las referencias apuntan a objetos diferentes:
`dog1 === dog2` da `false`. Cambiar una instancia no cambia la otra.

### String primitivo frente a `new String`

```js
const nombre = "fede";
const nombre2 = new String("fede");
```

`nombre` contiene un valor primitivo de tipo `string`. `nombre2` es un objeto
envoltorio de tipo `String`, con identidad propia y trabajo adicional para el
entorno. Por eso `typeof nombre` da `"string"` y `typeof nombre2` da `"object"`.
Para texto normal conviene usar literales, sin `new String(...)`.

### Esquema conceptual: stack y heap

```text
Marco de ejecución (stack conceptual)           Heap administrado
┌─────────────────────────────┐                 ┌─────────────────────────┐
│ nombre  = "fede" (primitivo)│                 │ String { "fede" }       │
│ nombre2 ─────── ref #S1 ─────────────────────►│ objeto envoltorio       │
│ dog1    ─────── ref #D1 ─────────────────────►│ Dog { name: "Rex" }     │
│ dog2    ─────── ref #D2 ─────────────────────►│ Dog { name: "Rex" }     │
└─────────────────────────────┘                 └─────────────────────────┘
```

Es una simplificación para aprender: ECMAScript no obliga a que cada valor se
guarde físicamente en una región llamada stack o heap. Los motores pueden
optimizar la representación interna. El esquema muestra la diferencia útil
entre valores primitivos y referencias a objetos.

## Parte 2: malas prácticas del ejemplo

```js
const nombre = new String("Juan");
const apellido = new String("Perez");
const nombreCompleto = nombre + apellido;
const edad = new Number(30);
const activo = new Boolean(true);
```

- `new String`, `new Number` y `new Boolean` crean objetos envoltorio para
  valores que normalmente conviene guardar como primitivos.
- Los objetos agregan identidad y asignaciones innecesarias. Además, una
  instancia como `new Boolean(false)` es un objeto y, por lo tanto, truthy en
  una condición.
- La concatenación produce `"JuanPerez"`, sin espacio entre nombre y apellido.
- Los constructores envoltorio pueden causar comparaciones inesperadas, por
  ejemplo, al comparar identidad de objetos.

Una versión idiomática sería:

```js
const nombre = "Juan";
const apellido = "Perez";
const nombreCompleto = `${nombre} ${apellido}`;
const edad = 30;
const activo = true;
```

## Parte 3: refactor de `User`

La clase conserva una instancia por usuario, pero almacena el nombre como una
cadena primitiva:

```js
class User {
  constructor(name) {
    this.name = name;
  }
}
```

Así se evita crear un objeto `String` por cada `User`. `user1` y `user2` siguen
siendo instancias distintas porque representan objetos diferentes; compartir el
valor primitivo del nombre no los convierte en el mismo usuario.

## Parte 4: instancias y duplicación

`new Dog("Rex")` dos veces crea dos instancias distintas. El programa lo muestra
comparando referencias y cambiando el nombre de `dog1`: `dog2` conserva `"Rex"`.
No hay que fusionar objetos solo porque sus atributos coincidan; su identidad
puede ser importante. Si una aplicación crea muchísimos objetos inmutables y
repetidos, puede evaluar reutilización o Flyweight, midiendo antes el costo y
considerando si compartir identidad es correcto para el dominio.

## Parte 5: Singleton `Config`

`Config.getInstance()` crea la instancia la primera vez y devuelve esa misma
referencia en las llamadas siguientes. La clase protege el constructor con un
token interno y expone `url` y `port` como propiedades de solo lectura.

```js
const configA = Config.getInstance({ url: "https://api.ejemplo.com", port: 8080 });
const configB = Config.getInstance();

console.log(configA === configB); // true
```

La demostración también muestra que las opciones de llamadas posteriores no
reemplazan la configuración ya creada. Esta instancia es única dentro del
módulo cargado en un proceso de Node.js; procesos o contextos de ejecución
separados tienen su propia memoria.

Un Singleton puede centralizar configuración compartida, aunque su estado
global puede dificultar pruebas y aislamiento. Conviene usarlo cuando la
aplicación realmente necesita una única configuración por proceso.

## Salida esperada

Al ejecutar `npm start`, se muestran los tipos primitivos, que `dog1` y `dog2`
son objetos distintos, que modificar uno no modifica el otro y que las llamadas
a `Config.getInstance()` comparten la misma referencia.
