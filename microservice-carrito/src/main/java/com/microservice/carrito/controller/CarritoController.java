package com.microservice.carrito.controller;

import com.microservice.carrito.model.Carrito;
import com.microservice.carrito.model.CarritoItem;
import com.microservice.carrito.service.CarritoService;
import com.microservice.carrito.dto.CarritoDTO;
import com.microservice.carrito.dto.CarritoItemDTO;
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
import java.util.stream.Collectors;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @GetMapping("/listar")
    public List<CarritoDTO> getAllCarritos(){
        return carritoService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id_carrito}")
    public ResponseEntity<?> getCarritoById(@PathVariable Integer id_carrito){
        Optional<Carrito> carrito = carritoService.getCarritoById(id_carrito);
        if(carrito.isPresent()){
            return ResponseEntity.ok().body(convertToDTO(carrito.get()));
        } else {
            Map<String,String> errorBody = new HashMap<>();
            errorBody.put("message","No se encontró el carrito con ese ID: " + id_carrito);
            errorBody.put("status","404");
            errorBody.put("timestamp",LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> getCarritoByUsuario(@PathVariable Integer usuarioId){
        try{
            Optional<Carrito> carrito = carritoService.getCarritoByUsuarioId(usuarioId);
            if(carrito.isPresent()){
                return ResponseEntity.ok().body(convertToDTO(carrito.get()));
            } else {
                Map<String,String> errorBody = new HashMap<>();
                errorBody.put("message","No se encontró carrito para el usuario: " + usuarioId);
                errorBody.put("status","404");
                errorBody.put("timestamp",LocalDateTime.now().toString());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
            }
        } catch(Exception ex){
            Map<String,String> errorBody = new HashMap<>();
            errorBody.put("message","Error al obtener carrito: " + ex.getMessage());
            errorBody.put("status","500");
            errorBody.put("timestamp",LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CarritoDTO carritoDTO){
        try{
            Carrito carrito = new Carrito();
            carrito.setUsuarioId(carritoDTO.getUsuarioId());
            Carrito carritoGuardado = carritoService.save(carrito);
            CarritoDTO responseDTO = convertToDTO(carritoGuardado);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(carritoGuardado.getId_carrito())
                    .toUri();
            return ResponseEntity.created(location).body(responseDTO);
        } catch(Exception e){
            Map<String,String> error = new HashMap<>();
            error.put("message","Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
    
    @PostMapping("/{id_carrito}/items")
    public ResponseEntity<?> addItem(@PathVariable int id_carrito, @Valid @RequestBody CarritoItemDTO itemDTO){
        try{
            Optional<Carrito> carritoOpt = carritoService.getCarritoById(id_carrito);
            if(!carritoOpt.isPresent()){
                Map<String,String> error = new HashMap<>();
                error.put("message","Carrito no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            
            CarritoItem item = new CarritoItem();
            item.setCarrito(carritoOpt.get());
            item.setProductoId(itemDTO.getProductoId());
            item.setCantidad(itemDTO.getCantidad());
            item.setPrecio(itemDTO.getPrecio());
            
            CarritoItem itemGuardado = carritoService.saveItem(item);
            CarritoItemDTO responseDTO = convertItemToDTO(itemGuardado);
            
            return ResponseEntity.ok().body(responseDTO);
        } catch(Exception e){
            Map<String,String> error = new HashMap<>();
            error.put("message","Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{id_carrito}")
    public ResponseEntity<CarritoDTO> update(@PathVariable int id_carrito, @RequestBody CarritoDTO carritoDTO){
        try{
            Optional<Carrito> carritoOpt = carritoService.getCarritoById(id_carrito);
            if(!carritoOpt.isPresent()){
                return ResponseEntity.notFound().build();
            }
            
            Carrito carrito = carritoOpt.get();
            carrito.setUsuarioId(carritoDTO.getUsuarioId());
            Carrito carritoActualizado = carritoService.save(carrito);
            return ResponseEntity.ok(convertToDTO(carritoActualizado));
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id_carrito}")
    public ResponseEntity<?> eliminar(@PathVariable int id_carrito){
        try{
            carritoService.delete(id_carrito);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> eliminarPorUsuario(@PathVariable int usuarioId){
        try{
            carritoService.deleteByUsuarioId(usuarioId);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/items/{id_item}")
    public ResponseEntity<?> eliminarItem(@PathVariable int id_item){
        try{
            carritoService.deleteItem(id_item);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    private CarritoDTO convertToDTO(Carrito carrito){
        CarritoDTO dto = new CarritoDTO();
        dto.setId_carrito(carrito.getId_carrito());
        dto.setUsuarioId(carrito.getUsuarioId());
        dto.setFechaCreacion(carrito.getFechaCreacion());
        dto.setFechaActualizacion(carrito.getFechaActualizacion());
        if(carrito.getItems() != null){
            dto.setItems(carrito.getItems().stream()
                    .map(this::convertItemToDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
    
    private CarritoItemDTO convertItemToDTO(CarritoItem item){
        CarritoItemDTO dto = new CarritoItemDTO();
        dto.setId_item(item.getId_item());
        dto.setProductoId(item.getProductoId());
        dto.setCantidad(item.getCantidad());
        dto.setPrecio(item.getPrecio());
        return dto;
    }
}

