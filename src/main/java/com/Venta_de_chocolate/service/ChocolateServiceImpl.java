package com.Venta_de_chocolate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Venta_de_chocolate.model.Chocolate;
import com.Venta_de_chocolate.repository.ChocolateRepository;

@Service
public class ChocolateServiceImpl implements IChocolateService {

    @Autowired
    private ChocolateRepository chocolateRepo;

    @Override
    public List<Chocolate> listarChocolates() {
        return chocolateRepo.findAll();
    }

    @Override
    public void guardarChocolate(Chocolate chocolate) {
        chocolateRepo.save(chocolate);
    }

    @Override
    public Chocolate buscarPorId(Integer id) {
        return chocolateRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminarChocolate(Integer id) {
        chocolateRepo.deleteById(id);
    }
}