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

    //Nota: en la bd hay que crear un índice con el campo nombre y crearlo como INDEX
    //para que al crear o editar un producto no saque errores o datos duplicados
    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
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
