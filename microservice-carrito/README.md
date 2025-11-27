# Microservicio de Carrito

Microservicio para gestionar carritos de compra y sus items.

## Configuración

- **Puerto:** 8085
- **Base de Datos:** `db_carrito`
- **URL Base:** `http://localhost:8085/api/v1/carrito`
- **Swagger:** `http://localhost:8085/swagger-ui.html`

## Endpoints

### Carrito

- `GET /api/v1/carrito/listar` - Lista todos los carritos
- `GET /api/v1/carrito/{id_carrito}` - Obtiene un carrito por ID
- `GET /api/v1/carrito/usuario/{usuarioId}` - Obtiene el carrito de un usuario
- `POST /api/v1/carrito` - Crea un nuevo carrito
- `PUT /api/v1/carrito/{id_carrito}` - Actualiza un carrito
- `DELETE /api/v1/carrito/{id_carrito}` - Elimina un carrito
- `DELETE /api/v1/carrito/usuario/{usuarioId}` - Elimina el carrito de un usuario

### Items del Carrito

- `POST /api/v1/carrito/{id_carrito}/items` - Agrega un item al carrito
- `DELETE /api/v1/carrito/items/{id_item}` - Elimina un item del carrito

## Modelo de Datos

### Carrito
- `id_carrito` (int) - ID del carrito
- `usuarioId` (int) - ID del usuario
- `fechaCreacion` (LocalDateTime) - Fecha de creación
- `fechaActualizacion` (LocalDateTime) - Fecha de actualización
- `items` (List<CarritoItem>) - Items del carrito

### CarritoItem
- `id_item` (int) - ID del item
- `productoId` (int) - ID del producto
- `cantidad` (int) - Cantidad del producto
- `precio` (BigDecimal) - Precio unitario

## Ejemplo de Uso

### Crear un carrito
```json
POST /api/v1/carrito
{
  "usuarioId": 1
}
```

### Agregar un item al carrito
```json
POST /api/v1/carrito/{id_carrito}/items
{
  "productoId": 1,
  "cantidad": 2,
  "precio": 29990.00
}
```

### Obtener carrito de un usuario
```
GET /api/v1/carrito/usuario/1
```

