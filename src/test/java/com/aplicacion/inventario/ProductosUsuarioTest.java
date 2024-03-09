package com.aplicacion.inventario;

import com.aplicacion.inventario.entity.ProductoEntity;
import com.aplicacion.inventario.entity.ProductosUsuarioEntity;
import com.aplicacion.inventario.entity.UsuarioEntity;
import com.aplicacion.inventario.repositories.ProductosUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest //Para que ejecute pruebas de una entidad
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Para interactuar con la bd
@Rollback(value = false) //Para que no me haga retroceder una transaccion
public class ProductosUsuarioTest {

    @Autowired
    ProductosUsuarioRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testAgregarProducto(){
        ProductoEntity producto = entityManager.find(ProductoEntity.class, 65);
        UsuarioEntity usuario = entityManager.find(UsuarioEntity.class, 1);

        ProductosUsuarioEntity productosUsuario = new ProductosUsuarioEntity(2, producto, usuario);
        repository.save(productosUsuario);
    }
    @Test
    public void testAgregarProductosByUsuario(){
        UsuarioEntity usuario = new UsuarioEntity(8);
        ProductoEntity producto1 = new ProductoEntity(61);
        ProductoEntity producto2 = new ProductoEntity(55);

        ProductosUsuarioEntity productosUsuario1 = new ProductosUsuarioEntity(2,producto1, usuario);
        ProductosUsuarioEntity productosUsuario2 = new ProductosUsuarioEntity(3,producto2, usuario);
        repository.saveAll(List.of(productosUsuario1, productosUsuario2));
    }
    @Test
    public void testListarProductos(){
        List<ProductosUsuarioEntity> listaProductos = repository.findAll();
        listaProductos.forEach(System.out::println);
    }
    @Test
    public void testActualizarProducto(){
        ProductosUsuarioEntity productosUsuario = repository.findById(1).get();
        productosUsuario.setCantidad(10);
        productosUsuario.setProducto(new ProductoEntity(58));
    }
    @Test
    public void testEliminarProducto(){
        repository.deleteById(5);
    }

}
