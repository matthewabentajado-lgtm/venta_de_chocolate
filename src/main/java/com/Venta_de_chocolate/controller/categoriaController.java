package com.Venta_de_chocolate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Venta_de_chocolate.model.Categoria;
import com.Venta_de_chocolate.service.ICategoriaService;

@Controller
@RequestMapping("/categorias")
public class categoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    // READ: Listar todas las categorías
    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "listaCategorias"; // Puedes crear esta vista o usar el Home
    }

    // CREATE (Formulario): Mostrar formulario de nueva categoría
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formCategoria";
    }

    // UPDATE (Formulario): Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria == null) {
            return "redirect:/categorias";
        }
        model.addAttribute("categoria", categoria);
        return "formCategoria";
    }

    // POST: Guardar o actualizar registro (Procesa el formulario)
    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.guardar(categoria);
        return "redirect:/"; // Redirige al catálogo principal tras guardar
    }

    // DELETE: Eliminar una categoría por ID
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return "redirect:/";
    }
}