package com.aplicacion.inventario;

import com.aplicacion.inventario.entity.RolEntity;
import com.aplicacion.inventario.entity.UsuarioEntity;
import com.aplicacion.inventario.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest //Para que ejecute pruebas de una entidad
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Para interactuar con la bd
@Rollback(value = false) //Para que no me haga retroceder una transaccion
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testCrearRoles(){
        RolEntity rolAdmin = new RolEntity("Administrador");
        RolEntity rolEditor = new RolEntity("Editor");
        RolEntity rolVisitante = new RolEntity("Visitante");

        entityManager.persist(rolAdmin);
        entityManager.persist(rolEditor);
        entityManager.persist(rolVisitante);
    }
    @Test
    public void testCrearNuevoUsuarioConUnRol(){
        RolEntity rolEditor = entityManager.find(RolEntity.class, 2);
        UsuarioEntity usuario = new UsuarioEntity("serway@gmail.com", "serway2009");
        usuario.agregarRol(rolEditor);
        usuarioRepository.save(usuario);
    }
    @Test
    public void testCrearNuevoUsuarioConDosRoles(){
        RolEntity rolEditor = entityManager.find(RolEntity.class, 2);
        RolEntity rolVisitante = entityManager.find(RolEntity.class, 3);
        UsuarioEntity usuario = new UsuarioEntity("serway@gmail.com", "serway2009");
        usuario.agregarRol(rolEditor);
        usuario.agregarRol(rolVisitante);
        usuarioRepository.save(usuario);
    }
    @Test
    public void asignarRolAUsuarioExistente(){
        UsuarioEntity usuario = usuarioRepository.findById(1).get();
        RolEntity rolEditor = entityManager.find(RolEntity.class, 2);
        usuario.agregarRol(rolEditor);
    }
    @Test
    public void eliminarRolAUsuarioExistente(){
        UsuarioEntity usuario = usuarioRepository.findById(1).get();
        RolEntity rol = new RolEntity(2); //le pasamos el id del rol a eliminar
        usuario.eliminarRol(rol);
    }
    @Test
    public void crearNuevoUsuarioConNuevoRol(){
        RolEntity rolVendedor = new RolEntity("Vendedor");
        UsuarioEntity usuario = new UsuarioEntity("roberto@hotmail.com", "robert2024");
        usuario.agregarRol(rolVendedor);
        usuarioRepository.save(usuario);
    }
    @Test
    public void testObtenerUsuario(){
        UsuarioEntity usuario = usuarioRepository.findById(3).get();
        entityManager.detach(usuario); //detach para que los cambios de este usuario no se guarden en la bd
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getRoles());
    }

}
