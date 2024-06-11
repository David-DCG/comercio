package com.emergentes.bean;

import com.emergentes.entidades.Componente;
import com.emergentes.jpa.ComponenteJpaController;
import com.emergentes.jpa.exceptions.IllegalOrphanException;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanComponente {

    private EntityManagerFactory emf;
    private ComponenteJpaController jpaComponente;

    public BeanComponente() {
        emf = Persistence.createEntityManagerFactory("UPcomercio");
        jpaComponente = new ComponenteJpaController(emf);
    }

    public void insertar(Componente com) {
        jpaComponente.create(com);
    }

    public void editar(Componente com) {
        try {
            jpaComponente.edit(com);
        } catch (Exception ex) {
            Logger.getLogger(BeanComponente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) {
        try {
            jpaComponente.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(BeanComponente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanComponente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Componente buscar(Integer id) {
        Componente com = new Componente();
        com = jpaComponente.findComponente(id);
        return com;
    }

    public List<Componente> listartodos() {
        //listando la lista
        return jpaComponente.findComponenteEntities();
    }
}
