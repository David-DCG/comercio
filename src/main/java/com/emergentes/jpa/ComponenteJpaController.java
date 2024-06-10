/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.jpa;

import com.emergentes.entidades.Componente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.entidades.Compra;
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
public class ComponenteJpaController implements Serializable {

    public ComponenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Componente componente) {
        if (componente.getCompraList() == null) {
            componente.setCompraList(new ArrayList<Compra>());
        }
        if (componente.getVentasList() == null) {
            componente.setVentasList(new ArrayList<Ventas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Compra> attachedCompraList = new ArrayList<Compra>();
            for (Compra compraListCompraToAttach : componente.getCompraList()) {
                compraListCompraToAttach = em.getReference(compraListCompraToAttach.getClass(), compraListCompraToAttach.getId());
                attachedCompraList.add(compraListCompraToAttach);
            }
            componente.setCompraList(attachedCompraList);
            List<Ventas> attachedVentasList = new ArrayList<Ventas>();
            for (Ventas ventasListVentasToAttach : componente.getVentasList()) {
                ventasListVentasToAttach = em.getReference(ventasListVentasToAttach.getClass(), ventasListVentasToAttach.getId());
                attachedVentasList.add(ventasListVentasToAttach);
            }
            componente.setVentasList(attachedVentasList);
            em.persist(componente);
            for (Compra compraListCompra : componente.getCompraList()) {
                Componente oldIdComOfCompraListCompra = compraListCompra.getIdCom();
                compraListCompra.setIdCom(componente);
                compraListCompra = em.merge(compraListCompra);
                if (oldIdComOfCompraListCompra != null) {
                    oldIdComOfCompraListCompra.getCompraList().remove(compraListCompra);
                    oldIdComOfCompraListCompra = em.merge(oldIdComOfCompraListCompra);
                }
            }
            for (Ventas ventasListVentas : componente.getVentasList()) {
                Componente oldIdComOfVentasListVentas = ventasListVentas.getIdCom();
                ventasListVentas.setIdCom(componente);
                ventasListVentas = em.merge(ventasListVentas);
                if (oldIdComOfVentasListVentas != null) {
                    oldIdComOfVentasListVentas.getVentasList().remove(ventasListVentas);
                    oldIdComOfVentasListVentas = em.merge(oldIdComOfVentasListVentas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Componente componente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Componente persistentComponente = em.find(Componente.class, componente.getId());
            List<Compra> compraListOld = persistentComponente.getCompraList();
            List<Compra> compraListNew = componente.getCompraList();
            List<Ventas> ventasListOld = persistentComponente.getVentasList();
            List<Ventas> ventasListNew = componente.getVentasList();
            List<String> illegalOrphanMessages = null;
            for (Compra compraListOldCompra : compraListOld) {
                if (!compraListNew.contains(compraListOldCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Compra " + compraListOldCompra + " since its idCom field is not nullable.");
                }
            }
            for (Ventas ventasListOldVentas : ventasListOld) {
                if (!ventasListNew.contains(ventasListOldVentas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ventas " + ventasListOldVentas + " since its idCom field is not nullable.");
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
            componente.setCompraList(compraListNew);
            List<Ventas> attachedVentasListNew = new ArrayList<Ventas>();
            for (Ventas ventasListNewVentasToAttach : ventasListNew) {
                ventasListNewVentasToAttach = em.getReference(ventasListNewVentasToAttach.getClass(), ventasListNewVentasToAttach.getId());
                attachedVentasListNew.add(ventasListNewVentasToAttach);
            }
            ventasListNew = attachedVentasListNew;
            componente.setVentasList(ventasListNew);
            componente = em.merge(componente);
            for (Compra compraListNewCompra : compraListNew) {
                if (!compraListOld.contains(compraListNewCompra)) {
                    Componente oldIdComOfCompraListNewCompra = compraListNewCompra.getIdCom();
                    compraListNewCompra.setIdCom(componente);
                    compraListNewCompra = em.merge(compraListNewCompra);
                    if (oldIdComOfCompraListNewCompra != null && !oldIdComOfCompraListNewCompra.equals(componente)) {
                        oldIdComOfCompraListNewCompra.getCompraList().remove(compraListNewCompra);
                        oldIdComOfCompraListNewCompra = em.merge(oldIdComOfCompraListNewCompra);
                    }
                }
            }
            for (Ventas ventasListNewVentas : ventasListNew) {
                if (!ventasListOld.contains(ventasListNewVentas)) {
                    Componente oldIdComOfVentasListNewVentas = ventasListNewVentas.getIdCom();
                    ventasListNewVentas.setIdCom(componente);
                    ventasListNewVentas = em.merge(ventasListNewVentas);
                    if (oldIdComOfVentasListNewVentas != null && !oldIdComOfVentasListNewVentas.equals(componente)) {
                        oldIdComOfVentasListNewVentas.getVentasList().remove(ventasListNewVentas);
                        oldIdComOfVentasListNewVentas = em.merge(oldIdComOfVentasListNewVentas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = componente.getId();
                if (findComponente(id) == null) {
                    throw new NonexistentEntityException("The componente with id " + id + " no longer exists.");
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
            Componente componente;
            try {
                componente = em.getReference(Componente.class, id);
                componente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The componente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Compra> compraListOrphanCheck = componente.getCompraList();
            for (Compra compraListOrphanCheckCompra : compraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Componente (" + componente + ") cannot be destroyed since the Compra " + compraListOrphanCheckCompra + " in its compraList field has a non-nullable idCom field.");
            }
            List<Ventas> ventasListOrphanCheck = componente.getVentasList();
            for (Ventas ventasListOrphanCheckVentas : ventasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Componente (" + componente + ") cannot be destroyed since the Ventas " + ventasListOrphanCheckVentas + " in its ventasList field has a non-nullable idCom field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(componente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Componente> findComponenteEntities() {
        return findComponenteEntities(true, -1, -1);
    }

    public List<Componente> findComponenteEntities(int maxResults, int firstResult) {
        return findComponenteEntities(false, maxResults, firstResult);
    }

    private List<Componente> findComponenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Componente.class));
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

    public Componente findComponente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Componente.class, id);
        } finally {
            em.close();
        }
    }

    public int getComponenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Componente> rt = cq.from(Componente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
