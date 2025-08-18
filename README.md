📚 ForoHub API

ForoHub es una API REST desarrollada con Spring Boot que permite la gestión de usuarios, cursos, tópicos y respuestas en un foro.
El proyecto incluye autenticación y autorización con Spring Security, además de persistencia en MySQL usando JPA/Hibernate.

🚀 Tecnologías utilizadas

Java 17+
Spring Boot 3
Spring Web
Spring Data JPA
Spring Security
Validation
MySQL
Lombok
Maven

⚙️ Requisitos previos

Antes de ejecutar el proyecto asegúrate de tener instalado:

Java 17+
Maven
MySQL

🔧 Configuración de la base de datos

Crea una base de datos en MySQL:
CREATE DATABASE forohub;

En tu archivo application.properties (o application.yml), configura la conexión:

spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶️ Ejecución del proyecto
mvn spring-boot:run


🔐 Seguridad y Autenticación

El proyecto usa JWT Bearer Tokens:

Registro de usuario
Endpoint público para crear nuevos usuarios:
POST /auth/register

Inicio de sesión
Endpoint público que devuelve un token JWT:
POST /auth/login
{
  "username": "tu_usuario",
  "password": "tu_password"
}
Respuesta esperada:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}

Acceso a recursos protegidos
Todos los endpoints que no sean de registro o login requieren el token JWT en el header de la petición:
Authorization: Bearer <token>

