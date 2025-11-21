package com.microservice.pagos.repository;

import com.microservice.pagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByUsuarioId(int usuarioId);
    List<Pago> findByOrdenId(int ordenId);
    List<Pago> findByEstado(String estado);
}

