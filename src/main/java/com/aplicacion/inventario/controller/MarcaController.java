package com.aplicacion.inventario.controller;

import com.aplicacion.inventario.entity.CategoriaEntity;
import com.aplicacion.inventario.entity.MarcaEntity;
import com.aplicacion.inventario.repositories.CategoriaRepository;
import com.aplicacion.inventario.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MarcaController {
    @Autowired
    MarcaRepository marcaRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/marcas")
    public String listarMarcas(Model model){
        List<MarcaEntity> listaMarcas = marcaRepository.findAll();
        model.addAttribute("listaMarcas", listaMarcas);
        return "marcas";
    }

    @GetMapping("/marcas/nueva")
    public String formNuevaMarca(Model model){
        List<CategoriaEntity> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        model.addAttribute("marca", new MarcaEntity());
        return "form_marca";
    }
   /* @PostMapping("/marcas/guardar")
    public String guardarMarca(MarcaEntity marca){
        marcaRepository.save(marca);
        return "redirect:/marcas";
    }*/
    @PostMapping("/marcas/guardar")
    public String guardarMarca(@ModelAttribute MarcaEntity marca, @RequestParam("categorias") List<Integer> categoriasById){
        List<CategoriaEntity> categoriasSeleccionadas = new ArrayList<>();
        for (Integer categoriaId : categoriasById){
            CategoriaEntity categoria = categoriaRepository.findById(categoriaId).orElse(null);
            if (categoria != null){
                categoriasSeleccionadas.add(categoria);
            }
        }
        marca.setCategorias(categoriasSeleccionadas);
        marcaRepository.save(marca);
        return "redirect:/marcas";
    }
    @GetMapping("/marcas/editar/{id}")
    public String formActualizarMarca(@PathVariable("id") Integer id, Model model){
        MarcaEntity marca = marcaRepository.findById(id).get();
        model.addAttribute("marca", marca);

        List<CategoriaEntity> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        return "form_editar_marca";
    }
    @GetMapping("/marcas/eliminar/{id}")
    public String eliminarMarca(@PathVariable ("id") Integer id){
        marcaRepository.deleteById(id);
        return "redirect:/marcas";
    }
}
