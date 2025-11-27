package com.microservice.usuarios.service;

import com.microservice.usuarios.model.Usuario;
import com.microservice.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(int id_usuario){
        return usuarioRepository.findById(id_usuario);
    }

    public Usuario save(Usuario usuario){
        try {
            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                // Solo codificar si no está ya codificado (no empieza con $2a$)
                if (!usuario.getPassword().startsWith("$2a$")) {
                    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
                }
            }
            Usuario saved = usuarioRepository.save(usuario);
            usuarioRepository.flush(); // Forzar el flush para asegurar que se guarde
            // Verificar que se guardó correctamente
            if (saved.getId_usuario() > 0) {
                System.out.println("Usuario guardado con ID: " + saved.getId_usuario());
            }
            return saved;
        } catch (Exception e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(int id_usuario){
        usuarioRepository.deleteById(id_usuario);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}

