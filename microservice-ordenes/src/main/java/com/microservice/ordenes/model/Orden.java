package com.microservice.ordenes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_orden;
    
    @Column(name="usuario_id", nullable=false)
    @NotNull(message = "El usuario es obligatorio")
    private int usuarioId;
    
    @Column(nullable=false, precision=10, scale=2)
    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    private BigDecimal total;
    
    @Column(nullable=false, length=50)
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
    
    @Column(name="fecha_creacion", nullable=false)
    private LocalDateTime fechaCreacion;
    
    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}

