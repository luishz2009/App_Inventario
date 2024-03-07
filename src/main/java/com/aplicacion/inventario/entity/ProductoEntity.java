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
@Table(name = "productos")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 150, nullable = false, unique = true)
    private String nombre;
    private float precio;

    /*
     muchos productos pueden pertenecer a una categoria
     */
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ProductoDetallesEntity> listaDetalles = new ArrayList<>();

    public ProductoEntity(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
    public List<ProductoDetallesEntity> getListaDetalles() {
        return listaDetalles;
    }
    public void setListaDetalles(List<ProductoDetallesEntity> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    //Vamos a crear un método para agregar los detalles
    public void agregarDetalles(String nombre, String valor){
        this.listaDetalles.add(new ProductoDetallesEntity(nombre, valor, this));
    }

    public void setDetalle(Integer id, String nombre, String valor){
        this.listaDetalles.add(new ProductoDetallesEntity(id, nombre, valor, this));
    }
}
