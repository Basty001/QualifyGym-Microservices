package com.microservice.pagos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pago;
    
    @Column(name="orden_id", nullable=false)
    @NotNull(message = "La orden es obligatoria")
    private int ordenId;
    
    @Column(name="usuario_id", nullable=false)
    @NotNull(message = "El usuario es obligatorio")
    private int usuarioId;
    
    @Column(nullable=false, precision=10, scale=2)
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;
    
    @Column(name="metodo_pago", nullable=false, length=50)
    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;
    
    @Column(nullable=false, length=50)
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
    
    @Column(name="fecha_pago", nullable=false)
    private LocalDateTime fechaPago;
    
    @Column(name="informacion_adicional", length=500)
    private String informacionAdicional;
}

