package com.Venta_de_chocolate.service;

import java.util.List;
import com.Venta_de_chocolate.model.Categoria;

public interface ICategoriaService {
    List<Categoria> listarTodas();
    Categoria buscarPorId(Long id);
    Categoria guardar(Categoria categoria);
    void eliminar(Long id);
}
