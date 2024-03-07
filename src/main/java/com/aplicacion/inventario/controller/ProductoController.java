package com.aplicacion.inventario.controller;

import com.aplicacion.inventario.entity.CategoriaEntity;
import com.aplicacion.inventario.entity.ProductoDetallesEntity;
import com.aplicacion.inventario.entity.ProductoEntity;
import com.aplicacion.inventario.repositories.CategoriaRepository;
import com.aplicacion.inventario.repositories.DetalleRepository;
import com.aplicacion.inventario.repositories.ProductoRepository;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    DetalleRepository detalleRepository;

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
    public String guardarProducto(ProductoEntity producto, HttpServletRequest request){
        String[] detallesIds = request.getParameterValues("detallesId");
        String[] detallesNombres = request.getParameterValues("detallesNombre");
        String[] detallesValores = request.getParameterValues("detallesValor");

       /* for (int i = 0; i < detallesNombres.length; i++){
            if (detallesIds != null && detallesIds.length > 0){
                producto.setDetalle(Integer.valueOf(detallesIds[i]), detallesNombres[i], detallesValores[i]);
            }else {
                producto.agregarDetalles(detallesNombres[i], detallesValores[i]);
            }
        }*/
        if (detallesNombres != null && detallesValores != null){
            for (int i = 0; i < detallesNombres.length; i++){
                //Si existe un Id para el detalle, significa que ya existe en la bd y se debe actualizar
                if (detallesIds != null && detallesIds.length > i){
                    Integer detalleId = Integer.valueOf(detallesIds[i]);
                    ProductoDetallesEntity detalleExistente = detalleRepository.findById(detalleId).orElse(null);
                    if (detalleExistente != null){
                        detalleExistente.setNombre(detallesNombres[i]);
                        detalleExistente.setValor(detallesValores[i]);
                        detalleRepository.save(detalleExistente);
                    }
                }else {
                    //Sino existe un id para el detalle, significa que es un nuevo detalle y se debe agregar
                    producto.agregarDetalles(detallesNombres[i], detallesValores[i]);
                }
            }
        }
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String formActualizarProducto(@PathVariable("id") Integer id, Model model) {
        ProductoEntity producto = productoRepository.findById(id).get();

        List<CategoriaEntity> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        model.addAttribute("producto", producto);
        return "form_editar_producto";
    }
    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id){
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }
}
