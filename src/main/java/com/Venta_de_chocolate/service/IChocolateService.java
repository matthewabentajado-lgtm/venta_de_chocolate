package com.Venta_de_chocolate.service;

import java.util.List;
import com.Venta_de_chocolate.model.chocolate;

public interface IChocolateService {
    List<chocolate> listarTodos();
    chocolate buscarPorId(Long id);
    chocolate guardar(chocolate chocolate);
    void eliminar(Long id);
}

