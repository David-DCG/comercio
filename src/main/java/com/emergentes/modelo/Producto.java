package com.emergentes.modelo;

public class Producto {

    int id;
    String nombre;
    double precio;
    String ruta;
    String descripcion;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio, String ruta, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.ruta = ruta;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
