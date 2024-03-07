package com.aplicacion.inventario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto_detalles")
public class ProductoDetallesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(length = 150)
    private String nombre;

    @Column(length = 50)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    public ProductoDetallesEntity(String nombre, String valor, ProductoEntity producto) {
        this.nombre = nombre;
        this.valor = valor;
        this.producto = producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + " - "+valor;
    }
}
