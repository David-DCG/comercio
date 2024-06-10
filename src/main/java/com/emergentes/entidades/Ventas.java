/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author David
 */
@Entity
@Table(name = "ventas")
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v"),
    @NamedQuery(name = "Ventas.findById", query = "SELECT v FROM Ventas v WHERE v.id = :id"),
    @NamedQuery(name = "Ventas.findByFecha", query = "SELECT v FROM Ventas v WHERE v.fecha = :fecha")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fecha")
    private String fecha;
    @JoinColumn(name = "id_com", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Componente idCom;
    @JoinColumn(name = "id_per", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Periferico idPer;
    @JoinColumn(name = "id_usr", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsr;

    public Ventas() {
        this.id = 0;
        this.fecha = "";
        this.idCom = new Componente();
        this.idPer = new Periferico();
        this.idUsr = new Usuario();
    }

    public Ventas(Integer id) {
        this.id = id;
    }

    public Ventas(Integer id, String fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Componente getIdCom() {
        return idCom;
    }

    public void setIdCom(Componente idCom) {
        this.idCom = idCom;
    }

    public Periferico getIdPer() {
        return idPer;
    }

    public void setIdPer(Periferico idPer) {
        this.idPer = idPer;
    }

    public Usuario getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(Usuario idUsr) {
        this.idUsr = idUsr;
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
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.entidades.Ventas[ id=" + id + " ]";
    }

}
