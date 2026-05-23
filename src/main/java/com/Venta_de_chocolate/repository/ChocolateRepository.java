package com.Venta_de_chocolate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Venta_de_chocolate.model.chocolate;
public interface ChocolateRepository extends JpaRepository<chocolate, Long> {

}
