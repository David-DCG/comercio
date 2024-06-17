package com.emergentes.bean;

import com.emergentes.entidades.Periferico;
import com.emergentes.jpa.PerifericoJpaController;
import com.emergentes.jpa.exceptions.IllegalOrphanException;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanPeriferico {

    private EntityManagerFactory emf;
    private PerifericoJpaController jpaPeriferico;

    public BeanPeriferico() {
        emf = Persistence.createEntityManagerFactory("UPcomercio");
        jpaPeriferico = new PerifericoJpaController(emf);
    }

    public void insertar(Periferico per) {
        jpaPeriferico.create(per);
    }

    public void editar(Periferico per) {
        try {
            jpaPeriferico.edit(per);
        } catch (Exception ex) {
            Logger.getLogger(BeanPeriferico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) {
        try {
            jpaPeriferico.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(BeanPeriferico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanPeriferico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Periferico buscar(Integer id) {
        Periferico per = new Periferico();
        per = jpaPeriferico.findPeriferico(id);
        return per;
    }

    public List<Periferico> listartodos() {
        //listando la lista
        return jpaPeriferico.findPerifericoEntities();
    }
}
