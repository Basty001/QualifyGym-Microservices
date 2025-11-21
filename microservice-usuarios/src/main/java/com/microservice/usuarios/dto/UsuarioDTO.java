package com.microservice.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private int id_usuario;
    private String username;
    private String email;
    private String phone;
    private String password;
    private int rolId;
}

