package com.Venta_de_chocolate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Venta_de_chocolate.model.chocolate;
import com.Venta_de_chocolate.service.ICategoriaService;
import com.Venta_de_chocolate.service.IChocolateService;

@Controller
@RequestMapping("/chocolates")
public class ChocolateController {

    @Autowired
    private IChocolateService chocolateService;

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping
    public String listarChocolates(Model model) {
        model.addAttribute("chocolates", chocolateService.listarTodos());
        return "tabla/tabla"; 
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("chocolate", new chocolate());
        // ENVIAR EL CATÁLOGO: Es vital para llenar el combobox select del formulario
        model.addAttribute("listaCategorias", categoriaService.listarTodas());
        return "chocolate/Chocolate"; 
    }

    @PostMapping("/guardar")
    public String guardarChocolate(@ModelAttribute("chocolate") chocolate choc) {
        chocolateService.guardar(choc);
        return "redirect:/inventario"; 
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        chocolate choc = chocolateService.buscarPorId(id);
        if (choc == null) {
            return "redirect:/inventario";
        }
        model.addAttribute("chocolate", choc);
        model.addAttribute("listaCategorias", categoriaService.listarTodas());
        return "chocolate/Chocolate"; 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarChocolate(@PathVariable Long id) {
        chocolateService.eliminar(id);
        return "redirect:/inventario";
    }
}