package com.aplicacion.inventario.repositories;

import com.aplicacion.inventario.entity.ProductoDetallesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleRepository extends JpaRepository<ProductoDetallesEntity, Integer> {
}
