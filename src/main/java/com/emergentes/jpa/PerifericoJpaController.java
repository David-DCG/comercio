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
import com.emergentes.entidades.Compra;
import com.emergentes.entidades.Periferico;
import java.util.ArrayList;
import java.util.List;
import com.emergentes.entidades.Ventas;
import com.emergentes.jpa.exceptions.IllegalOrphanException;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author David
 */
public class PerifericoJpaController implements Serializable {

    public PerifericoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Periferico periferico) {
        if (periferico.getCompraList() == null) {
            periferico.setCompraList(new ArrayList<Compra>());
        }
        if (periferico.getVentasList() == null) {
            periferico.setVentasList(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Compra> attachedCompraList = new ArrayList<Compra>();
            for (Compra compraListCompraToAttach : periferico.getCompraList()) {
                compraListCompraToAttach = em.getReference(compraListCompraToAttach.getClass(), compraListCompraToAttach.getId());
                attachedCompraList.add(compraListCompraToAttach);
            }
            periferico.setCompraList(attachedCompraList);
            List<Ventas> attachedVentasList = new ArrayList<Ventas>();
            for (Ventas ventasListVentasToAttach : periferico.getVentasList()) {
                ventasListVentasToAttach = em.getReference(ventasListVentasToAttach.getClass(), ventasListVentasToAttach.getId());
                attachedVentasList.add(ventasListVentasToAttach);
            }
            periferico.setVentasList(attachedVentasList);
            em.persist(periferico);
            for (Compra compraListCompra : periferico.getCompraList()) {
                Periferico oldIdPerOfCompraListCompra = compraListCompra.getIdPer();
                compraListCompra.setIdPer(periferico);
                compraListCompra = em.merge(compraListCompra);
                if (oldIdPerOfCompraListCompra != null) {
                    oldIdPerOfCompraListCompra.getCompraList().remove(compraListCompra);
                    oldIdPerOfCompraListCompra = em.merge(oldIdPerOfCompraListCompra);
                }
            }
            for (Ventas ventasListVentas : periferico.getVentasList()) {
                Periferico oldIdPerOfVentasListVentas = ventasListVentas.getIdPer();
                ventasListVentas.setIdPer(periferico);
                ventasListVentas = em.merge(ventasListVentas);
                if (oldIdPerOfVentasListVentas != null) {
                    oldIdPerOfVentasListVentas.getVentasList().remove(ventasListVentas);
                    oldIdPerOfVentasListVentas = em.merge(oldIdPerOfVentasListVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Periferico periferico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periferico persistentPeriferico = em.find(Periferico.class, periferico.getId());
            List<Compra> compraListOld = persistentPeriferico.getCompraList();
            List<Compra> compraListNew = periferico.getCompraList();
            List<Ventas> ventasListOld = persistentPeriferico.getVentasList();
            List<Ventas> ventasListNew = periferico.getVentasList();
            List<String> illegalOrphanMessages = null;
            for (Compra compraListOldCompra : compraListOld) {
                if (!compraListNew.contains(compraListOldCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compra " + compraListOldCompra + " since its idPer field is not nullable.");
                }
            }
            for (Ventas ventasListOldVentas : ventasListOld) {
                if (!ventasListNew.contains(ventasListOldVentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ventas " + ventasListOldVentas + " since its idPer field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Compra> attachedCompraListNew = new ArrayList<Compra>();
            for (Compra compraListNewCompraToAttach : compraListNew) {
                compraListNewCompraToAttach = em.getReference(compraListNewCompraToAttach.getClass(), compraListNewCompraToAttach.getId());
                attachedCompraListNew.add(compraListNewCompraToAttach);
            }
            compraListNew = attachedCompraListNew;
            periferico.setCompraList(compraListNew);
            List<Ventas> attachedVentasListNew = new ArrayList<Ventas>();
            for (Ventas ventasListNewVentasToAttach : ventasListNew) {
                ventasListNewVentasToAttach = em.getReference(ventasListNewVentasToAttach.getClass(), ventasListNewVentasToAttach.getId());
                attachedVentasListNew.add(ventasListNewVentasToAttach);
            }
            ventasListNew = attachedVentasListNew;
            periferico.setVentasList(ventasListNew);
            periferico = em.merge(periferico);
            for (Compra compraListNewCompra : compraListNew) {
                if (!compraListOld.contains(compraListNewCompra)) {
                    Periferico oldIdPerOfCompraListNewCompra = compraListNewCompra.getIdPer();
                    compraListNewCompra.setIdPer(periferico);
                    compraListNewCompra = em.merge(compraListNewCompra);
                    if (oldIdPerOfCompraListNewCompra != null && !oldIdPerOfCompraListNewCompra.equals(periferico)) {
                        oldIdPerOfCompraListNewCompra.getCompraList().remove(compraListNewCompra);
                        oldIdPerOfCompraListNewCompra = em.merge(oldIdPerOfCompraListNewCompra);
                    }
                }
            }
            for (Ventas ventasListNewVentas : ventasListNew) {
                if (!ventasListOld.contains(ventasListNewVentas)) {
                    Periferico oldIdPerOfVentasListNewVentas = ventasListNewVentas.getIdPer();
                    ventasListNewVentas.setIdPer(periferico);
                    ventasListNewVentas = em.merge(ventasListNewVentas);
                    if (oldIdPerOfVentasListNewVentas != null && !oldIdPerOfVentasListNewVentas.equals(periferico)) {
                        oldIdPerOfVentasListNewVentas.getVentasList().remove(ventasListNewVentas);
                        oldIdPerOfVentasListNewVentas = em.merge(oldIdPerOfVentasListNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = periferico.getId();
                if (findPeriferico(id) == null) {
                    throw new NonexistentEntityException("The periferico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periferico periferico;
            try {
                periferico = em.getReference(Periferico.class, id);
                periferico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periferico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Compra> compraListOrphanCheck = periferico.getCompraList();
            for (Compra compraListOrphanCheckCompra : compraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periferico (" + periferico + ") cannot be destroyed since the Compra " + compraListOrphanCheckCompra + " in its compraList field has a non-nullable idPer field.");
            }
            List<Ventas> ventasListOrphanCheck = periferico.getVentasList();
            for (Ventas ventasListOrphanCheckVentas : ventasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periferico (" + periferico + ") cannot be destroyed since the Ventas " + ventasListOrphanCheckVentas + " in its ventasList field has a non-nullable idPer field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(periferico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Periferico> findPerifericoEntities() {
        return findPerifericoEntities(true, -1, -1);
    }

    public List<Periferico> findPerifericoEntities(int maxResults, int firstResult) {
        return findPerifericoEntities(false, maxResults, firstResult);
    }

    private List<Periferico> findPerifericoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Periferico.class));
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

    public Periferico findPeriferico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periferico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerifericoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Periferico> rt = cq.from(Periferico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
