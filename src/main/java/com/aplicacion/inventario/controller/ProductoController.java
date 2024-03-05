package com.aplicacion.inventario.controller;

import com.aplicacion.inventario.entity.CategoriaEntity;
import com.aplicacion.inventario.entity.ProductoEntity;
import com.aplicacion.inventario.repositories.CategoriaRepository;
import com.aplicacion.inventario.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductoController {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/productos")
    public String listarProductos(Model model){
        List<ProductoEntity> listaProductos = productoRepository.findAll();
        model.addAttribute("listaProductos", listaProductos);
        return "productos";
    }
    @GetMapping("/productos/nuevo")
    public String formNuevoProducto(Model model){
        List<CategoriaEntity> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("productos", new ProductoEntity());
        model.addAttribute("listaCategorias", listaCategorias);
        return "form_producto";
    }
    @PostMapping("/productos/guardar")
    public String guardarProducto(ProductoEntity producto){
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String formActualizarProducto(@PathVariable("id") Integer id, Model model) {
        ProductoEntity producto = productoRepository.findById(id).get();

        List<CategoriaEntity> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        model.addAttribute("producto", producto);
        return "form_producto";
    }
    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id){
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }
}
