package com.aplicacion.inventario.repositories;

import com.aplicacion.inventario.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
}
