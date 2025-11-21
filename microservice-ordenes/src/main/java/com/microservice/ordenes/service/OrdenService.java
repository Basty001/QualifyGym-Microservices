package com.microservice.ordenes.service;

import com.microservice.ordenes.model.Orden;
import com.microservice.ordenes.repository.OrdenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@Transactional
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> findAll(){
        return ordenRepository.findAll();
    }

    public Optional<Orden> getOrdenById(int id_orden){
        return ordenRepository.findById(id_orden);
    }

    public Orden save(Orden orden){
        if (orden.getFechaCreacion() == null) {
            orden.setFechaCreacion(LocalDateTime.now());
        }
        orden.setFechaActualizacion(LocalDateTime.now());
        return ordenRepository.save(orden);
    }

    public void delete(int id_orden){
        ordenRepository.deleteById(id_orden);
    }

    public List<Orden> findByUsuarioId(int usuarioId) {
        return ordenRepository.findByUsuarioId(usuarioId);
    }
}

