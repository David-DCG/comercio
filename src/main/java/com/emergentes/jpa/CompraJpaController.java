/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.entidades.Cliente;
import com.emergentes.entidades.Componente;
import com.emergentes.entidades.Compra;
import com.emergentes.entidades.Periferico;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author David
 */
public class CompraJpaController implements Serializable {

    public CompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCli = compra.getIdCli();
            if (idCli != null) {
                idCli = em.getReference(idCli.getClass(), idCli.getId());
                compra.setIdCli(idCli);
            }
            Componente idCom = compra.getIdCom();
            if (idCom != null) {
                idCom = em.getReference(idCom.getClass(), idCom.getId());
                compra.setIdCom(idCom);
            }
            Periferico idPer = compra.getIdPer();
            if (idPer != null) {
                idPer = em.getReference(idPer.getClass(), idPer.getId());
                compra.setIdPer(idPer);
            }
            em.persist(compra);
            if (idCli != null) {
                idCli.getCompraList().add(compra);
                idCli = em.merge(idCli);
            }
            if (idCom != null) {
                idCom.getCompraList().add(compra);
                idCom = em.merge(idCom);
            }
            if (idPer != null) {
                idPer.getCompraList().add(compra);
                idPer = em.merge(idPer);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getId());
            Cliente idCliOld = persistentCompra.getIdCli();
            Cliente idCliNew = compra.getIdCli();
            Componente idComOld = persistentCompra.getIdCom();
            Componente idComNew = compra.getIdCom();
            Periferico idPerOld = persistentCompra.getIdPer();
            Periferico idPerNew = compra.getIdPer();
            if (idCliNew != null) {
                idCliNew = em.getReference(idCliNew.getClass(), idCliNew.getId());
                compra.setIdCli(idCliNew);
            }
            if (idComNew != null) {
                idComNew = em.getReference(idComNew.getClass(), idComNew.getId());
                compra.setIdCom(idComNew);
            }
            if (idPerNew != null) {
                idPerNew = em.getReference(idPerNew.getClass(), idPerNew.getId());
                compra.setIdPer(idPerNew);
            }
            compra = em.merge(compra);
            if (idCliOld != null && !idCliOld.equals(idCliNew)) {
                idCliOld.getCompraList().remove(compra);
                idCliOld = em.merge(idCliOld);
            }
            if (idCliNew != null && !idCliNew.equals(idCliOld)) {
                idCliNew.getCompraList().add(compra);
                idCliNew = em.merge(idCliNew);
            }
            if (idComOld != null && !idComOld.equals(idComNew)) {
                idComOld.getCompraList().remove(compra);
                idComOld = em.merge(idComOld);
            }
            if (idComNew != null && !idComNew.equals(idComOld)) {
                idComNew.getCompraList().add(compra);
                idComNew = em.merge(idComNew);
            }
            if (idPerOld != null && !idPerOld.equals(idPerNew)) {
                idPerOld.getCompraList().remove(compra);
                idPerOld = em.merge(idPerOld);
            }
            if (idPerNew != null && !idPerNew.equals(idPerOld)) {
                idPerNew.getCompraList().add(compra);
                idPerNew = em.merge(idPerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compra.getId();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            Cliente idCli = compra.getIdCli();
            if (idCli != null) {
                idCli.getCompraList().remove(compra);
                idCli = em.merge(idCli);
            }
            Componente idCom = compra.getIdCom();
            if (idCom != null) {
                idCom.getCompraList().remove(compra);
                idCom = em.merge(idCom);
            }
            Periferico idPer = compra.getIdPer();
            if (idPer != null) {
                idPer.getCompraList().remove(compra);
                idPer = em.merge(idPer);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Compra findCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
