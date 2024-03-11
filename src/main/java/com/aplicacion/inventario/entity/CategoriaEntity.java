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
@Entity
@Table(name = "categorias")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    private String nombre;

    /*@ManyToOne //Muchas categorias pueden pertenecer a una marca
    @JoinColumn(name = "marca_id")
    private MarcaEntity marca;*/
    @ManyToMany(mappedBy = "categorias")
    private List<MarcaEntity> marcas = new ArrayList<>();


    public CategoriaEntity(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return  nombre;
    }
}
