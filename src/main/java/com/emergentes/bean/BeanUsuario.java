package com.emergentes.bean;

import com.emergentes.entidades.Usuario;
import com.emergentes.jpa.UsuarioJpaController;
import com.emergentes.jpa.exceptions.IllegalOrphanException;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanUsuario {

    private EntityManagerFactory emf;
    private UsuarioJpaController jpaUsuario;

    public BeanUsuario() {
        emf = Persistence.createEntityManagerFactory("UPcomercio");
        jpaUsuario = new UsuarioJpaController(emf);
    }

    public void insertar(Usuario u) {
        jpaUsuario.create(u);
    }

    public void editar(Usuario u) {
        try {
            jpaUsuario.edit(u);
        } catch (Exception ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) {
        try {
            jpaUsuario.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario buscar(Integer id) {
        Usuario u = new Usuario();
        u = jpaUsuario.findUsuario(id);
        return u;
    }

    public List<Usuario> listartodos() {
        //listando la lista
        return jpaUsuario.findUsuarioEntities();
    }
}
