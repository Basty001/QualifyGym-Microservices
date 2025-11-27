package com.microservice.carrito.repository;

import com.microservice.carrito.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
    List<CarritoItem> findByCarritoId(int carritoId);
    void deleteByCarritoId(int carritoId);
}

