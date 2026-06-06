package com.Venta_de_chocolate.service;

import java.util.List;
import com.Venta_de_chocolate.model.Categoria;

public interface ICategoriaService {
    List<Categoria> listarCategorias();
    void guardarCategoria(Categoria categoria);
    Categoria buscarPorId(Integer id);
    void eliminarCategoria(Integer id);
}