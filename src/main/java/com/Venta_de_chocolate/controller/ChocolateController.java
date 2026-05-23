package com.Venta_de_chocolate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Venta_de_chocolate.model.chocolate;
import com.Venta_de_chocolate.service.ChocolateService;

public class ChocolateController {
	
	@Autowired
    private ChocolateService chocolateService;

    @GetMapping
    public List<chocolate> obtenerTodos() {
        return chocolateService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<chocolate> obtenerPorId(@PathVariable Long id) {
        return chocolateService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public chocolate crear(@RequestBody chocolate chocolate) {
        return chocolateService.guardar(chocolate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<chocolate> actualizar(@PathVariable Long id, @RequestBody chocolate detallesChocolate) {
        return chocolateService.buscarPorId(id).map(chocolate -> {
            chocolate.setNombre(detallesChocolate.getNombre());
            chocolate.setPrecio(detallesChocolate.getPrecio());
            chocolate.setStock(detallesChocolate.getStock());
            Chocolate actualizado = chocolateService.guardar(chocolate);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (chocolateService.buscarPorId(id).isPresent()) {
            chocolateService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
