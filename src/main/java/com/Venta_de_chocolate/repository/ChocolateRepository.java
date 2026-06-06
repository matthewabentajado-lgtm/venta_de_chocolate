package com.Venta_de_chocolate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Venta_de_chocolate.model.Chocolate;

@Repository
public interface ChocolateRepository extends JpaRepository<Chocolate, Integer> {
    
}