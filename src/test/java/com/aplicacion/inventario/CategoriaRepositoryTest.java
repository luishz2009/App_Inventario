package com.aplicacion.inventario;

import com.aplicacion.inventario.entity.CategoriaEntity;
import com.aplicacion.inventario.repositories.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest //Para que ejecute pruebas de una entidad
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Para interactuar con la bd
@Rollback(value = false) //Para que no me haga retroceder una transaccion
public class CategoriaRepositoryTest {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Test
    public void testCrearCategoria(){
        CategoriaEntity nuevaCategoria = categoriaRepository.save(new CategoriaEntity("Electrónicos"));
        assertThat(nuevaCategoria.getId()).isGreaterThan(0);
    }
}
