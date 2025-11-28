package com.qualifygym.ordenes.repository;

import com.qualifygym.ordenes.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    List<Orden> findByUsuarioId(int usuarioId);
    List<Orden> findByEstado(String estado);
}

