package com.Venta_de_chocolate.controller;

import com.Venta_de_chocolate.model.Categoria;
import com.Venta_de_chocolate.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    // Muestra el formulario y la lista de categorías al mismo tiempo
    @GetMapping("/nueva")
    public String nuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("categorias", categoriaService.listarCategorias()); 
        return "categoria/Categoria";
    }

    // Guarda o actualiza la categoría
    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categoria/nueva";
    }

    // Carga los datos al formulario para edición
    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id); 
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("categorias", categoriaService.listarCategorias());
            return "categoria/Categoria";
        }
        return "redirect:/categoria/nueva";
    }

    // Elimina la categoría
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id) {
        try {
            categoriaService.eliminarCategoria(id); 
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
        return "redirect:/categoria/nueva";
    }
}