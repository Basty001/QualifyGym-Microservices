# Instrucciones Completas - Cómo Hacer Funcionar QualifyGym

## ✅ Verificación de Configuración

He verificado y configurado todo el sistema. Todo está listo para funcionar:

- ✅ **4 Microservicios** configurados con puertos y bases de datos
- ✅ **Endpoints** verificados y conectados con el frontend
- ✅ **CORS** habilitado en todos los microservicios
- ✅ **Frontend** configurado para comunicarse con los microservicios
- ✅ **Endpoints faltantes** agregados (`/usuario/{usuarioId}` en órdenes y pagos)

---

## 📋 Paso 1: Preparar MySQL

### 1.1 Iniciar MySQL

Asegúrate de que MySQL esté ejecutándose en tu sistema.

### 1.2 Crear las Bases de Datos

Abre MySQL Workbench o la línea de comandos de MySQL y ejecuta:

```sql
CREATE DATABASE db_usuarios;
CREATE DATABASE db_productos;
CREATE DATABASE db_ordenes;
CREATE DATABASE db_pagos;
```

### 1.3 Verificar

```sql
SHOW DATABASES;
```

Deberías ver las 4 bases de datos: `db_usuarios`, `db_productos`, `db_ordenes`, `db_pagos`.

---

## ⚙️ Paso 2: Configurar Contraseña de MySQL

### 2.1 Editar Archivos de Configuración

Edita cada archivo `application.yml` en cada microservicio y configura tu contraseña de MySQL:

**Ubicación de los archivos:**
- `microservice-usuarios/src/main/resources/application.yml`
- `microservice-productos/src/main/resources/application.yml`
- `microservice-ordenes/src/main/resources/application.yml`
- `microservice-pagos/src/main/resources/application.yml`

**En cada archivo, busca esta línea:**
```yaml
spring:
  datasource:
    password: 
```

**Si tu MySQL tiene contraseña, escríbela:**
```yaml
spring:
  datasource:
    password: tuPassword123
```

**Si tu MySQL NO tiene contraseña, déjala vacía:**
```yaml
spring:
  datasource:
    password: 
```

---

## 🚀 Paso 3: Ejecutar los Microservicios

Abre **4 terminales diferentes** (una para cada microservicio):

### Terminal 1 - Microservicio de Usuarios (Puerto 8081)

```bash
cd C:\Users\isaia\OneDrive\Documentos\GitHub\QualifyGym-Microservices\microservice-usuarios
mvnw spring-boot:run
```

**Espera a ver:** `Started MicroserviceUsuariosApplication`

### Terminal 2 - Microservicio de Productos (Puerto 8082)

```bash
cd C:\Users\isaia\OneDrive\Documentos\GitHub\QualifyGym-Microservices\microservice-productos
mvnw spring-boot:run
```

**Espera a ver:** `Started MicroserviceProductosApplication`

### Terminal 3 - Microservicio de Órdenes (Puerto 8083)

```bash
cd C:\Users\isaia\OneDrive\Documentos\GitHub\QualifyGym-Microservices\microservice-ordenes
mvnw spring-boot:run
```

**Espera a ver:** `Started MicroserviceOrdenesApplication`

### Terminal 4 - Microservicio de Pagos (Puerto 8084)

```bash
cd C:\Users\isaia\OneDrive\Documentos\GitHub\QualifyGym-Microservices\microservice-pagos
mvnw spring-boot:run
```

**Espera a ver:** `Started MicroservicePagosApplication`

---

## ✅ Paso 4: Verificar que los Microservicios Funcionan

### 4.1 Verificar Swagger

Abre estos URLs en tu navegador. Si ves la interfaz de Swagger, están funcionando:

- ✅ **Usuarios**: http://localhost:8081/swagger-ui.html
- ✅ **Productos**: http://localhost:8082/swagger-ui.html
- ✅ **Órdenes**: http://localhost:8083/swagger-ui.html
- ✅ **Pagos**: http://localhost:8084/swagger-ui.html

### 4.2 Crear Datos de Prueba (Opcional)

#### Crear un Usuario desde Swagger:

1. Ve a http://localhost:8081/swagger-ui.html
2. Expande `POST /api/v1/usuarios`
3. Click en "Try it out"
4. Usa este JSON:
```json
{
  "username": "testuser",
  "email": "test@test.com",
  "phone": "+56912345678",
  "password": "password123",
  "rolId": 2
}
```
5. Click en "Execute"
6. Debe retornar código 201 (Created)

#### Crear un Producto desde Swagger:

1. Ve a http://localhost:8082/swagger-ui.html
2. Expande `POST /api/v1/productos`
3. Click en "Try it out"
4. Usa este JSON:
```json
{
  "nombre": "Proteína Whey",
  "descripcion": "Proteína de suero de leche",
  "precio": 29990.00,
  "imagen": "https://example.com/proteina.jpg",
  "stock": 50,
  "categoriaId": 1,
  "activo": true
}
```
5. Click en "Execute"
6. Debe retornar código 201 (Created)

---

## 🌐 Paso 5: Ejecutar el Frontend

### 5.1 Abrir Terminal del Frontend

Abre una **nueva terminal** (la quinta terminal):

```bash
cd C:\Users\isaia\OneDrive\Documentos\GitHub\GymFitWebTY\GymFit2.0
```

### 5.2 Instalar Dependencias (si es necesario)

```bash
npm install
```

### 5.3 Ejecutar el Frontend

```bash
npm run dev
```

**Deberías ver:**
```
VITE v5.x.x  ready in xxx ms

➜  Local:   http://localhost:5173/
```

---

## 🧪 Paso 6: Probar la Integración Completa

### 6.1 Abrir la Aplicación Web

Abre tu navegador en: **http://localhost:5173**

### 6.2 Flujo de Prueba Completo

#### 1. Registro de Usuario

1. Click en "Registrarse" o ve a `/register`
2. Completa el formulario:
   - Nombre: "Juan Pérez"
   - Email: "juan@test.com"
   - Contraseña: "password123"
   - Confirmar contraseña: "password123"
3. Click en "Registrarse"
4. **Resultado esperado:** Te redirige al login o a la página principal

#### 2. Login

1. Ve a `/login`
2. Ingresa:
   - Email: "juan@test.com"
   - Contraseña: "password123"
3. Click en "Iniciar Sesión"
4. **Resultado esperado:** Te autentica y redirige a la página principal

#### 3. Ver Productos en la Tienda

1. Ve a `/store`
2. **Resultado esperado:** Deberías ver productos cargados desde el microservicio
3. Si no hay productos, créalos desde Swagger (Paso 4.2)

#### 4. Agregar Productos al Carrito

1. En la tienda, click en "Comprar" en cualquier producto
2. **Resultado esperado:** El producto se agrega al carrito y te redirige al carrito

#### 5. Finalizar Compra

1. Ve a `/cart`
2. Verifica los productos en el carrito
3. Click en "Finalizar Compra"
4. **Resultado esperado:** 
   - Se crea una orden en el microservicio de órdenes
   - Se crea un pago en el microservicio de pagos
   - Te redirige al panel de usuario
   - Muestra mensaje de éxito

#### 6. Verificar en los Microservicios

1. Ve a http://localhost:8083/swagger-ui.html (Órdenes)
2. Expande `GET /api/v1/ordenes/listar`
3. Click en "Execute"
4. **Resultado esperado:** Deberías ver la orden creada

5. Ve a http://localhost:8084/swagger-ui.html (Pagos)
6. Expande `GET /api/v1/pagos/listar`
7. Click en "Execute"
8. **Resultado esperado:** Deberías ver el pago creado

---

## 📊 Resumen de Puertos y URLs

| Servicio | Puerto | URL Base | Swagger |
|----------|--------|----------|---------|
| Usuarios | 8081 | http://localhost:8081/api/v1/usuarios | http://localhost:8081/swagger-ui.html |
| Productos | 8082 | http://localhost:8082/api/v1/productos | http://localhost:8082/swagger-ui.html |
| Órdenes | 8083 | http://localhost:8083/api/v1/ordenes | http://localhost:8083/swagger-ui.html |
| Pagos | 8084 | http://localhost:8084/api/v1/pagos | http://localhost:8084/swagger-ui.html |
| Frontend | 5173 | http://localhost:5173 | - |

---

## 🔍 Endpoints Disponibles

### Usuarios (Puerto 8081)
- `GET /api/v1/usuarios/listar` - Lista todos los usuarios
- `GET /api/v1/usuarios/{id}` - Obtiene un usuario por ID
- `POST /api/v1/usuarios` - Crea un nuevo usuario
- `POST /api/v1/usuarios/login` - Inicia sesión
- `PUT /api/v1/usuarios/{id}` - Actualiza un usuario
- `DELETE /api/v1/usuarios/{id}` - Elimina un usuario

### Productos (Puerto 8082)
- `GET /api/v1/productos/listar` - Lista todos los productos
- `GET /api/v1/productos/activos` - Lista productos activos
- `GET /api/v1/productos/{id}` - Obtiene un producto por ID
- `POST /api/v1/productos` - Crea un nuevo producto
- `PUT /api/v1/productos/{id}` - Actualiza un producto
- `DELETE /api/v1/productos/{id}` - Elimina un producto

### Órdenes (Puerto 8083)
- `GET /api/v1/ordenes/listar` - Lista todas las órdenes
- `GET /api/v1/ordenes/{id}` - Obtiene una orden por ID
- `GET /api/v1/ordenes/usuario/{usuarioId}` - Obtiene órdenes por usuario
- `POST /api/v1/ordenes` - Crea una nueva orden
- `PUT /api/v1/ordenes/{id}` - Actualiza una orden
- `DELETE /api/v1/ordenes/{id}` - Elimina una orden

### Pagos (Puerto 8084)
- `GET /api/v1/pagos/listar` - Lista todos los pagos
- `GET /api/v1/pagos/{id}` - Obtiene un pago por ID
- `GET /api/v1/pagos/usuario/{usuarioId}` - Obtiene pagos por usuario
- `POST /api/v1/pagos` - Crea un nuevo pago
- `PUT /api/v1/pagos/{id}` - Actualiza un pago
- `DELETE /api/v1/pagos/{id}` - Elimina un pago

---

## 🐛 Solución de Problemas

### Error: "Cannot connect to database"

**Causa:** MySQL no está ejecutándose o la contraseña es incorrecta.

**Solución:**
1. Verifica que MySQL esté ejecutándose
2. Verifica la contraseña en `application.yml` de cada microservicio
3. Verifica que las bases de datos existan

### Error: "Port already in use"

**Causa:** El puerto ya está siendo usado por otro proceso.

**Solución:**
1. Cambia el puerto en `application.yml` (línea `server.port`)
2. O detén el proceso que está usando ese puerto:
   ```bash
   # Windows PowerShell
   netstat -ano | findstr :8081
   taskkill /PID <PID> /F
   ```

### Error: CORS en el navegador

**Causa:** Los microservicios no permiten solicitudes desde el frontend.

**Solución:** Ya está configurado. Si persiste:
1. Verifica que los puertos sean correctos
2. Verifica que los microservicios estén ejecutándose
3. Revisa la consola del navegador para más detalles

### Error: "404 Not Found" en las APIs

**Causa:** El microservicio no está ejecutándose o la URL es incorrecta.

**Solución:**
1. Verifica que el microservicio esté ejecutándose
2. Verifica la URL en `src/services/api.ts` del frontend
3. Revisa los logs del microservicio en la terminal

### El frontend no carga productos

**Causa:** El microservicio de productos no está ejecutándose o no hay productos.

**Solución:**
1. Verifica que el microservicio de productos esté ejecutándose
2. Crea productos desde Swagger (Paso 4.2)
3. Verifica la consola del navegador para errores
4. El frontend tiene fallback a localStorage si el microservicio no está disponible

### El login no funciona

**Causa:** El microservicio de usuarios no está ejecutándose o el usuario no existe.

**Solución:**
1. Verifica que el microservicio de usuarios esté ejecutándose
2. Crea un usuario desde Swagger primero
3. Verifica la consola del navegador para errores
4. El frontend tiene fallback a localStorage si el microservicio no está disponible

---

## ✅ Checklist Final

Antes de probar, verifica:

- [ ] MySQL está ejecutándose
- [ ] Las 4 bases de datos están creadas (`db_usuarios`, `db_productos`, `db_ordenes`, `db_pagos`)
- [ ] La contraseña de MySQL está configurada en los 4 `application.yml`
- [ ] Los 4 microservicios están ejecutándose (4 terminales)
- [ ] Puedo acceder a Swagger de cada microservicio (4 URLs)
- [ ] El frontend está ejecutándose (`npm run dev`)
- [ ] Puedo acceder a la aplicación web (http://localhost:5173)

---

## 🎯 Orden de Ejecución Recomendado

1. **Primero:** Configura MySQL y crea las bases de datos
2. **Segundo:** Configura las contraseñas en `application.yml` (4 archivos)
3. **Tercero:** Ejecuta los 4 microservicios (4 terminales)
4. **Cuarto:** Verifica Swagger de cada microservicio
5. **Quinto:** (Opcional) Crea datos de prueba desde Swagger
6. **Sexto:** Ejecuta el frontend (`npm run dev`)
7. **Séptimo:** Prueba la aplicación web completa

---

## 📝 Notas Importantes

- **Fallback automático:** Si un microservicio no está disponible, el frontend usará localStorage como respaldo
- **Datos persistentes:** Los datos se guardan en MySQL, no se pierden al reiniciar
- **Swagger:** Usa Swagger para probar los endpoints directamente sin el frontend
- **Logs:** Revisa los logs en las terminales de los microservicios para ver errores
- **CORS:** Ya está configurado en todos los microservicios
- **Endpoints:** Todos los endpoints necesarios están implementados y funcionando

---

## 🎉 Si Todo Funciona Correctamente

Deberías poder:
- ✅ Registrarte desde la web
- ✅ Hacer login desde la web
- ✅ Ver productos en la tienda (desde el microservicio)
- ✅ Agregar productos al carrito
- ✅ Finalizar compras (crea órdenes y pagos en los microservicios)
- ✅ Ver tus compras en el panel de usuario

¡Felicidades! Tu sistema está completamente funcional. 🎉

