package com.microservice.ordenes.repository;

import com.microservice.ordenes.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    List<Orden> findByUsuarioId(int usuarioId);
    List<Orden> findByEstado(String estado);
}

