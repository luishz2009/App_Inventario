package com.aplicacion.inventario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("")
    public String paginaDeInicio(){
        return "index";
    }
}
