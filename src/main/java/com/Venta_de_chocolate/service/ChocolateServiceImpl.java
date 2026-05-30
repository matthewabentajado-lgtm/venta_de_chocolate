package com.Venta_de_chocolate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Venta_de_chocolate.model.chocolate;
import com.Venta_de_chocolate.repository.ChocolateRepository;

@Service
public class ChocolateServiceImpl implements IChocolateService {

    @Autowired
    private ChocolateRepository chocolateRepository;

    @Override
    public List<chocolate> listarTodos() {
        return chocolateRepository.findAll();
    }

    @Override
    public chocolate buscarPorId(Long id) {
        return chocolateRepository.findById(id).orElse(null);
    }

    @Override
    public chocolate guardar(chocolate chocolate) {
        return chocolateRepository.save(chocolate);
    }

    @Override
    public void eliminar(Long id) {
        chocolateRepository.deleteById(id);
    }
}
