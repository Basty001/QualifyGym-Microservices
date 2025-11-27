package com.microservice.carrito.service;

import com.microservice.carrito.model.Carrito;
import com.microservice.carrito.model.CarritoItem;
import com.microservice.carrito.repository.CarritoRepository;
import com.microservice.carrito.repository.CarritoItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@Transactional
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private CarritoItemRepository carritoItemRepository;

    public List<Carrito> findAll(){
        return carritoRepository.findAll();
    }

    public Optional<Carrito> getCarritoById(int id_carrito){
        return carritoRepository.findById(id_carrito);
    }
    
    public Optional<Carrito> getCarritoByUsuarioId(int usuarioId){
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    public Carrito save(Carrito carrito){
        if (carrito.getFechaCreacion() == null) {
            carrito.setFechaCreacion(LocalDateTime.now());
        }
        carrito.setFechaActualizacion(LocalDateTime.now());
        Carrito saved = carritoRepository.save(carrito);
        carritoRepository.flush(); // Forzar el flush para asegurar que se guarde
        return saved;
    }
    
    public CarritoItem saveItem(CarritoItem item){
        CarritoItem saved = carritoItemRepository.save(item);
        carritoItemRepository.flush(); // Forzar el flush para asegurar que se guarde
        return saved;
    }
    
    public void deleteItem(int id_item){
        carritoItemRepository.deleteById(id_item);
    }

    public void delete(int id_carrito){
        carritoRepository.deleteById(id_carrito);
    }
    
    public void deleteByUsuarioId(int usuarioId){
        Optional<Carrito> carrito = carritoRepository.findByUsuarioId(usuarioId);
        if (carrito.isPresent()) {
            carritoRepository.delete(carrito.get());
        }
    }
    
    public List<CarritoItem> getItemsByCarritoId(int carritoId){
        return carritoItemRepository.findByCarritoId(carritoId);
    }
}

