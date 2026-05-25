# Microservicio de Inventario

## server.port=8091

## Endpoints

### POST `/api/v1/inventario`
Crea un nuevo registro de inventario. Si el producto ya existe, suma el stock.

**JSON de entrada:**
```json
{
    "idProducto": 1,
    "idBodega": 1,
    "stockDisponible": 10,
    "stockMinimo": 5
}
```

---

### GET `/api/v1/inventario`
Lista todos los registros de inventario.

---

### GET `/api/v1/inventario/{id}`
Obtiene un registro de inventario por su id.

---

### GET `/api/v1/inventario/alertas`
Lista todos los productos cuyo stock disponible es menor o igual al stock mínimo.

**Respuesta:**
```json
[
    {
        "idInventario": 1,
        "idProducto": 1,
        "idBodega": 1,
        "tituloProducto": "El Señor de los Anillos",
        "stockDisponible": 10,
        "stockMinimo": 15,
        "fechaActualizacion": "2026-05-25"
    }
]
```

---

### GET `/api/v1/inventario/totalStock`
Retorna el total de productos en inventario.

---

### GET `/api/v1/inventario/stockPorBodega/{idBodega}`
Retorna el total de stock de una bodega específica.

---

### PUT `/api/v1/inventario/{id}`
Actualiza un registro de inventario.

**JSON de entrada:**
```json
{
    "idProducto": 1,
    "idBodega": 1,
    "tituloProducto": "El Señor de los Anillos",
    "stockDisponible": 50,
    "stockMinimo": 5
}
```

---

### PUT `/api/v1/inventario/descontar/{idProducto}/{cantidad}`
Descuenta stock de un producto específico.

**Ejemplo:**
```
PUT /api/v1/inventario/descontar/1/5
```

---

### DELETE `/api/v1/inventario/{id}`
Elimina un registro de inventario por su id.

---

## Dependencias
| MS | Puerto | Para qué |
|---|---|---|
| MS Catálogo | 8090 | Validar que el producto existe al registrar |