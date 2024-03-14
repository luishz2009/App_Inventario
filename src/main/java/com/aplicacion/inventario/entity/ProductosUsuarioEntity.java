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
@Table(name = "productos_usuario")
public class ProductosUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private int cantidad;
    private float prunit;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public ProductosUsuarioEntity(int cantidad, ProductoEntity producto, UsuarioEntity usuario) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.usuario = usuario;
    }

    public ProductosUsuarioEntity(int cantidad, float prunit) {
        this.cantidad = cantidad;
        this.prunit = prunit;
    }

    @Override
    public String toString() {
        return "ProductosUsuarioEntity{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", prunit=" + prunit +
                ", producto=" + producto +
                ", usuario=" + usuario +
                '}';
    }
}
