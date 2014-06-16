/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.entidades.InventarioMov;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alex
 */
@Stateless
public class InventarioMovFacade extends AbstractFacade<InventarioMov> {
    @PersistenceContext(unitName = "KAROLSIGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioMovFacade() {
        super(InventarioMov.class);
    }
    
}
