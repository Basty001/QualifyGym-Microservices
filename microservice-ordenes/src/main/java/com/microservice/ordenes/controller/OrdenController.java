package com.microservice.ordenes.controller;

import com.microservice.ordenes.model.Orden;
import com.microservice.ordenes.service.OrdenService;
import com.microservice.ordenes.dto.OrdenDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/ordenes")
@CrossOrigin(origins = "*")
public class OrdenController {
    @Autowired
    private OrdenService ordenService;

    @GetMapping("/listar")
    public List<Orden> getAllOrdenes(){
        return ordenService.findAll();
    }

    @GetMapping("/{id_orden}")
    public ResponseEntity<?> getOrdenById(@PathVariable Integer id_orden){
        Optional<Orden> orden = ordenService.getOrdenById(id_orden);
        if(orden.isPresent()){
            return ResponseEntity.ok().body(orden.get());
        } else {
            Map<String,String> errorBody = new HashMap<>();
            errorBody.put("message","No se encontró la orden con ese ID: " + id_orden);
            errorBody.put("status","404");
            errorBody.put("timestamp",LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody OrdenDTO ordenDTO){
        try{
            Orden orden = new Orden();
            orden.setUsuarioId(ordenDTO.getUsuarioId());
            orden.setTotal(ordenDTO.getTotal());
            orden.setEstado(ordenDTO.getEstado() != null ? ordenDTO.getEstado() : "PENDIENTE");
            Orden ordenGuardado = ordenService.save(orden);
            OrdenDTO responseDTO = new OrdenDTO();
            responseDTO.setId_orden(ordenGuardado.getId_orden());
            responseDTO.setUsuarioId(ordenGuardado.getUsuarioId());
            responseDTO.setTotal(ordenGuardado.getTotal());
            responseDTO.setEstado(ordenGuardado.getEstado());
            responseDTO.setFechaCreacion(ordenGuardado.getFechaCreacion());
            responseDTO.setFechaActualizacion(ordenGuardado.getFechaActualizacion());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ordenGuardado.getId_orden()).toUri();
            return ResponseEntity.created(location).body(responseDTO);
        } catch(Exception e){
            Map<String,String> error = new HashMap<>();
            error.put("message","Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{id_orden}")
    public ResponseEntity<OrdenDTO> update(@PathVariable int id_orden, @RequestBody OrdenDTO ordenDTO){
        try{
            Orden orden = new Orden();
            orden.setId_orden(id_orden);
            orden.setUsuarioId(ordenDTO.getUsuarioId());
            orden.setTotal(ordenDTO.getTotal());
            orden.setEstado(ordenDTO.getEstado());
            Orden ordenActualizado = ordenService.save(orden);
            OrdenDTO responseDTO = new OrdenDTO();
            responseDTO.setId_orden(ordenActualizado.getId_orden());
            responseDTO.setUsuarioId(ordenActualizado.getUsuarioId());
            responseDTO.setTotal(ordenActualizado.getTotal());
            responseDTO.setEstado(ordenActualizado.getEstado());
            responseDTO.setFechaCreacion(ordenActualizado.getFechaCreacion());
            responseDTO.setFechaActualizacion(ordenActualizado.getFechaActualizacion());
            return ResponseEntity.ok(responseDTO);
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id_orden}")
    public ResponseEntity<?> eliminar(@PathVariable int id_orden){
        try{
            ordenService.delete(id_orden);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> getOrdenesByUsuario(@PathVariable Integer usuarioId){
        try{
            List<Orden> ordenes = ordenService.findByUsuarioId(usuarioId);
            return ResponseEntity.ok().body(ordenes);
        } catch(Exception ex){
            Map<String,String> errorBody = new HashMap<>();
            errorBody.put("message","Error al obtener órdenes del usuario: " + ex.getMessage());
            errorBody.put("status","500");
            errorBody.put("timestamp",LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        }
    }
}

