package com.microservice.usuarios.config;

import com.microservice.usuarios.model.Usuario;
import com.microservice.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Clase para inicializar usuarios precargados del frontend en la base de datos
 * Este componente se ejecuta automáticamente al iniciar el microservicio
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
    }

    /**
     * Inicializa los usuarios precargados del frontend si no existen
     * Rol IDs: 1=ADMIN, 2=USER, 3=TRAINER
     */
    private void initializeUsers() {
        // Usuario Administrador 1 (del frontend)
        if (!usuarioRepository.findByEmail("admin@gym.com").isPresent()) {
            Usuario admin = Usuario.builder()
                    .username("Administrador")
                    .email("admin@gym.com")
                    .phone("+1234567890")
                    .password(passwordEncoder.encode("admin123"))
                    .rolId(1) // ADMIN
                    .build();
            usuarioRepository.save(admin);
        }

        // Usuario Entrenador (del frontend)
        if (!usuarioRepository.findByEmail("trainer@gym.com").isPresent()) {
            Usuario trainer = Usuario.builder()
                    .username("Carlos Entrenador")
                    .email("trainer@gym.com")
                    .phone("+1234567891")
                    .password(passwordEncoder.encode("trainer123"))
                    .rolId(3) // TRAINER
                    .build();
            usuarioRepository.save(trainer);
        }

        // Usuario Normal 1 (del frontend)
        if (!usuarioRepository.findByEmail("user@gym.com").isPresent()) {
            Usuario user = Usuario.builder()
                    .username("Usuario Ejemplo")
                    .email("user@gym.com")
                    .phone("+1234567892")
                    .password(passwordEncoder.encode("user123"))
                    .rolId(2) // USER
                    .build();
            usuarioRepository.save(user);
        }

        // Usuario Administrador 2 (adicional)
        if (!usuarioRepository.findByEmail("admin2@gym.com").isPresent()) {
            Usuario admin2 = Usuario.builder()
                    .username("Administrador 2")
                    .email("admin2@gym.com")
                    .phone("+1234567893")
                    .password(passwordEncoder.encode("admin123"))
                    .rolId(1) // ADMIN
                    .build();
            usuarioRepository.save(admin2);
        }

        // Usuario Normal 2 (adicional)
        if (!usuarioRepository.findByEmail("user2@gym.com").isPresent()) {
            Usuario user2 = Usuario.builder()
                    .username("Usuario Ejemplo 2")
                    .email("user2@gym.com")
                    .phone("+1234567894")
                    .password(passwordEncoder.encode("user123"))
                    .rolId(2) // USER
                    .build();
            usuarioRepository.save(user2);
        }
    }
}

