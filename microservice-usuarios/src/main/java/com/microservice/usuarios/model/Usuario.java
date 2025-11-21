package com.microservice.usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    @Column(name="username", nullable=false, length=100)
    @NotBlank(message = "El username es obligatorio")
    @Size(min=3, max=100, message="El username debe tener entre 3 y 100 caracteres")
    private String username;

    @Column(name="email", unique=true, nullable=false, length=100)
    @NotBlank(message = "El email es obligatorio")
    @Email(message="El formato del correo no es válido")
    private String email;

    @Column(name="phone", unique=true, nullable=false, length=20)
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "El teléfono debe tener formato válido")
    private String phone;

    @Column(name="password", nullable=false)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min=6, message="La contraseña debe tener al menos 6 caracteres")
    private String password;

    @Column(name="rol_id", nullable=false)
    @NotNull(message = "El rol es obligatorio")
    private int rolId;
}

