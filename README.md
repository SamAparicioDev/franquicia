Utilizaremos git clone para clonar el proyecto local.

Nos conectamos a mi instancia de rds postgresql : 
testdatabase.czyuk6as2clz.us-east-1.rds.amazonaws.com

puerto: 5432

nombre usuario: posgres

nombre de la base de datos: testdb 

clave: NY3ZrKadZwMPVG4toyyb

Con esto, modificamos las variables de entorno del proyecto con estos valores, luego compilamos y ejecutamos la clase Main de Spring, para probar los servicios se documento con el siguiente enlace local:

http://localhost:8080/webjars/swagger-ui/index.html#/


(En caso de no servir la conexion por medio de mi rds, para probar seria necesario crear una bd local de posgreSQL en pgAdmin y incluirla en las variables de entorno, las tablas se generan solas al ejecutar java)
