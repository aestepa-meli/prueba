# Prueba Mercado Libre
Jhon Eider Murillo Usuga
[![](https://http2.mlstatic.com/static/org-img/homesnw/mercado-libre.png?v=2)](https://http2.mlstatic.com/static/org-img/homesnw/mercado-libre.png?v=2)
## A continuación se daran los detalles asociados a la solución del reto propuesto

###Ejecución con Docker
-  Clonar el repositorio.
- Es necesario una versión de maven 3.+ para realizar la compilación.
- Ejecutar el siguiente comando **mvn package -PDesarrolloDocker  docker-compose up --build -d**, (No estoy seguro si en el archivo se visualiza bien pero luego de el up son 2 guiones -)él se encargará de levantar la base de datos y el proyecto, puede tardar un poco ya que el proyecto creará las tablas.
- El proyecto estará expuesto en el puerto 8080.


###Se utilizó
- Java 8
- Maven 3.+
- Spring Boot en su versión 2.6.1
- GitHub (Este es logico por donde estan leyendo esto jeje)
- Postman para realizar pruebas funcionales (Tanto la colección como el environment fueron enviados a los correos indicados de Sebastian y Andres )
- Docker para realizar despliegue, con su respectivo docker-compose
- Se utilizó una imagen de docker (que está especificada en el docker-compose) de mysql como motor de base de datos, la razón de realizar esto es debido a que es una de las tecnologías indicadas y también se puede explorar un poco mejor el tema de docker
- Se agrego sistema de logs con log4j2 en su version 2.16 ya que se reportaron vulnerabilidades criticas en las anteriores.

###Observaciones
- Se utilizan prácticas de desarrollo en general dentro del estándar de Spring Boot, para facilitar la ejecución de proyectos y mantener la filosofía de Spring Boot de enfoque al desarrollo.
- Se utiliza estándar de nomenclatura CamelCase que es el más común en java
- Se realizan los respectivos comentarios siguiendo el estandar basico de JavaDoc (Para visualizar el Javadoc ejecutar el comando **mvn javadoc:javadoc** dentro de la carpeta target se generarán los archivos necesarios para visualizarlo )
- Se mantiene una estructura de delegación de responsabilidades donde no interfieren entre sí las capas Controller, Service,Persistencia, más que para transmitir datos entre sí de acuerdo a sus funciones.
- Para el manejo de errores se utiliza un ExceptionHandler para realizar una captura de errores inesperados y evitar mostrar mensajes de error de código que pueden ser sensibles a ataques.
- Todo el flujo de el manejo de Git puede verse en el mismo GitHub o una vez clonado ejecutando el comando **gitk** (por lo menos en windows y linux).
- Se manejaron testing del código asociado a los puntos de el reto utilizando Junit y Mockito.
- Se utilizaron clases de utilidad para reducción de código.
- Se realizó capa de seguridad como extra ya que normalmente el uso de apis requieren esta capa en etapas futuras o desde el comienzo.
- En conjunto a esto se realiza la configuración de campos de auditoría para tener un registro de que usuarios alteran los valores en bd.
- Se realizó configuración de swagger para documentación del api (Para acceder ingresar http://localhost:8080/swagger-ui.html una vez allí ingresar en el buscador /v3/api-docs y se podrá visualizar la documentación de los controladores)
- Uso de DTO para evitar pasos directos de entidades al cliente.

####Consideraciones  para las pruebas
- Como se podrá ver en la colección de postman todas las fechas se manejan en formato **yyy-MM-dd HH:mm:ss** , se utilizó este formato por comodidad simplemente, puede ser cambiado en los DTO de ser necesario.
- Como existe capa de seguridad se habilitaron 2 end-point para el registro de usuarios y para el login, se pueden encontrar en la colección de postman como **Register User** y **Login** respectivamente.
- Cuando se realice un login se debe agregar el token devuelto al environment en el parámetro **TOKEN** , los demás request están configurados para leerlo.
- Para el caso de las solicitudes de préstamo el identificador único que se le muestra al usuario no es el id de la tabla en sí pues esto no es una práctica adecuada, se genera un hash único que es el que se le muestra al usuario y también es almacenado en base de datos.
- Se utilizan clases para realizar la transformación de DTO a entidad y Entidad a DTO en caso de ser necesario.
- Para tener una mejor relación entre los datos se establece una relación de uno a muchos entre Solicitudes de préstamo y Pago así se puede en un futuro tener un mejor control de la relación de datos entre ellos.
- Así pues el registro de pagos se hace necesario indicar la solicitud de préstamo a la que hace referencia para esto al JSON se le añade un campo loanId que hace referencia al identificador único antes mencionado.
Ejemplo :  
{
	"payment": "missed",
	"date": "2021-09-05 02:18:18",
	"amount": 85.60,
	"loanId": "{{LOAN_ID}}"
}
- Para obtener el balance de la deuda también se recibe como path variable el identificador único mencionado anteriormente y como RequestParam el fecha solicitada
Ejemplo :  **{{URL_BASE}}/payment/pendingDebt/{{LOAN_ID}}?date=2021-09-04 21:18:18**


###Agradecimiento
- Finalmente Agradezco la oportunidad de estar en el proceso, espero no haberme salido mucho de lo que tenían pensado revisar con el reto, y espero haber mostrado un poco los conocimientos, quedo atento, Feliz dia
