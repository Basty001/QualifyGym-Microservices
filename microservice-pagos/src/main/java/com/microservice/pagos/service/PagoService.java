package com.microservice.pagos.service;

import com.microservice.pagos.model.Pago;
import com.microservice.pagos.repository.PagoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
@Transactional
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> findAll(){
        return pagoRepository.findAll();
    }

    public Optional<Pago> getPagoById(int id_pago){
        return pagoRepository.findById(id_pago);
    }

    public Pago save(Pago pago){
        if (pago.getFechaPago() == null) {
            pago.setFechaPago(LocalDateTime.now());
        }
        Pago saved = pagoRepository.save(pago);
        pagoRepository.flush(); // Forzar el flush para asegurar que se guarde
        return saved;
    }

    public void delete(int id_pago){
        pagoRepository.deleteById(id_pago);
    }

    public List<Pago> findByUsuarioId(int usuarioId) {
        return pagoRepository.findByUsuarioId(usuarioId);
    }

    public List<Pago> findByOrdenId(int ordenId) {
        return pagoRepository.findByOrdenId(ordenId);
    }
}

