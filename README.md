# QualifyGym Microservices

Sistema de microservicios para la aplicación QualifyGym desarrollado con Spring Boot y Spring Cloud.

## Estructura del Proyecto

Este proyecto contiene 4 microservicios principales:

- **microservice-usuarios**: Gestión de usuarios, autenticación y roles
- **microservice-productos**: Gestión de productos y categorías
- **microservice-ordenes**: Gestión de órdenes de compra
- **microservice-pagos**: Gestión de pagos

## Requisitos Previos

- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Configuración

### Bases de Datos

Cada microservicio requiere su propia base de datos MySQL:

```sql
CREATE DATABASE db_usuarios;
CREATE DATABASE db_productos;
CREATE DATABASE db_ordenes;
CREATE DATABASE db_pagos;
```

### Configuración de Propiedades

Cada microservicio tiene su archivo `application.yml` en `src/main/resources/`. Asegúrate de configurar:

- URL de la base de datos
- Usuario y contraseña de MySQL
- Puerto del servidor (si es necesario)

## Instalación y Ejecución

### Compilar el proyecto completo

```bash
mvn clean install
```

### Ejecutar cada microservicio

Desde la raíz del proyecto:

```bash
# Microservicio de Usuarios
cd microservice-usuarios
mvnw spring-boot:run

# Microservicio de Productos
cd microservice-productos
mvnw spring-boot:run

# Microservicio de Órdenes
cd microservice-ordenes
mvnw spring-boot:run

# Microservicio de Pagos
cd microservice-pagos
mvnw spring-boot:run
```

## Endpoints Principales

### Microservicio de Usuarios (Puerto por defecto: 8081)

- `GET /api/v1/usuarios/listar` - Listar todos los usuarios
- `GET /api/v1/usuarios/{id}` - Obtener usuario por ID
- `POST /api/v1/usuarios` - Crear nuevo usuario
- `PUT /api/v1/usuarios/{id}` - Actualizar usuario
- `DELETE /api/v1/usuarios/{id}` - Eliminar usuario
- `POST /api/v1/usuarios/login` - Iniciar sesión

### Microservicio de Productos (Puerto por defecto: 8082)

- `GET /api/v1/productos/listar` - Listar todos los productos
- `GET /api/v1/productos/activos` - Listar productos activos
- `GET /api/v1/productos/{id}` - Obtener producto por ID
- `POST /api/v1/productos` - Crear nuevo producto
- `PUT /api/v1/productos/{id}` - Actualizar producto
- `DELETE /api/v1/productos/{id}` - Eliminar producto

### Microservicio de Órdenes (Puerto por defecto: 8083)

- `GET /api/v1/ordenes/listar` - Listar todas las órdenes
- `GET /api/v1/ordenes/{id}` - Obtener orden por ID
- `POST /api/v1/ordenes` - Crear nueva orden
- `PUT /api/v1/ordenes/{id}` - Actualizar orden
- `DELETE /api/v1/ordenes/{id}` - Eliminar orden

### Microservicio de Pagos (Puerto por defecto: 8084)

- `GET /api/v1/pagos/listar` - Listar todos los pagos
- `GET /api/v1/pagos/{id}` - Obtener pago por ID
- `POST /api/v1/pagos` - Crear nuevo pago
- `PUT /api/v1/pagos/{id}` - Actualizar pago
- `DELETE /api/v1/pagos/{id}` - Eliminar pago

## Documentación API

Cada microservicio incluye documentación Swagger disponible en:

- Usuarios: `http://localhost:8081/swagger-ui.html`
- Productos: `http://localhost:8082/swagger-ui.html`
- Órdenes: `http://localhost:8083/swagger-ui.html`
- Pagos: `http://localhost:8084/swagger-ui.html`

## Tecnologías Utilizadas

- **Spring Boot** 3.3.12
- **Spring Cloud** 2023.0.5
- **Spring Data JPA** - Persistencia de datos
- **Spring Security** - Autenticación y autorización
- **MySQL** - Base de datos
- **Lombok** - Reducción de código boilerplate
- **Jakarta Validation** - Validación de datos
- **Swagger/OpenAPI** - Documentación de API
- **Maven** - Gestión de dependencias

## Estructura de Cada Microservicio

```
microservice-[nombre]/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/microservice/[nombre]/
│   │   │       ├── Microservice[Nombre]Application.java
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       └── application.yml
│   └── test/
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## Testing

Para ejecutar los tests de cada microservicio:

```bash
cd microservice-[nombre]
mvnw test
```

## Contribución

Este proyecto forma parte de QualifyGym - Grupo 13.

## Licencia

Este proyecto es de uso educativo.

