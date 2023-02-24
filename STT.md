
# SKILLS, TECNOLOGÍAS Y TÉCNICAS APLICADOS AL PROYECTO

## Patrones de diseño POO

### Strategy

Se utilizó la composición en la clase vivero, para almacenar la clase abstracta Planta. Luego cada subclase de Planta lleva a cabo su propia implementación del método **paso()**, que define
de que manera gestionará la planta el avance y la sucesión de las etapas.

### Factory

La clase FabricaPlantas es la encargada de crear las nuevas plantas solicitadas. Se encarga de hacer la búsqueda en el catálogo, decidir la subclase necesaria y rellenar la nueva planta con 
los objetos Etapa correspondientes.

### MVC

Se implementó el modelo MVC para desacoplar el modelo (clase Vivero) de la vista (clase Vista) y el controlador (clase Controlador). La vista corre sobre el EDT del paquete swing, encargado
de actualizar los paneles y capturar las acciones del usuario. Los eventos asociados con modificaciones del modelo o actualización de los datos generan nuevos hilos que llaman a los métodos del
controlador (de esta forma el EDT está libre y la ventana de aplicación no se cuelga). El controlador es el encargado de recibir todos los eventos (generados en la vista, los sensores o la 
simulación) y transformarlos en acciones sobre el modelo, o modificaciones y actualización de la vista. Finalmente el modelo contiene toda la lógica para el funcionamiento correcto del modelo
de vivero.

## Bases de Datos y SQL

Se utilizó SQLite para crear la base de datos que almacena el catálogo de plantas. Dentro de la clase FabricaPlantas se realizan las queries necesarias para recuperar los datos y
crear los nuevos objetos de la clase Planta.

![Diagrama de la DB CatalogoPlantas](./DB_Diagram.pmg)

## Uso de Multithreading y sincronización

Se utiliza un hilo diferente para la ejecución de la simulación continua. Los eventos que se generan en la vista y requieren acciones sobre el controlador delegan dichas tareas a nuevos hilos.
La clase Controlador utiliza la exclusión mutua para evitar condiciones de carrera, inconsistencias u otros problemas derivados de la concurrencia.

## Otros

### Uso de swing y JFrame

La vista esta implementada a través swing y JFrame.

### Uso de JSON

Se utilizó el formato JSON como interfaz de paso de información entre los diferentes niveles del modelo y la vista.

### Documentación mediante Javadoc

Todas las clases y métodos están documentados mediante el estándar javadoc.