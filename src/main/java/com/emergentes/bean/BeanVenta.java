package com.emergentes.bean;

import com.emergentes.entidades.Ventas;
import com.emergentes.jpa.VentasJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanVenta {

    private EntityManagerFactory emf;
    private VentasJpaController jpaVentas;

    public BeanVenta() {
        emf = Persistence.createEntityManagerFactory("UPcomercio");
        jpaVentas = new VentasJpaController(emf);
    }

    public void insertar(Ventas ven) {
        jpaVentas.create(ven);
    }

    public void editar(Ventas ven) {
        try {
            jpaVentas.edit(ven);
        } catch (Exception ex) {
            Logger.getLogger(BeanVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) {
        try {
            jpaVentas.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Ventas buscar(Integer id) {
        Ventas ven = new Ventas();
        ven = jpaVentas.findVentas(id);
        return ven;
    }

    public List<Ventas> listartodos() {
        //listando la lista
        return jpaVentas.findVentasEntities();
    }
}
