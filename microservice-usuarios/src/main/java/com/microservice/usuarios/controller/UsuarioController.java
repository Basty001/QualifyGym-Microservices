package com.microservice.usuarios.controller;

import com.microservice.usuarios.model.Usuario;
import com.microservice.usuarios.service.UsuarioService;
import com.microservice.usuarios.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> getAllUsuarios(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id_usuario}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id_usuario){

        Optional<Usuario> usuario = usuarioService.getUsuarioById(id_usuario);

        if(usuario.isPresent()){
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId_usuario(usuario.get().getId_usuario());
            dto.setUsername(usuario.get().getUsername());
            dto.setEmail(usuario.get().getEmail());
            dto.setPhone(usuario.get().getPhone());
            dto.setRolId(usuario.get().getRolId());

            return ResponseEntity.ok()
                        .header("mi-encabezado","valor")
                        .body(usuario.get());
        }
        else{
            Map<String,String> errorBody = new HashMap<>();
            errorBody.put("message","No se encontró el usuario con ese ID: " + id_usuario);
            errorBody.put("status","404");
            errorBody.put("timestamp",LocalDateTime.now().toString());

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorBody);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UsuarioDTO usuarioDTO){
        try{
            if (usuarioService.existsByEmail(usuarioDTO.getEmail())) {
                Map<String,String> error = new HashMap<>();
                error.put("message","El email ya está registrado");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            }

            // Validar que los campos requeridos no estén vacíos
            if (usuarioDTO.getUsername() == null || usuarioDTO.getUsername().trim().isEmpty()) {
                Map<String,String> error = new HashMap<>();
                error.put("message","El username es obligatorio");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            if (usuarioDTO.getEmail() == null || usuarioDTO.getEmail().trim().isEmpty()) {
                Map<String,String> error = new HashMap<>();
                error.put("message","El email es obligatorio");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            if (usuarioDTO.getPassword() == null || usuarioDTO.getPassword().trim().isEmpty()) {
                Map<String,String> error = new HashMap<>();
                error.put("message","La contraseña es obligatoria");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            if (usuarioDTO.getPhone() == null || usuarioDTO.getPhone().trim().isEmpty()) {
                Map<String,String> error = new HashMap<>();
                error.put("message","El teléfono es obligatorio");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            
            // Validar formato del teléfono
            String phone = usuarioDTO.getPhone().trim();
            if (!phone.matches("^\\+?[0-9]{8,15}$")) {
                Map<String,String> error = new HashMap<>();
                error.put("message","El teléfono debe tener formato válido: 8-15 dígitos, puede empezar con + (ejemplo: +56912345678 o 912345678)");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            
            Usuario usuario = new Usuario();
            usuario.setUsername(usuarioDTO.getUsername().trim());
            usuario.setEmail(usuarioDTO.getEmail().trim().toLowerCase());
            usuario.setPhone(phone);
            usuario.setPassword(usuarioDTO.getPassword());
            usuario.setRolId(usuarioDTO.getRolId() > 0 ? usuarioDTO.getRolId() : 2); // Default rol 2 (Usuario)
        
            Usuario usuarioGuardado = usuarioService.save(usuario);
            
            // Verificar que se guardó correctamente
            if (usuarioGuardado.getId_usuario() == 0) {
                Map<String,String> error = new HashMap<>();
                error.put("message","Error al guardar el usuario");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
            }
        
            UsuarioDTO responseDTO = new UsuarioDTO();
            responseDTO.setId_usuario(usuarioGuardado.getId_usuario());
            responseDTO.setUsername(usuarioGuardado.getUsername());
            responseDTO.setEmail(usuarioGuardado.getEmail());
            responseDTO.setPhone(usuarioGuardado.getPhone());
            responseDTO.setRolId(usuarioGuardado.getRolId());
        
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(usuarioGuardado.getId_usuario())
                    .toUri();

            return ResponseEntity.created(location).body(responseDTO);

        } catch(DataIntegrityViolationException e){
            Map<String,String> error = new HashMap<>();
            error.put("message","Error de integridad de datos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        } catch(Exception e){
            Map<String,String> error = new HashMap<>();
            error.put("message","Error al crear usuario: " + e.getMessage());
            e.printStackTrace(); // Para debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{id_usuario}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable int id_usuario, @RequestBody UsuarioDTO usuarioDTO){
        try{
            Usuario usuario = new Usuario();
            usuario.setId_usuario(id_usuario);
            usuario.setUsername(usuarioDTO.getUsername());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setPhone(usuarioDTO.getPhone());
            if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
                usuario.setPassword(usuarioDTO.getPassword());
            }
            usuario.setRolId(usuarioDTO.getRolId());

            Usuario usuarioActualizado = usuarioService.save(usuario);
        
            UsuarioDTO responseDTO = new UsuarioDTO();
            responseDTO.setId_usuario(usuarioActualizado.getId_usuario());
            responseDTO.setUsername(usuarioActualizado.getUsername());
            responseDTO.setEmail(usuarioActualizado.getEmail());
            responseDTO.setPhone(usuarioActualizado.getPhone());
            responseDTO.setRolId(usuarioActualizado.getRolId());
            
            return ResponseEntity.ok(responseDTO);

        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<?> eliminar(@PathVariable int id_usuario){
        try{
            usuarioService.delete(id_usuario);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales){
        try{
            String email = credenciales.get("email");
            String password = credenciales.get("password");

            if (email == null || password == null) {
                Map<String,String> error = new HashMap<>();
                error.put("message","Email y contraseña son requeridos");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            Optional<Usuario> usuarioOpt = usuarioService.findByEmail(email);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                // Nota: En producción, deberías usar PasswordEncoder para verificar
                // Por ahora, comparación simple (no recomendado en producción)
                if (password.equals(usuario.getPassword()) || 
                    usuarioService.getPasswordEncoder().matches(password, usuario.getPassword())) {
                    UsuarioDTO dto = new UsuarioDTO();
                    dto.setId_usuario(usuario.getId_usuario());
                    dto.setUsername(usuario.getUsername());
                    dto.setEmail(usuario.getEmail());
                    dto.setPhone(usuario.getPhone());
                    dto.setRolId(usuario.getRolId());
                    return ResponseEntity.ok(dto);
                }
            }

            Map<String,String> error = new HashMap<>();
            error.put("message","Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

        }catch(Exception ex){
            Map<String,String> error = new HashMap<>();
            error.put("message","Error al procesar el login: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}

