package com.aplicacion.inventario.repositories;

import com.aplicacion.inventario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Integer> {
}
