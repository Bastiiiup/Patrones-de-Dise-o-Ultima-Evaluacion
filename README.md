# Patrones-de-Dise-o-Ultima-Evaluacion
App para el registro de asistencia en una sede.
# Sistema de Asistencia para Sede

## Descripcion del Sistema

Este sistema controla el ingreso y salida de personas (empleados y visitantes) a una sede, y gestiona su asistencia a eventos. Su objetivo es mantener un registro claro y centralizado de la presencia de individuos, facilitar la creacion de personas y eventos, y tambien se mostrara un registro para ver los datos de ejemplo y la demostracion de los patrones.

## Patrones de Diseno Aplicados y su Justificacion

El proyecto utiliza 4 patrones de diseno para mejorar su estructura y flexibilidad:

### 1. Singleton (Creacional)

* **Por que:** Asegura que solo exista **una unica instancia** del `SistemaAsistencia` para gestionar todos los datos. Esto evita confusiones y garantiza la coherencia de la informacion global (personas, eventos, registros).
* **Como y Donde:** La clase `SistemaAsistencia` (en `sistema`) tiene un constructor privado y un metodo estatico `getInstancia()` que siempre devuelve la misma instancia. En `main.java`, se accede a ella con `SistemaAsistencia.getInstancia()`.

### 2. Prototype (Creacional)

* **Porque:** Permite crear nuevos objetos (`Persona`, `Evento`) copiando un objeto existente (usando una plantilla). Esto es mas eficiente que crear un objeto desde cero, ya que se demorarian mas.
* **Como y Donde:** Se usa una interfaz `Clonable` (en `model`) que implementan `Persona` y `Evento` para definir el metodo `clonar()`. En `main.java`, se demuestra el uso del metodo `clonar()` al obtener y manipular un objeto clonado de los datos de ejemplo.

### 3. Bridge (Estructural)

* **Por que:** Separa el "que" de las notificaciones (el mensaje) del "como" se envian (el medio). Asi, puedes cambiar como envias una notificacion (ej., a consola o a email) sin modificar el contenido del mensaje.
* **Como y Donde:** Las clases `Notificacion` y `NotificacionEvento` (en `notificacion`) definen la abstraccion del mensaje. Las clases `Notificador` y `NotificadorConsola` (en `notificacion`) definen la implementacion del envio. Una `Notificacion` tiene una referencia a un `Notificador`, permitiendo intercambiar el metodo de envio. La notificacion se activara automaticamente al cargar los eventos de ejemplo.

### 4. Iterator (Comportamiento)

* **Porque:** Proporciona una forma estandar y limpia de recorrer colecciones de objetos (listas de personas, eventos, registros) sin necesidad de saber como estan almacenados internamente. Esto mantiene el codigo que muestra los datos simple y desacoplado de la estructura interna.
* **Como y Donde:** Se utiliza implicitamente a traves de las listas de Java (`java.util.List`). En `main.java`, al mostrar los listados de personas, eventos o registros de los datos de ejemplo, se usan bucles `for-each` que se basan en el Iterator interno de las listas:
    ```java
    for (Persona persona : sistema.getPersonasRegistradas()) {
        System.out.println(persona);
    }
    ```

## Instrucciones de Uso

### Compilacion y Ejecucion

1.  **Descarga** el proyecto.
2.  **Abre** el proyecto en tu IDE netbeans.
3.  **Ejecuta** la clase `app.main`.

Alternativamente, desde la **linea de comandos**:

1.  Navega a la carpeta raiz del proyecto (`SistemaSede`).
2.  **Compila**:
    ```bash
    javac -d bin src/app/*.java src/model/*.java src/notificacion/*.java src/sistema/*.java
    ```
3.  **Ejecuta**:
    ```bash
    java -cp bin app.main
    ```
Al ejecutar, veras la carga de los datos de ejemplo y la demostracion en consola de como los patrones estan siendo utilizados.
