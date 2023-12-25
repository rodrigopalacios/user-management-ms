# user-management-ms
User Management Based Spring Boot 

Microservicio User Management construido con Spring boot versión 3.2.

# Tecnologías
Se utilizan las siguientes técnologías.
- Spring Security implementando Java Web Token (JWT)
- Lombok
- JPA con Spring Data, hibernate y base de datos en memoria H2.
- JSR303, para la validación de atributos del request, usando hibernate validator
- Swagger 3, para la documentación del api.

# Swagger
Se puede acceder a ladocumentación del api en: 
- http://localhost:8080/swagger-ui/index.html

Se implementó openapi para agregar más detalles a la documentación generada por swagger.

# Java Web Token(JWT)
Se han configurado algunas urls las cuales no requieren que el usuario esté autenticado, por lo tanto, no es necesario que se envíe un token header. (Authorization Bearer)
- "v3/api-docs/**"  y "/swagger-ui/**" necesarios para mostrar correctamente la documentación de swagger.
- "/api/auth/**" necesario para poder consumir el servicio /api/auth/getToken.

Se ha habilitado la restricción de acceso al api en la URL /api/user/** para usuarios authenticados.Es decir, es necesario enviar un token de sesión.

# USER MANAGEMENT API
1 - GENERAR UN TOKEN DE SESION
Para poder hacer uso del api, primero generar un token de sesión, consumiendo el servicio /api/auth/getToken. Más detalle en http://localhost:8080/swagger-ui/index.html#/security-controller/getToken
La aplicación carga unos usuarios por defecto al inicio.
Se puede generar un token con el siguiente request:
URL: http://localhost:8080/api/auth/getToken
METHOD: POST
JSON BODY:
{
"username": "Nombre Apellido1"
,"password":"1234561"
}

Se obtiene un JSON RESPONSE similar al siguiente:
{"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOb21icmUgQXBlbGxpZG8xIiwiaWF0IjoxNzAzNDYyODk2LCJleHAiOjE3MDM0NjQ2OTZ9.hgBbUsRJPHGReIVLyk1_kBGiqMQvL-dbah09vyfPlGY"}

2 - Consumir alguno de los metodos del API.
Revisar las operaciones disponibles en:
http://localhost:8080/swagger-ui/index.html






