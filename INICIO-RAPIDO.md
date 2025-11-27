# 🚀 Inicio Rápido - QualifyGym

## 📋 Orden de Ejecución

Sigue estos pasos **en orden** para que todo funcione correctamente:

---

## ✅ Paso 1: Verificar MySQL

### 1.1 Iniciar MySQL
Asegúrate de que MySQL esté ejecutándose en tu sistema.

### 1.2 Crear Bases de Datos
Abre MySQL Workbench o la línea de comandos y ejecuta:

```sql
CREATE DATABASE IF NOT EXISTS db_usuarios;
CREATE DATABASE IF NOT EXISTS db_productos;
CREATE DATABASE IF NOT EXISTS db_ordenes;
CREATE DATABASE IF NOT EXISTS db_pagos;
```

### 1.3 Verificar
```sql
SHOW DATABASES;
```

---

## ⚙️ Paso 2: Configurar Contraseña MySQL

Edita estos 4 archivos y configura tu contraseña de MySQL:

**Archivos a editar:**
- `microservice-usuarios/src/main/resources/application.yml`
- `microservice-productos/src/main/resources/application.yml`
- `microservice-ordenes/src/main/resources/application.yml`
- `microservice-pagos/src/main/resources/application.yml`

**En cada archivo, busca:**
```yaml
spring:
  datasource:
    password: 
```

**Si tienes contraseña:**
```yaml
spring:
  datasource:
    password: tuPassword123
```

**Si NO tienes contraseña, déjala vacía:**
```yaml
spring:
  datasource:
    password: 
```

---

## 🚀 Paso 3: Iniciar los Microservicios

**IMPORTANTE:** Abre **4 terminales diferentes** (una para cada microservicio).

### Terminal 1 - Usuarios (Puerto 8081)

```powershell
cd C:\Users\isaia\OneDrive\Documentos\GitHub\QualifyGym-Microservices\microservice-usuarios
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run
```

**Espera a ver:** `Started MicroserviceUsuariosApplication`

### Terminal 2 - Productos (Puerto 8082)

```powershell
cd C:\Users\isaia\OneDrive\Documentos\GitHub\QualifyGym-Microservices\microservice-productos
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run
```

**Espera a ver:** `Started MicroserviceProductosApplication`

### Terminal 3 - Órdenes (Puerto 8083)

```powershell
cd C:\Users\isaia\OneDrive\Documentos\GitHub\QualifyGym-Microservices\microservice-ordenes
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run
```

**Espera a ver:** `Started MicroserviceOrdenesApplication`

### Terminal 4 - Pagos (Puerto 8084)

```powershell
cd C:\Users\isaia\OneDrive\Documentos\GitHub\QualifyGym-Microservices\microservice-pagos
.\mvnw.cmd clean install
.\mvnw.cmd spring-boot:run
```

**Espera a ver:** `Started MicroservicePagosApplication`

---

## ✅ Paso 4: Verificar que los Microservicios Funcionan

Abre estos URLs en tu navegador. Si ves Swagger, están funcionando:

- ✅ **Usuarios**: http://localhost:8081/swagger-ui.html
- ✅ **Productos**: http://localhost:8082/swagger-ui.html
- ✅ **Órdenes**: http://localhost:8083/swagger-ui.html
- ✅ **Pagos**: http://localhost:8084/swagger-ui.html

---

## 🌐 Paso 5: Iniciar el Frontend

Abre una **nueva terminal** (la quinta terminal):

```powershell
cd C:\Users\isaia\OneDrive\Documentos\GitHub\GymFitWebTY\GymFit2.0
npm install
npm run dev
```

**Deberías ver:**
```
VITE v5.x.x  ready in xxx ms

➜  Local:   http://localhost:5173/
```

---

## 🧪 Paso 6: Probar la Aplicación

1. Abre tu navegador en: **http://localhost:5173**
2. Prueba:
   - Registrarte
   - Hacer login
   - Ver productos en la tienda
   - Agregar productos al carrito
   - Finalizar compra

---

## 📊 Resumen de Puertos

| Servicio | Puerto | URL |
|----------|--------|-----|
| Usuarios | 8081 | http://localhost:8081 |
| Productos | 8082 | http://localhost:8082 |
| Órdenes | 8083 | http://localhost:8083 |
| Pagos | 8084 | http://localhost:8084 |
| Frontend | 5173 | http://localhost:5173 |

---

## ⚠️ Notas Importantes

1. **Primero MySQL**: Asegúrate de que MySQL esté ejecutándose antes de iniciar los microservicios
2. **Orden de inicio**: Puedes iniciar los microservicios en cualquier orden, pero todos deben estar ejecutándose antes del frontend
3. **Contraseña MySQL**: Si cambias la contraseña, actualízala en los 4 archivos `application.yml`
4. **Puertos ocupados**: Si un puerto está ocupado, cambia el puerto en `application.yml` o detén el proceso que lo está usando

---

## 🐛 Si Algo No Funciona

### Error: "Cannot connect to database"
- Verifica que MySQL esté ejecutándose
- Verifica la contraseña en `application.yml`
- Verifica que las bases de datos existan

### Error: "Port already in use"
- Cambia el puerto en `application.yml`
- O detén el proceso que usa ese puerto

### Los microservicios no inician
- Ejecuta `mvnw clean install` primero
- Verifica que Java 17+ esté instalado
- Revisa los logs en la terminal

---

## ✅ Checklist Final

Antes de probar, verifica:

- [ ] MySQL está ejecutándose
- [ ] Las 4 bases de datos están creadas
- [ ] La contraseña de MySQL está configurada en los 4 `application.yml`
- [ ] Los 4 microservicios están ejecutándose (4 terminales)
- [ ] Puedo acceder a Swagger de cada microservicio
- [ ] El frontend está ejecutándose
- [ ] Puedo acceder a http://localhost:5173

---

¡Listo! Tu sistema debería estar funcionando correctamente. 🎉

