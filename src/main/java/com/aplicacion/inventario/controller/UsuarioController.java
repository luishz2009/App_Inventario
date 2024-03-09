package com.aplicacion.inventario.controller;

import com.aplicacion.inventario.entity.CategoriaEntity;
import com.aplicacion.inventario.entity.ProductoEntity;
import com.aplicacion.inventario.entity.RolEntity;
import com.aplicacion.inventario.entity.UsuarioEntity;
import com.aplicacion.inventario.repositories.RolRepository;
import com.aplicacion.inventario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model){
        List<UsuarioEntity> listaUsuarios = usuarioRepository.findAll();
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "usuarios";
    }
    @GetMapping("/usuarios/nuevo")
    public String formNuevoUsuario(Model model){
        List<RolEntity> listaRoles = rolRepository.findAll();
        model.addAttribute("usuarios", new UsuarioEntity());
        model.addAttribute("listaRoles", listaRoles);
        return "form_usuario";
    }
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(UsuarioEntity usuario){
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }
    @GetMapping("/usuarios/editar/{id}")
    public String formActualizarUsuario(@PathVariable("id") Integer id, Model model) {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        model.addAttribute("usuario", usuario);

        List<RolEntity> listaRoles = rolRepository.findAll();
        model.addAttribute("listaRoles", listaRoles);

        return "form_editar_usuario";
    }
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id){
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}
