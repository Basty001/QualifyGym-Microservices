package com.microservice.pagos.controller;

import com.microservice.pagos.model.Pago;
import com.microservice.pagos.service.PagoService;
import com.microservice.pagos.dto.PagoDTO;
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
@RequestMapping("/api/v1/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @GetMapping("/listar")
    public List<Pago> getAllPagos(){
        return pagoService.findAll();
    }

    @GetMapping("/{id_pago}")
    public ResponseEntity<?> getPagoById(@PathVariable Integer id_pago){
        Optional<Pago> pago = pagoService.getPagoById(id_pago);
        if(pago.isPresent()){
            return ResponseEntity.ok().body(pago.get());
        } else {
            Map<String,String> errorBody = new HashMap<>();
            errorBody.put("message","No se encontró el pago con ese ID: " + id_pago);
            errorBody.put("status","404");
            errorBody.put("timestamp",LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PagoDTO pagoDTO){
        try{
            Pago pago = new Pago();
            pago.setOrdenId(pagoDTO.getOrdenId());
            pago.setUsuarioId(pagoDTO.getUsuarioId());
            pago.setMonto(pagoDTO.getMonto());
            pago.setMetodoPago(pagoDTO.getMetodoPago());
            pago.setEstado(pagoDTO.getEstado() != null ? pagoDTO.getEstado() : "PENDIENTE");
            pago.setInformacionAdicional(pagoDTO.getInformacionAdicional());
            Pago pagoGuardado = pagoService.save(pago);
            PagoDTO responseDTO = new PagoDTO();
            responseDTO.setId_pago(pagoGuardado.getId_pago());
            responseDTO.setOrdenId(pagoGuardado.getOrdenId());
            responseDTO.setUsuarioId(pagoGuardado.getUsuarioId());
            responseDTO.setMonto(pagoGuardado.getMonto());
            responseDTO.setMetodoPago(pagoGuardado.getMetodoPago());
            responseDTO.setEstado(pagoGuardado.getEstado());
            responseDTO.setFechaPago(pagoGuardado.getFechaPago());
            responseDTO.setInformacionAdicional(pagoGuardado.getInformacionAdicional());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pagoGuardado.getId_pago()).toUri();
            return ResponseEntity.created(location).body(responseDTO);
        } catch(Exception e){
            Map<String,String> error = new HashMap<>();
            error.put("message","Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{id_pago}")
    public ResponseEntity<PagoDTO> update(@PathVariable int id_pago, @RequestBody PagoDTO pagoDTO){
        try{
            Pago pago = new Pago();
            pago.setId_pago(id_pago);
            pago.setOrdenId(pagoDTO.getOrdenId());
            pago.setUsuarioId(pagoDTO.getUsuarioId());
            pago.setMonto(pagoDTO.getMonto());
            pago.setMetodoPago(pagoDTO.getMetodoPago());
            pago.setEstado(pagoDTO.getEstado());
            pago.setInformacionAdicional(pagoDTO.getInformacionAdicional());
            Pago pagoActualizado = pagoService.save(pago);
            PagoDTO responseDTO = new PagoDTO();
            responseDTO.setId_pago(pagoActualizado.getId_pago());
            responseDTO.setOrdenId(pagoActualizado.getOrdenId());
            responseDTO.setUsuarioId(pagoActualizado.getUsuarioId());
            responseDTO.setMonto(pagoActualizado.getMonto());
            responseDTO.setMetodoPago(pagoActualizado.getMetodoPago());
            responseDTO.setEstado(pagoActualizado.getEstado());
            responseDTO.setFechaPago(pagoActualizado.getFechaPago());
            responseDTO.setInformacionAdicional(pagoActualizado.getInformacionAdicional());
            return ResponseEntity.ok(responseDTO);
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id_pago}")
    public ResponseEntity<?> eliminar(@PathVariable int id_pago){
        try{
            pagoService.delete(id_pago);
            return ResponseEntity.noContent().build();
        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
}

