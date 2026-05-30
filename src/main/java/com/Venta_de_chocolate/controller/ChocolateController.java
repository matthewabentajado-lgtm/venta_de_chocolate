package com.Venta_de_chocolate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Venta_de_chocolate.model.chocolate;
import com.Venta_de_chocolate.service.IChocolateService;
import com.Venta_de_chocolate.service.ICategoriaService;

@Controller
@RequestMapping("/chocolates")
public class chocolateController {

    @Autowired
    private IChocolateService chocolateService;

    @Autowired
    private ICategoriaService categoriaService;


    @GetMapping
    public String listarChocolates(Model model) {
        model.addAttribute("chocolates", chocolateService.listarTodos());
        return "tabla";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("chocolate", new chocolate());
        model.addAttribute("listaCategorias", categoriaService.listarTodas());
        return "formchocolate";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        chocolate choc = chocolateService.buscarPorId(id);
        if (choc == null) {
            return "redirect:/chocolates";
        }
        model.addAttribute("chocolate", choc);
        model.addAttribute("listaCategorias", categoriaService.listarTodas());
        return "formchocolate";
    }

    @PostMapping("/guardar")
    public String guardarChocolate(@ModelAttribute("chocolate") chocolate choc) {
        chocolateService.guardar(choc);
        return "redirect:/chocolates"; 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarChocolate(@PathVariable Long id) {
        chocolateService.eliminar(id);
        return "redirect:/chocolates";
    }


   

    // 1. ACTIVAR GUARDAR
    // URL: http://localhost:8080/chocolates/guardar-test
    @GetMapping("/guardar-test")
    @ResponseBody
    public String activarGuardar() {
        chocolate nuevo = new chocolate();
        // Forzamos el ID 1. Si ya existe, JPA hará un update; si no, lo insertará.
        nuevo.setId(1L); 
        nuevo.setNombre("chocolate con almendras");
        nuevo.setDescripcion("chocltes con trozos con almendras");
        nuevo.setPrecio(2.50);
        nuevo.setStock(30);

        chocolateService.guardar(nuevo);
        return "<h1>¡Guardar Activado!</h1><p>Se registró 'chocolate con almendras' con ID 1 en la base de datos.</p>";
    }

    // 2. ACTIVAR LEVANTAR / BUSCAR (UP)
    // URL: http://localhost:8080/chocolates/cargar-test
    @GetMapping("/cargar-test")
    @ResponseBody
    public String activarLevantar() {
        // Levantamos el registro con ID 1 de la base de datos
        chocolate encontrado = chocolateService.buscarPorId(1L);
        
        if (encontrado == null) {
            return "<h1>Error</h1><p>No se encontró ningún chocolate con el ID 1 en la base de datos. Primero ejecuta la ruta de guardar.</p>";
        }

        return "<h1>¡Registro Levantado (Up) con Éxito!</h1>" +
               "<ul>" +
               "<li><b>ID:</b> " + encontrado.getId() + "</li>" +
               "<li><b>Nombre:</b> " + encontrado.getNombre() + "</li>" +
               "<li><b>Descripción:</b> " + encontrado.getDescripcion() + "</li>" +
               "<li><b>Precio:</b> $" + encontrado.getPrecio() + "</li>" +
               "<li><b>Stock:</b> " + encontrado.getStock() + "</li>" +
               "</ul>";
    }

    // 3. ACTIVAR MODIFICAR
    // URL: http://localhost:8080/chocolates/modificar-test
    @GetMapping("/modificar-test")
    @ResponseBody
    public String activarModificar() {
        // Primero levantamos el registro existente por ID para asegurar su estado
        chocolate aModificar = chocolateService.buscarPorId(1L);

        if (aModificar == null) {
            // Si por alguna razón no existe, creamos el objeto con ID 1
            aModificar = new chocolate();
            aModificar.setId(1L);
        }

        // Cambiamos los valores a los solicitados
        aModificar.setNombre("chocolate con crispy");
        aModificar.setDescripcion("chocolate con trosos de crispy");
        aModificar.setPrecio(3.00); // Actualización opcional de precio
        aModificar.setStock(15);    // Actualización opcional de stock

        // Al guardar un objeto que ya contiene un ID existente en la BD, Spring Data hace el UPDATE
        chocolateService.guardar(aModificar);

        return "<h1>¡Modificar Activado!</h1><p>El registro con ID 1 ha sido actualizado a 'chocolate con crispy' en la base de datos.</p>";
    }

    // 4. ACTIVAR ELIMINAR
    // URL: http://localhost:8080/chocolates/eliminar-test
    @GetMapping("/eliminar-test")
    @ResponseBody
    public String activarEliminar() {
        chocolate verif = chocolateService.buscarPorId(1L);
        
        if (verif == null) {
            return "<h1>Error al eliminar</h1><p>El registro con ID 1 no existe en la base de datos.</p>";
        }

        // Eliminación física por ID
        chocolateService.eliminar(1L);
        return "<h1>¡Eliminar Activado!</h1><p>El chocolate con ID 1 fue removido de la base de datos por completo.</p>";
    }
}