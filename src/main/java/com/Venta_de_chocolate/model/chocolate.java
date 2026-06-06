package com.Venta_de_chocolate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "chocolates")
public class Chocolate { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    
    private String nombre;
    
    private String marca; 
    
    private Double precio;
    
    @Column(length = 500) 
    private String imagen; 

    @ManyToOne
    @JoinColumn(name = "categoria_id") 
    private Categoria categoria;

    // Constructores
    public Chocolate() {}

    public Chocolate(Integer id, String nombre, String marca, Double precio, String imagen, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}