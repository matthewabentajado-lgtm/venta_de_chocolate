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
public class HomeController {

    @Autowired
    private IChocolateService chocolateService;

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Catálogo de Chocolates Exclusivos");
        model.addAttribute("chocolates", chocolateService.listarTodos());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "home/home"; 
    }

    // CORRECCIÓN AQUÍ: Cambiamos "tabla/tabla" por "tabla/tabla" si mueves el archivo, 
    // o simplemente "tabla" si tu archivo está suelto en templates.
    @GetMapping("/inventario")
    public String tablaInventario(Model model) {
        model.addAttribute("chocolates", chocolateService.listarTodos());
        return "tabla/tabla"; 
    }

    @GetMapping("/categoria/ver/{id}")
    public String verImagenCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria == null) {
            return "redirect:/";
        }
        model.addAttribute("categoria", categoria);
        return "imagen.html/imagen"; 
    }
}