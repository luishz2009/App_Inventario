package com.aplicacion.inventario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "marcas")
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String nombre;

   /* @OneToMany //Una marca puede tener muchas categorias
    @JoinColumn(name = "marca_id")
    private List<CategoriaEntity> categorias = new ArrayList<>(); //Esta es la que va en el formulario form_marca*/
    @ManyToMany
    @JoinTable(
            name = "marca_categoria",
            joinColumns = @JoinColumn(name = "marca_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<CategoriaEntity> categorias = new ArrayList<>();

    public MarcaEntity(Integer id) {
        this.id = id;
    }

    public MarcaEntity(String nombre, List<CategoriaEntity> categorias) {
        this.nombre = nombre;
        this.categorias = categorias;
    }

}
