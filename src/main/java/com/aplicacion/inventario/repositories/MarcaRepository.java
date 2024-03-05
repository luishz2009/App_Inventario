package com.aplicacion.inventario.repositories;

import com.aplicacion.inventario.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer> {
}
