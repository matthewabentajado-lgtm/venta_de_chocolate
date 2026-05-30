package com.Venta_de_chocolate.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Venta_de_chocolate.model.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long> {
	
	 


}
