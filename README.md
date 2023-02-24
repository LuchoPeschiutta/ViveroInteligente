
# PROYECTO VIVERO INTELIGENTE

## Introducción

Este proyecto persigue la creación de una aplicación que monitorice e informe el estado de un vivero inteligente.  
En un principio se busca sencillamente monitorizar las variables que afectan el desarrollo de las plantas (mediante el uso de sensores de humedad, temperatura y luminosidad)
y contrastarlos con los valores adecuados para la misma.

## Áreas de aplicación

Si bien el proyecto está orientado a la automatización y gestión de un vivero, puede ser adaptado correctamente al uso de un jardín doméstico o incluso a un laboratorio de agronomía, para la
realización de ensayos.

## Funcionamiento

La aplicación está pensada para desplegarse en un entorno en el cual se cuente con diversos sensores que monitoreen humedad, temperatura y luminosidad. Puede contar con multitud de ellos,
y cada uno o un grupo de ellos puede estar relacionado con una ubicación o rango de ubicaciones específicas en el vivero. De esta manera, podemos gestionar plantas que requieren diferentes
condiciones, ubicándolas en lugares diferentes.  
La aplicación cuenta con un catálogo de plantas en el cual se almacenan las etapas que atraviesa cada una durante su ciclo de vida y los rangos de valores ambientales adecuados para cada una.
Al plantar una nueva planta en una ubicación determinada del vivero, se debe seleccionar la planta del catálogo y relacionarla con la ubicación. De esta manera la aplicación comenzará a 
comparar los datos censados en tiempo real con los valores adecuados para la planta en cada etapa de su ciclo. Los valores (tanto los márgenes entre los cuales es apropiado que permanezca, así
como los datos censados) son mostrados en la ventana de la aplicación, permitiendo que se tomen medidas para corregir condiciones inadecuadas. 

## Estado actual de desarrollo

La vista de la aplicación cuenta con 3 paneles:

* Un panel de plantas en forma de tabla donde se listan las plantas con sus respectivos datos.
* Uno donde se puede seleccionar una planta dentro del catálogo disponible y añadirla a una ubicación libre.
* Finalmente uno donde se puede remover una planta de una ubicación dada.

Dado que hasta el momento del despliegue no se podrá interactuar con el hardware encargado de entregar los nuevos datos, en el panel de plantas se encuentran tres botones que
permiten hacer simulaciones de la adquisición de datos, del paso del tiempo y finalmente un estado de simulación continuo.  

Las plantas cuentan con 4 etapas en su ciclo de vida: Germinación, Crecimiento, Vegetativa y Reproducción. Dadas estas etapas se distinguen dos tipos de plantas diferentes:
* Las no perennes, que durante su ciclo de vida sus etapas solo se cumplen una vez antes de que la planta perezca.
* Las perennes, que tras atravesar las etapas de Germinación y Crecimiento comienzan un ciclo donde sus etapas Vegetativa y Reproducción se alternan indefinidamente.

El catálogo de plantas se almacena en una base de datos SQLite y por el momento contiene solo algunas plantas de muestra de cada tipo, cuyo contenido es puramente demostrativo.

## Perspectivas a futuro

Algunas de las opciones inmediatas para mejorar la aplicación son:

* Implementar un log donde se registre cada vez que una condición ambiental inadecuada se produce para una planta. Añadir un panel donde se pueda acceder a él desde la vista.

Algunas de las opciones a largo plazo y dependientes del dispositivo en el cual se produzca el despliegue:

* Implementar un sistema de respuesta automatizado frente al surgimiento de eventos determinados. Accionamiento de sistemas de riego, control de termostato, control de iluminación.

## Datos del desarrollo

Dado que parte de la motivación del proyecto es generar una aplicación que haga uso y demostración de diferentes técnicas de POO y otras skills en general, se generó un [archivo más detallado](./STT.md).  
