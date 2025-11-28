package com.qualifygym.productos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private int id_producto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String imagen;
    private Integer stock;
    private int categoriaId;
    private Boolean activo;
}

