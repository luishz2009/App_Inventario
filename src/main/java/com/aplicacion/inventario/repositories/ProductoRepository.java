package com.aplicacion.inventario.repositories;

import com.aplicacion.inventario.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
}
