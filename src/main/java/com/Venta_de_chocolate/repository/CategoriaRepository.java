package com.Venta_de_chocolate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Venta_de_chocolate.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}