package com.Venta_de_chocolate.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    private String nombre;

    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Chocolate> chocolates;
  
    // Constructores
    public Categoria() {}

    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public List<Chocolate> getChocolates() { return chocolates; }
    public void setChocolates(List<Chocolate> chocolates) { this.chocolates = chocolates; }
}