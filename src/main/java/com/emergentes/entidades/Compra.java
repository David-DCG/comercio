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
@Table(name = "compras")
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findById", query = "SELECT c FROM Compra c WHERE c.id = :id"),
    @NamedQuery(name = "Compra.findByFecha", query = "SELECT c FROM Compra c WHERE c.fecha = :fecha")})
public class Compra implements Serializable {

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
    @JoinColumn(name = "id_cli", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente idCli;
    @JoinColumn(name = "id_com", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Componente idCom;
    @JoinColumn(name = "id_per", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Periferico idPer;

    public Compra() {
        this.id = 0;
        this.fecha = "";
        this.idCli = new Cliente();
        this.idCom = new Componente();
        this.idPer = new Periferico();
    }

    public Compra(Integer id) {
        this.id = id;
    }

    public Compra(Integer id, String fecha) {
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

    public Cliente getIdCli() {
        return idCli;
    }

    public void setIdCli(Cliente idCli) {
        this.idCli = idCli;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.entidades.Compra[ id=" + id + " ]";
    }

}
