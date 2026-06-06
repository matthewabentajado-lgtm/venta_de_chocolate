package com.Venta_de_chocolate.service;

import java.util.List;
import com.Venta_de_chocolate.model.Chocolate;

public interface IChocolateService {
    List<Chocolate> listarChocolates();
    void guardarChocolate(Chocolate chocolate);
    Chocolate buscarPorId(Integer id);
    void eliminarChocolate(Integer id);
}