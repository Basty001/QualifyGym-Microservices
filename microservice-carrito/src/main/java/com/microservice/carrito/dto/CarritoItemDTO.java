package com.microservice.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItemDTO {
    private int id_item;
    private int productoId;
    private Integer cantidad;
    private BigDecimal precio;
}

