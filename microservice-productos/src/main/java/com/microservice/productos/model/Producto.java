package com.microservice.productos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;

    @Column(nullable=false, length=200)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min=2, max=200, message="El nombre debe tener entre 2 y 200 caracteres")
    private String nombre;

    @Column(length=1000)
    private String descripcion;

    @Column(nullable=false, precision=10, scale=2)
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @Column(length=500)
    private String imagen;

    @Column(nullable=false)
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Column(name="categoria_id", nullable=false)
    @NotNull(message = "La categoría es obligatoria")
    private int categoriaId;

    @Column(nullable=false)
    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo = true;
}

