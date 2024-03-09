package com.aplicacion.inventario.repositories;

import com.aplicacion.inventario.entity.ProductosUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosUsuarioRepository extends JpaRepository <ProductosUsuarioEntity, Integer> {
}
