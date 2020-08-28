# IONIX
Ejercicios java API Rest

# Ejercicio 1 (b y c) 

Tecnologias y librerias usadas
  <li>lombok 
  <li>SpringBoot
  <li>java 8
  <li>httpclient
  <li>mockito-core
  <li>io.cucumber


### Servicio que consume una api (https://sandbox.ionix.cl/test-tecnico/search) 

Url para consumir api:
  <li>url: http://localhost:8080/api/usuarios
  <li>Params: rut (encriptado con DES)
  <li>HttpMethod: GET
    
### Información del proyecto 

<li>El endpoint de la aplicacion es de tipo Get http://localhost:8080/api/usuarios 
  el cual envia 1 paramatro "rut encriptado a consultar", el cual retorna los registros asociados al rut
<li>En la carpeta test se encuentran dos test unitarios para el "controlador" y otro para el "servicio"

# Ejercicio 2

Ejercicio 2 :
Se ocuparia criptografia TripleDES, que es un algoritmo de cifrado simétrico por bloques, basado en un triple cifrado DES.  
inicialmente se crearia un servicio que permita crer una llave publica y otra privada, la llave privada seria registrada en
una BD Mongo y la publica seria devuelta a la APP API para que pueda encriptar los datos necesarios de autorización, una vez encritada
se envia a un servicio del lado backend para que la informacion a validar encriptada se pueda desencriptar con la llave privada registrada 
en BD Mongo, a la hora de encriptar y desencriptar se debe definir un secreto para poder realizar esta definición.
