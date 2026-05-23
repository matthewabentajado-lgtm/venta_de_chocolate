package com.Venta_de_chocolate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Venta_de_chocolate.model.chocolate;
import com.Venta_de_chocolate.repository.ChocolateRepository;


@Service
public class ChocolateService {
	
	@Autowired
    private ChocolateRepository chocolateRepository;

    public List<chocolate> listarTodos() {
        return chocolateRepository.findAll();
    }

    public Optional<chocolate> buscarPorId(Long id) {
        return chocolateRepository.findById(id);
    }

    public chocolate guardar(chocolate chocolate) {
        return chocolateRepository.save(chocolate);
    }

    public void eliminar(Long id) {
        chocolateRepository.deleteById(id);
    }

}
