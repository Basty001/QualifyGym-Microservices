package com.qualifygym.productos.service;

import com.qualifygym.productos.model.Producto;
import com.qualifygym.productos.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public List<Producto> findActivos(){
        return productoRepository.findByActivoTrue();
    }

    public Optional<Producto> getProductoById(int id_producto){
        return productoRepository.findById(id_producto);
    }

    public Producto save(Producto producto){
        Producto saved = productoRepository.save(producto);
        productoRepository.flush(); // Forzar el flush para asegurar que se guarde
        return saved;
    }

    public void delete(int id_producto){
        productoRepository.deleteById(id_producto);
    }

    public List<Producto> findByCategoriaId(int categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
}

