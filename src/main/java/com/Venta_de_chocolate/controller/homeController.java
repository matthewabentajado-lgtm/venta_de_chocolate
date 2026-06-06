package com.Venta_de_chocolate.controller;

import com.Venta_de_chocolate.service.IChocolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private IChocolateService chocolateService;

    // Catálogo principal 
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("chocolates", chocolateService.listarChocolates());
        return "home/home"; 
    }

    // Tabla de administración 
    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        model.addAttribute("chocolates", chocolateService.listarChocolates());
        return "tabla/tabla"; 
    }
}