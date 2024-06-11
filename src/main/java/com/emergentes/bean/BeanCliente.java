package com.emergentes.bean;

import com.emergentes.entidades.Cliente;
import com.emergentes.jpa.ClienteJpaController;
import com.emergentes.jpa.exceptions.IllegalOrphanException;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanCliente {

    private EntityManagerFactory emf;
    private ClienteJpaController jpaCliente;

    public BeanCliente() {
        emf = Persistence.createEntityManagerFactory("UPcomercio");
        jpaCliente = new ClienteJpaController(emf);
    }

    public void insertar(Cliente c) {
        jpaCliente.create(c);
    }

    public void editar(Cliente c) {
        try {
            jpaCliente.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) {
        try {
            jpaCliente.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(BeanCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Cliente buscar(Integer id) {
        Cliente c = new Cliente();
        c = jpaCliente.findCliente(id);
        return c;
    }

    public List<Cliente> listartodos() {
        //listando la lista
        return jpaCliente.findClienteEntities();
    }
}
