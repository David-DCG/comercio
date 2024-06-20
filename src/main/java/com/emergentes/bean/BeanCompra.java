package com.emergentes.bean;

import com.emergentes.entidades.Compra;
import com.emergentes.jpa.CompraJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanCompra {

    private EntityManagerFactory emf;
    private CompraJpaController jpaCompra;

    public BeanCompra() {
        emf = Persistence.createEntityManagerFactory("UPcomercio");
        jpaCompra = new CompraJpaController(emf);
    }

    public void insertar(Compra com) {
        jpaCompra.create(com);
    }

    public void editar(Compra com) {
        try {
            jpaCompra.edit(com);
        } catch (Exception ex) {
            Logger.getLogger(BeanCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) {
        try {
            jpaCompra.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Compra buscar(Integer id) {
        Compra com = new Compra();
        com = jpaCompra.findCompra(id);
        return com;
    }

    public List<Compra> listartodos() {
        //listando la lista
        return jpaCompra.findCompraEntities();
    }
}
