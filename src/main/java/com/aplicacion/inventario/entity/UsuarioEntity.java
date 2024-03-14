package com.aplicacion.inventario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    private String password;
    /*
        Un usuario puede tener muchos roles
        y un rol puede tener muchos usuarios
         */
    //fetch= para que al listar un objeto tembien te liste a sus objetos relacionados
    //FetchType.EAGER = es para que esté pendiente cuando se invoca al objeto
    //Nota: el fetch se necesita para poder hacer las pruebas unitarias
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolEntity> roles = new HashSet<>();

    public UsuarioEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UsuarioEntity(Integer id) {
        this.id = id;
    }

    public void agregarRol(RolEntity rol){
        this.roles.add(rol);
    }

    //Para que este método funcione hay que generar el equals y el hashCode en RolEntity
    //Este método se utiliza en las pruebas unitarias
    public void eliminarRol(RolEntity rol){
        this.roles.remove(rol);
    }

    @Override
    public String toString() {
        return  email;
    }
}
