package com.aplicacion.inventario.controller;

import com.aplicacion.inventario.entity.CategoriaEntity;
import com.aplicacion.inventario.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CategoriaController {
    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public String listarCategorias(Model model){
        List<CategoriaEntity> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        return "categorias";
    }
    @GetMapping("/categorias/nuevo")
    public String formNuevaCategoria(Model model){
        model.addAttribute("categorias", new CategoriaEntity());
        return "form_categoria";
    }
    @PostMapping("/categorias/guardar")
    public String guardarCategoria(CategoriaEntity categoria){
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }
    @GetMapping("/categorias/editar/{id}")
    public String formActualizarCategoria(@PathVariable("id") Integer id, Model model){
        CategoriaEntity categoria = categoriaRepository.findById(id).get();
        model.addAttribute("categoria", categoria);
        return "form_editar_categoria";
    }

}
