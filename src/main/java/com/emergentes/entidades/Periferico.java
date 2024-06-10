/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author David
 */
@Entity
@Table(name = "perifericos")
@NamedQueries({
    @NamedQuery(name = "Periferico.findAll", query = "SELECT p FROM Periferico p"),
    @NamedQuery(name = "Periferico.findById", query = "SELECT p FROM Periferico p WHERE p.id = :id"),
    @NamedQuery(name = "Periferico.findByNombre", query = "SELECT p FROM Periferico p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Periferico.findByTipo", query = "SELECT p FROM Periferico p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Periferico.findByMarca", query = "SELECT p FROM Periferico p WHERE p.marca = :marca"),
    @NamedQuery(name = "Periferico.findByDescripcion", query = "SELECT p FROM Periferico p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Periferico.findByPrecio", query = "SELECT p FROM Periferico p WHERE p.precio = :precio"),
    @NamedQuery(name = "Periferico.findByRuta", query = "SELECT p FROM Periferico p WHERE p.ruta = :ruta")})
public class Periferico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private double precio;
    @Size(max = 50)
    @Column(name = "ruta")
    private String ruta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPer")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPer")
    private List<Ventas> ventasList;

    public Periferico() {
    }

    public Periferico(Integer id) {
        this.id = id;
    }

    public Periferico(Integer id, String nombre, String tipo, String marca, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periferico)) {
            return false;
        }
        Periferico other = (Periferico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.entidades.Periferico[ id=" + id + " ]";
    }
    
}
