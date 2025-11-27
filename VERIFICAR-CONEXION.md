# Cómo Verificar que el Frontend está Conectado con los Microservicios

## 🔍 Método 1: Consola del Navegador (Más Fácil)

### Pasos:

1. **Abre la aplicación web** en: http://localhost:5173

2. **Abre las herramientas de desarrollador:**
   - Presiona `F12` o `Ctrl + Shift + I`
   - O click derecho → "Inspeccionar"

3. **Ve a la pestaña "Console" (Consola)**

4. **Prueba estas acciones:**

   #### a) Registro de Usuario
   - Ve a la página de registro
   - Completa el formulario y regístrate
   - **Busca en la consola:** Deberías ver mensajes como:
     - `POST http://localhost:8081/api/v1/usuarios` (status: 201)
     - O si hay error: `Failed to fetch` o `Network error`

   #### b) Login
   - Ve a la página de login
   - Ingresa tus credenciales
   - **Busca en la consola:** Deberías ver:
     - `POST http://localhost:8081/api/v1/usuarios/login` (status: 200)
     - O mensajes de error si no funciona

   #### c) Ver Productos
   - Ve a la tienda (`/store`)
   - **Busca en la consola:** Deberías ver:
     - `GET http://localhost:8082/api/v1/productos/listar` o `/activos`
     - O `GET http://localhost:8082/api/v1/productos/activos`

   #### d) Finalizar Compra
   - Agrega productos al carrito
   - Finaliza la compra
   - **Busca en la consola:** Deberías ver:
     - `POST http://localhost:8083/api/v1/ordenes` (status: 201)
     - `POST http://localhost:8084/api/v1/pagos` (status: 201)

---

## 🔍 Método 2: Pestaña Network (Red) del Navegador

### Pasos:

1. **Abre las herramientas de desarrollador** (`F12`)

2. **Ve a la pestaña "Network" (Red)**

3. **Recarga la página** (`F5`)

4. **Filtra por "Fetch/XHR"** (solo peticiones AJAX)

5. **Prueba las acciones** (registro, login, ver productos, etc.)

6. **Verifica las peticiones:**
   - Deberías ver peticiones a `localhost:8081`, `8082`, `8083`, `8084`
   - El **Status** debe ser `200` (éxito) o `201` (creado)
   - Si ves `Failed` o `CORS error`, hay un problema

### Ejemplo de lo que deberías ver:

```
Name                          Status  Type
─────────────────────────────────────────────
usuarios/login                200     fetch
productos/activos             200     fetch
ordenes                       201     fetch
pagos                         201     fetch
```

---

## 🔍 Método 3: Probar Funcionalidades Específicas

### Prueba 1: Registro y Login

1. **Registra un nuevo usuario:**
   - Ve a `/register`
   - Completa el formulario
   - Click en "Registrarse"
   - **Resultado esperado:** Te redirige o muestra mensaje de éxito

2. **Haz login:**
   - Ve a `/login`
   - Ingresa email y contraseña
   - Click en "Iniciar Sesión"
   - **Resultado esperado:** Te autentica y redirige a la página principal

### Prueba 2: Ver Productos

1. **Ve a la tienda** (`/store`)
2. **Resultado esperado:** Deberías ver productos cargados desde el microservicio
3. Si no hay productos, créalos desde Swagger primero

### Prueba 3: Agregar al Carrito y Comprar

1. **Agrega productos al carrito** desde la tienda
2. **Ve al carrito** (`/cart`)
3. **Finaliza la compra**
4. **Resultado esperado:** 
   - Muestra mensaje de éxito
   - Se crea la orden en el microservicio de órdenes
   - Se crea el pago en el microservicio de pagos

### Prueba 4: Verificar en Swagger

1. **Abre Swagger de Órdenes:** http://localhost:8083/swagger-ui.html
2. **Expande `GET /api/v1/ordenes/listar`**
3. **Click en "Execute"**
4. **Resultado esperado:** Deberías ver la orden que creaste desde el frontend

5. **Abre Swagger de Pagos:** http://localhost:8084/swagger-ui.html
6. **Expande `GET /api/v1/pagos/listar`**
7. **Click en "Execute"**
8. **Resultado esperado:** Deberías ver el pago que creaste desde el frontend

---

## ✅ Checklist de Verificación

Marca cada uno cuando funcione:

- [ ] Puedo registrarme desde el frontend
- [ ] Puedo hacer login desde el frontend
- [ ] Veo productos en la tienda (desde el microservicio)
- [ ] Puedo agregar productos al carrito
- [ ] Puedo finalizar una compra
- [ ] La orden aparece en Swagger de órdenes
- [ ] El pago aparece en Swagger de pagos
- [ ] No hay errores en la consola del navegador
- [ ] Las peticiones en Network muestran status 200/201

---

## 🐛 Si Algo No Funciona

### Error: "Failed to fetch" o "Network error"

**Causa:** El microservicio no está ejecutándose o hay problema de CORS.

**Solución:**
1. Verifica que el microservicio esté ejecutándose (revisa la terminal)
2. Verifica que el puerto sea correcto
3. Revisa la consola del navegador para más detalles

### Error: "CORS policy"

**Causa:** Problema de CORS entre frontend y microservicios.

**Solución:**
- Ya está configurado `@CrossOrigin` en los controladores
- Si persiste, verifica que los microservicios estén ejecutándose

### No aparecen productos en la tienda

**Causa:** No hay productos en la base de datos.

**Solución:**
1. Crea productos desde Swagger: http://localhost:8082/swagger-ui.html
2. O verifica que el microservicio de productos esté ejecutándose

### El login no funciona

**Causa:** El usuario no existe o el microservicio no está ejecutándose.

**Solución:**
1. Crea un usuario desde Swagger primero: http://localhost:8081/swagger-ui.html
2. O regístrate desde el frontend
3. Verifica que el microservicio de usuarios esté ejecutándose

---

## 📊 URLs para Verificar

| Servicio | URL Swagger | URL API |
|----------|-------------|---------|
| Usuarios | http://localhost:8081/swagger-ui.html | http://localhost:8081/api/v1/usuarios |
| Productos | http://localhost:8082/swagger-ui.html | http://localhost:8082/api/v1/productos |
| Órdenes | http://localhost:8083/swagger-ui.html | http://localhost:8083/api/v1/ordenes |
| Pagos | http://localhost:8084/swagger-ui.html | http://localhost:8084/api/v1/pagos |
| Frontend | - | http://localhost:5173 |

---

## 🎯 Resumen Rápido

**La forma más rápida de verificar:**

1. Abre http://localhost:5173
2. Presiona `F12` → Pestaña "Console"
3. Intenta registrarte o hacer login
4. Busca en la consola peticiones a `localhost:8081`, `8082`, `8083`, `8084`
5. Si ves las peticiones con status 200/201, **¡está conectado!** ✅

