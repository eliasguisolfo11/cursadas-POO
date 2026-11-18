# Análisis conceptual

## Parte 1 — diseño inicial

**¿Qué sucede si queremos enviar mensajes por WhatsApp?**

La versión inicial solo sabe enviar por correo. Habría que agregar una implementación de WhatsApp y cambiar el servicio principal para que la use.

**¿Qué clase deberíamos modificar?**

`NotificacionService`, porque su código conoce directamente a `EmailService`. En el ejemplo también crea esa dependencia dentro de la clase.

**¿Qué problema presenta este diseño?**

Hay acoplamiento fuerte: la política de notificación queda ligada a un proveedor concreto. Cada nuevo canal obliga a cambiar la lógica existente, dificulta intercambiar implementaciones y complica aislar el servicio para probarlo.

## Parte 4 — conceptos

### ¿Qué establece el principio DIP de SOLID?

DIP propone que las partes de alto nivel, que contienen las reglas principales del sistema, no dependan directamente de detalles concretos. Ambas deben apoyarse en abstracciones. También indica que esas abstracciones no deberían depender de los detalles; los detalles deben ajustarse a ellas.

### ¿Qué diferencia existe entre depender de una clase concreta y depender de una interfaz?

Una clase concreta fija qué implementación se usa y conoce cómo está construida. Una interfaz expresa el contrato que el servicio necesita. Si el servicio depende de ese contrato, una implementación puede reemplazarse por otra que cumpla el mismo método, sin cambiar la lógica del servicio.

### ¿Qué es la inyección de dependencias?

Es entregar a un objeto las dependencias que necesita desde afuera, en lugar de hacer que las busque o las cree por sí mismo. En este trabajo, `Main` crea un canal y se lo pasa a `NotificacionService`.

### ¿Qué ventajas tiene utilizar inyección por constructor?

Hace visible qué necesita el objeto para funcionar y permite validar esas dependencias al crearlo. El servicio queda listo para usarse desde el inicio y no depende de setters ni de un estado incompleto. Además, cambiar el canal consiste en pasar otra implementación.

### ¿Por qué el diseño desarrollado facilita agregar nuevas funcionalidades?

Cada canal nuevo se incorpora como una implementación independiente de `Mensajeria`. `NotificacionService` trabaja con ese contrato, así que no necesita conocer ni distinguir Email, WhatsApp, SMS o Push. El punto de composición decide qué objeto conectar.

### ¿Qué relación existe entre DIP y Open/Closed (OCP)?

DIP hace posible que la lógica central trabaje contra contratos estables y OCP recomienda que esa lógica pueda extenderse sin editarla para cada variante. Al agregar una implementación nueva de `Mensajeria`, se amplían las opciones del sistema y se conserva el código de `NotificacionService`.

## Persistencia con DAO

`NotificacionDAO` concentra las operaciones para guardar y consultar notificaciones. `NotificacionService` depende de esa interfaz, no del almacenamiento concreto. En esta entrega se usa `NotificacionDAOMemoria` para que el historial pueda demostrarse sin configurar una base de datos; los registros se pierden al cerrar el programa. En un sistema real, se podría agregar un DAO SQL que implemente el mismo contrato.
