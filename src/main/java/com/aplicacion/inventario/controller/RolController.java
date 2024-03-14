package com.aplicacion.inventario.controller;

import com.aplicacion.inventario.entity.RolEntity;
import com.aplicacion.inventario.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RolController {

    @Autowired
    RolRepository rolRepository;

    @GetMapping("/roles")
    public String listarRoles(Model model){
        List<RolEntity> listaRoles = rolRepository.findAll();
        model.addAttribute("listaRoles", listaRoles);
        return "roles";
    }
    @GetMapping("/roles/nuevo")
    public String formNuevoRol(Model model){
        model.addAttribute("rol", new RolEntity());
        return "form_rol";
    }
    @PostMapping("/roles/guardar")
    public String guardarRol(RolEntity rol){
        rolRepository.save(rol);
        return "redirect:/roles";
    }
    @GetMapping("/roles/editar/{id}")
    public String formActualizarRol(@PathVariable("id") Integer id, Model model){
        RolEntity rol = rolRepository.findById(id).get();
        model.addAttribute("rol", rol);
        return "form_editar_rol";
    }
    @GetMapping("roles/eliminar/{id}")
    public String eliminarRol(@PathVariable("id") Integer id){
        rolRepository.deleteById(id);
        return "redirect:/roles";
    }
}