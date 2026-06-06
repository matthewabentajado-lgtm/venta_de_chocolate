package com.Venta_de_chocolate.controller;

import com.Venta_de_chocolate.model.Chocolate;
import com.Venta_de_chocolate.service.IChocolateService;
import com.Venta_de_chocolate.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChocolateController {

    @Autowired
    private IChocolateService chocolateService;

    @Autowired
    private ICategoriaService categoriaService;

    // Carga el formulario para registrar un nuevo chocolate
    @GetMapping("/nuevo")
    public String nuevoChocolate(Model model) {
        model.addAttribute("chocolate", new Chocolate());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("chocolates", chocolateService.listarChocolates()); 
        return "chocolate/formulario"; 
    }
    
    

    // Guarda el registro y vuelve a la tabla
    @PostMapping("/guardar")
    public String guardarChocolate(@ModelAttribute Chocolate chocolate) {
        chocolateService.guardarChocolate(chocolate);
        return "redirect:/tabla";
    }

    // Carga los datos de un chocolate existente en el formulario para modificarlo
    @GetMapping("/editar/{id}")
    public String editarChocolate(@PathVariable Integer id, Model model) {
        Chocolate chocolate = chocolateService.buscarPorId(id); 
        if (chocolate != null) {
            model.addAttribute("chocolate", chocolate);
            model.addAttribute("categorias", categoriaService.listarCategorias());
            model.addAttribute("chocolates", chocolateService.listarChocolates()); 
            return "chocolate/formulario";
        }
        return "redirect:/tabla";
    }

    // Elimina el registro y recarga la tabla
    @GetMapping("/eliminar/{id}")
    public String eliminarChocolate(@PathVariable Integer id) {
        chocolateService.eliminarChocolate(id); 
        return "redirect:/tabla";
    }
}