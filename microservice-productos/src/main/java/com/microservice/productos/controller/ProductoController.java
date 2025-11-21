package com.microservice.productos.controller;

import com.microservice.productos.model.Producto;
import com.microservice.productos.service.ProductoService;
import com.microservice.productos.dto.ProductoDTO;
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
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> getAllProductos(){
        return productoService.findAll();
    }

    @GetMapping("/activos")
    public List<Producto> getProductosActivos(){
        return productoService.findActivos();
    }

    @GetMapping("/{id_producto}")
    public ResponseEntity<?> getProductoById(@PathVariable Integer id_producto){
        Optional<Producto> producto = productoService.getProductoById(id_producto);
        if(producto.isPresent()){
            return ResponseEntity.ok().body(producto.get());
        } else {
            Map<String,String> errorBody = new HashMap<>();
            errorBody.put("message","No se encontró el producto con ese ID: " + id_producto);
            errorBody.put("status","404");
            errorBody.put("timestamp",LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductoDTO productoDTO){
        try{
            Producto producto = new Producto();
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setImagen(productoDTO.getImagen());
            producto.setStock(productoDTO.getStock());
            producto.setCategoriaId(productoDTO.getCategoriaId());
            producto.setActivo(productoDTO.getActivo() != null ? productoDTO.getActivo() : true);
        
            Producto productoGuardado = productoService.save(producto);
        
            ProductoDTO responseDTO = new ProductoDTO();
            responseDTO.setId_producto(productoGuardado.getId_producto());
            responseDTO.setNombre(productoGuardado.getNombre());
            responseDTO.setDescripcion(productoGuardado.getDescripcion());
            responseDTO.setPrecio(productoGuardado.getPrecio());
            responseDTO.setImagen(productoGuardado.getImagen());
            responseDTO.setStock(productoGuardado.getStock());
            responseDTO.setCategoriaId(productoGuardado.getCategoriaId());
            responseDTO.setActivo(productoGuardado.getActivo());
        
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(productoGuardado.getId_producto())
                    .toUri();

            return ResponseEntity.created(location).body(responseDTO);
        } catch(DataIntegrityViolationException e){
            Map<String,String> error = new HashMap<>();
            error.put("message","Error de integridad de datos: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @PutMapping("/{id_producto}")
    public ResponseEntity<ProductoDTO> update(@PathVariable int id_producto, @RequestBody ProductoDTO productoDTO){
        try{
            Producto producto = new Producto();
            producto.setId_producto(id_producto);
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setImagen(productoDTO.getImagen());
            producto.setStock(productoDTO.getStock());
            producto.setCategoriaId(productoDTO.getCategoriaId());
            producto.setActivo(productoDTO.getActivo());

            Producto productoActualizado = productoService.save(producto);
        
            ProductoDTO responseDTO = new ProductoDTO();
            responseDTO.setId_producto(productoActualizado.getId_producto());
            responseDTO.setNombre(productoActualizado.getNombre());
            responseDTO.setDescripcion(productoActualizado.getDescripcion());
            responseDTO.setPrecio(productoActualizado.getPrecio());
            responseDTO.setImagen(productoActualizado.getImagen());
            responseDTO.setStock(productoActualizado.getStock());
            responseDTO.setCategoriaId(productoActualizado.getCategoriaId());
            responseDTO.setActivo(productoActualizado.getActivo());
            
            return ResponseEntity.ok(responseDTO);
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id_producto}")
    public ResponseEntity<?> eliminar(@PathVariable int id_producto){
        try{
            productoService.delete(id_producto);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}

