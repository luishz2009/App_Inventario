package com.aplicacion.inventario.controller;

import com.aplicacion.inventario.entity.ProductoEntity;
import com.aplicacion.inventario.entity.ProductosUsuarioEntity;
import com.aplicacion.inventario.entity.UsuarioEntity;
import com.aplicacion.inventario.repositories.ProductoRepository;
import com.aplicacion.inventario.repositories.ProductosUsuarioRepository;
import com.aplicacion.inventario.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductosUsuarioController {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProductosUsuarioRepository productosUsuarioRepository;

    @GetMapping("/compras")
    public String listarComprasByUsuario(Model model){
        List<ProductosUsuarioEntity> listaCompras = productosUsuarioRepository.findAll();
        model.addAttribute("listaCompras", listaCompras);
        return "compras";
    }
    @GetMapping("/compras/nueva")
    public String formNuevaCompra(Model model){
        List<UsuarioEntity> listaUsuarios = usuarioRepository.findAll();
        List<ProductoEntity> listaProductos = productoRepository.findAll();

        ProductosUsuarioEntity nuevaCompra = new ProductosUsuarioEntity();
        model.addAttribute("listaUsuarios", listaUsuarios);
        model.addAttribute("listaProductos", listaProductos);
        model.addAttribute("compra", nuevaCompra);
        return "form_compra";
    }
    @PostMapping("/compras/guardar")
    public String guardarComprasByUsuario(@RequestParam ("usuarioId") Integer usuarioId,
                                          @RequestParam("productoId") Integer productoId,
                                          @RequestParam("cantidad") int cantidad,
                                          @RequestParam("prunit") float prunit){

        //float prtotal = cantidad * prunit; //Los tres se calculan directamente en form_compra.html
        //float iva = prtotal * 0.19;
        //foat totalneto = prtotal + iva;

        UsuarioEntity usuario = usuarioRepository.findById(usuarioId).orElse(null);
        ProductoEntity producto = productoRepository.findById(productoId).orElse(null);
        ProductosUsuarioEntity compra = new ProductosUsuarioEntity(cantidad, prunit);

        compra.setUsuario(usuario);
        compra.setProducto(producto);

        productosUsuarioRepository.save(compra);
        return "redirect:/compras";
    }

    @GetMapping("/compras/eliminar/{id}")
    public String eliminarCompra(@PathVariable("id") Integer id){
        productosUsuarioRepository.deleteById(id);
        return "redirect:/compras";
    }

}
