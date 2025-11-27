# Configuración de Puertos y Bases de Datos

## 📍 Ubicación de la Configuración

La configuración de **puertos** y **nombres de bases de datos** se encuentra en los archivos `application.yml` de cada microservicio:

```
QualifyGym-Microservices/
├── microservice-usuarios/
│   └── src/main/resources/application.yml  ← AQUÍ
├── microservice-productos/
│   └── src/main/resources/application.yml   ← AQUÍ
├── microservice-ordenes/
│   └── src/main/resources/application.yml   ← AQUÍ
└── microservice-pagos/
    └── src/main/resources/application.yml   ← AQUÍ
```

---

## 🔧 Configuración por Microservicio

### 1. Microservicio de Usuarios

**Archivo:** `microservice-usuarios/src/main/resources/application.yml`

```yaml
server:
  port: 8081  ← PUERTO DEL MICROSERVICIO

spring:
  application:
    name: msvc-usuarios
  datasource:
    url: jdbc:mysql://localhost:3306/db_usuarios  ← NOMBRE DE LA BASE DE DATOS
    username: root
    password:  ← CONTRASEÑA DE MYSQL (cambiar si es necesario)
```

**Resumen:**
- **Puerto:** `8081`
- **Base de Datos:** `db_usuarios`
- **URL Swagger:** http://localhost:8081/swagger-ui.html

---

### 2. Microservicio de Productos

**Archivo:** `microservice-productos/src/main/resources/application.yml`

```yaml
server:
  port: 8082  ← PUERTO DEL MICROSERVICIO

spring:
  application:
    name: msvc-productos
  datasource:
    url: jdbc:mysql://localhost:3306/db_productos  ← NOMBRE DE LA BASE DE DATOS
    username: root
    password:  ← CONTRASEÑA DE MYSQL (cambiar si es necesario)
```

**Resumen:**
- **Puerto:** `8082`
- **Base de Datos:** `db_productos`
- **URL Swagger:** http://localhost:8082/swagger-ui.html

---

### 3. Microservicio de Órdenes

**Archivo:** `microservice-ordenes/src/main/resources/application.yml`

```yaml
server:
  port: 8083  ← PUERTO DEL MICROSERVICIO

spring:
  application:
    name: msvc-ordenes
  datasource:
    url: jdbc:mysql://localhost:3306/db_ordenes  ← NOMBRE DE LA BASE DE DATOS
    username: root
    password:  ← CONTRASEÑA DE MYSQL (cambiar si es necesario)
```

**Resumen:**
- **Puerto:** `8083`
- **Base de Datos:** `db_ordenes`
- **URL Swagger:** http://localhost:8083/swagger-ui.html

---

### 4. Microservicio de Pagos

**Archivo:** `microservice-pagos/src/main/resources/application.yml`

```yaml
server:
  port: 8084  ← PUERTO DEL MICROSERVICIO

spring:
  application:
    name: msvc-pagos
  datasource:
    url: jdbc:mysql://localhost:3306/db_pagos  ← NOMBRE DE LA BASE DE DATOS
    username: root
    password:  ← CONTRASEÑA DE MYSQL (cambiar si es necesario)
```

**Resumen:**
- **Puerto:** `8084`
- **Base de Datos:** `db_pagos`
- **URL Swagger:** http://localhost:8084/swagger-ui.html

---

## 📊 Tabla Resumen

| Microservicio | Puerto | Base de Datos | Archivo de Configuración |
|---------------|--------|---------------|--------------------------|
| Usuarios | `8081` | `db_usuarios` | `microservice-usuarios/src/main/resources/application.yml` |
| Productos | `8082` | `db_productos` | `microservice-productos/src/main/resources/application.yml` |
| Órdenes | `8083` | `db_ordenes` | `microservice-ordenes/src/main/resources/application.yml` |
| Pagos | `8084` | `db_pagos` | `microservice-pagos/src/main/resources/application.yml` |

---

## 🔑 Campos Importantes en `application.yml`

### 1. Puerto del Servidor
```yaml
server:
  port: 8081  # Cambia este número para cambiar el puerto
```

### 2. Nombre de la Base de Datos
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db_usuarios
    #                                    ^^^^^^^^^^^^
    #                                    Nombre de la BD aquí
```

### 3. Usuario y Contraseña de MySQL
```yaml
spring:
  datasource:
    username: root        # Usuario de MySQL
    password:            # Contraseña de MySQL (dejar vacío si no tiene)
```

---

## ⚙️ Cómo Cambiar la Configuración

### Cambiar el Puerto

1. Abre el archivo `application.yml` del microservicio
2. Busca la línea `server.port:`
3. Cambia el número (ejemplo: de `8081` a `9091`)
4. Guarda el archivo
5. Reinicia el microservicio

**Ejemplo:**
```yaml
# Antes
server:
  port: 8081

# Después
server:
  port: 9091
```

### Cambiar el Nombre de la Base de Datos

1. Abre el archivo `application.yml` del microservicio
2. Busca la línea `url: jdbc:mysql://localhost:3306/`
3. Cambia el nombre después de `/` (ejemplo: de `db_usuarios` a `db_usuarios_nuevo`)
4. Guarda el archivo
5. Crea la nueva base de datos en MySQL
6. Reinicia el microservicio

**Ejemplo:**
```yaml
# Antes
url: jdbc:mysql://localhost:3306/db_usuarios

# Después
url: jdbc:mysql://localhost:3306/db_usuarios_nuevo
```

### Cambiar la Contraseña de MySQL

1. Abre el archivo `application.yml` del microservicio
2. Busca la línea `password:`
3. Escribe tu contraseña de MySQL
4. Guarda el archivo
5. Reinicia el microservicio

**Ejemplo:**
```yaml
# Antes
password: 

# Después
password: miPassword123
```

---

## ⚠️ Importante

- **Puertos únicos:** Cada microservicio debe tener un puerto diferente
- **Bases de datos separadas:** Cada microservicio tiene su propia base de datos
- **MySQL debe estar ejecutándose:** Antes de iniciar los microservicios, MySQL debe estar activo
- **Crear las bases de datos:** Las bases de datos deben existir en MySQL antes de iniciar los microservicios

---

## 🔍 Verificar la Configuración

### Verificar que el Puerto Está Configurado

Al iniciar el microservicio, deberías ver en los logs:
```
Tomcat started on port(s): 8081 (http)
```

### Verificar que la Base de Datos Está Conectada

Al iniciar el microservicio, deberías ver en los logs:
```
HikariPool-1 - Starting...
HikariPool-1 - Start completed.
```

Si ves errores de conexión, verifica:
- ✅ MySQL está ejecutándose
- ✅ La base de datos existe
- ✅ El usuario y contraseña son correctos
- ✅ El nombre de la base de datos coincide en `application.yml`

---

## 📝 Notas

- Los archivos `application.yml` están en la carpeta `src/main/resources/` de cada microservicio
- Si cambias algún puerto, también debes actualizar el frontend en `src/services/api.ts`
- Los nombres de las bases de datos deben coincidir exactamente con los que creaste en MySQL

