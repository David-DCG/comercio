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
import com.emergentes.entidades.Componente;
import com.emergentes.entidades.Periferico;
import com.emergentes.entidades.Usuario;
import com.emergentes.entidades.Ventas;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author David
 */
public class VentasJpaController implements Serializable {

    public VentasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ventas ventas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Componente idCom = ventas.getIdCom();
            if (idCom != null) {
                idCom = em.getReference(idCom.getClass(), idCom.getId());
                ventas.setIdCom(idCom);
            }
            Periferico idPer = ventas.getIdPer();
            if (idPer != null) {
                idPer = em.getReference(idPer.getClass(), idPer.getId());
                ventas.setIdPer(idPer);
            }
            Usuario idUsr = ventas.getIdUsr();
            if (idUsr != null) {
                idUsr = em.getReference(idUsr.getClass(), idUsr.getId());
                ventas.setIdUsr(idUsr);
            }
            em.persist(ventas);
            if (idCom != null) {
                idCom.getVentasList().add(ventas);
                idCom = em.merge(idCom);
            }
            if (idPer != null) {
                idPer.getVentasList().add(ventas);
                idPer = em.merge(idPer);
            }
            if (idUsr != null) {
                idUsr.getVentasList().add(ventas);
                idUsr = em.merge(idUsr);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ventas ventas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ventas persistentVentas = em.find(Ventas.class, ventas.getId());
            Componente idComOld = persistentVentas.getIdCom();
            Componente idComNew = ventas.getIdCom();
            Periferico idPerOld = persistentVentas.getIdPer();
            Periferico idPerNew = ventas.getIdPer();
            Usuario idUsrOld = persistentVentas.getIdUsr();
            Usuario idUsrNew = ventas.getIdUsr();
            if (idComNew != null) {
                idComNew = em.getReference(idComNew.getClass(), idComNew.getId());
                ventas.setIdCom(idComNew);
            }
            if (idPerNew != null) {
                idPerNew = em.getReference(idPerNew.getClass(), idPerNew.getId());
                ventas.setIdPer(idPerNew);
            }
            if (idUsrNew != null) {
                idUsrNew = em.getReference(idUsrNew.getClass(), idUsrNew.getId());
                ventas.setIdUsr(idUsrNew);
            }
            ventas = em.merge(ventas);
            if (idComOld != null && !idComOld.equals(idComNew)) {
                idComOld.getVentasList().remove(ventas);
                idComOld = em.merge(idComOld);
            }
            if (idComNew != null && !idComNew.equals(idComOld)) {
                idComNew.getVentasList().add(ventas);
                idComNew = em.merge(idComNew);
            }
            if (idPerOld != null && !idPerOld.equals(idPerNew)) {
                idPerOld.getVentasList().remove(ventas);
                idPerOld = em.merge(idPerOld);
            }
            if (idPerNew != null && !idPerNew.equals(idPerOld)) {
                idPerNew.getVentasList().add(ventas);
                idPerNew = em.merge(idPerNew);
            }
            if (idUsrOld != null && !idUsrOld.equals(idUsrNew)) {
                idUsrOld.getVentasList().remove(ventas);
                idUsrOld = em.merge(idUsrOld);
            }
            if (idUsrNew != null && !idUsrNew.equals(idUsrOld)) {
                idUsrNew.getVentasList().add(ventas);
                idUsrNew = em.merge(idUsrNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ventas.getId();
                if (findVentas(id) == null) {
                    throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.");
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
            Ventas ventas;
            try {
                ventas = em.getReference(Ventas.class, id);
                ventas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.", enfe);
            }
            Componente idCom = ventas.getIdCom();
            if (idCom != null) {
                idCom.getVentasList().remove(ventas);
                idCom = em.merge(idCom);
            }
            Periferico idPer = ventas.getIdPer();
            if (idPer != null) {
                idPer.getVentasList().remove(ventas);
                idPer = em.merge(idPer);
            }
            Usuario idUsr = ventas.getIdUsr();
            if (idUsr != null) {
                idUsr.getVentasList().remove(ventas);
                idUsr = em.merge(idUsr);
            }
            em.remove(ventas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ventas> findVentasEntities() {
        return findVentasEntities(true, -1, -1);
    }

    public List<Ventas> findVentasEntities(int maxResults, int firstResult) {
        return findVentasEntities(false, maxResults, firstResult);
    }

    private List<Ventas> findVentasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ventas.class));
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

    public Ventas findVentas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ventas.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ventas> rt = cq.from(Ventas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
