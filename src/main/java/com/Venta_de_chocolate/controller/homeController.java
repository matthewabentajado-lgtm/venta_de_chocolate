package com.Venta_de_chocolate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Venta_de_chocolate.service.IChocolateService;
import com.Venta_de_chocolate.service.ICategoriaService;
import com.Venta_de_chocolate.model.Categoria;

@Controller
public class homeController {

    @Autowired
    private IChocolateService chocolateService;

    @Autowired
    private ICategoriaService categoriaService;

    // 1. Vista Principal (Catálogo)
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Catálogo de Chocolates Exclusivos");
        model.addAttribute("chocolates", chocolateService.listarTodos());
        model.addAttribute("categorias", categoriaService.listarTodas());
        // CORREGIDO: Tu archivo está en templates/home/home.html
        return "home/home"; 
    }

    // 2. Vista de Tabla (Inventario) - AQUÍ OCURRÍA EL ERROR DE TU IMAGEN
    @GetMapping("/inventario")
    public String tablaInventario(Model model) {
        model.addAttribute("chocolates", chocolateService.listarTodos());
        // CORREGIDO: Tu archivo está en templates/tabla/tabla.html
        // Al retornar "tabla/tabla" rompemos el bucle circular con la URL "/inventario"
        return "tabla/tabla"; 
    }

    // 3. Vista de Detalle Multimedia
    @GetMapping("/categoria/ver/{id}")
    public String verImagenCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria == null) {
            return "redirect:/";
        }
        model.addAttribute("categoria", categoria);
        // CORREGIDO: Tu archivo está en templates/imagen.html/imagen.html
        return "imagen.html/imagen"; 
    }
}