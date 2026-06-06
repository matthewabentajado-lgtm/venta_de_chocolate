package com.Venta_de_chocolate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Venta_de_chocolate.model.Categoria;
import com.Venta_de_chocolate.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepo;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        categoriaRepo.save(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return categoriaRepo.findById(id).orElse(null);
    }

    @Override
    public void eliminarCategoria(Integer id) {
        categoriaRepo.deleteById(id);
    }
}