package com.microservice.carrito.repository;

import com.microservice.carrito.model.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
    @Query("SELECT ci FROM CarritoItem ci WHERE ci.carrito.id_carrito = :carritoId")
    List<CarritoItem> findByCarritoId(@Param("carritoId") int carritoId);
    
    @Modifying
    @Query("DELETE FROM CarritoItem ci WHERE ci.carrito.id_carrito = :carritoId")
    void deleteByCarritoId(@Param("carritoId") int carritoId);
}

