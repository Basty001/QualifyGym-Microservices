package com.microservice.carrito.repository;

import com.microservice.carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByUsuarioId(int usuarioId);
    List<Carrito> findAllByUsuarioId(int usuarioId);
}

