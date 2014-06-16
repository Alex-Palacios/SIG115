/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.controladores.util.JsfUtil;
import com.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "KAROLSIGPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    // FUNCIONES Y CONSULTAS
    
    public boolean testConnection() {
        try{
            em.getTransaction().begin();
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public List<Usuario> login(String usr,String pass){
        List<Usuario> resultado =null;
        try{
            Query consulta = em.createNamedQuery("Usuario.login"); //Especificar nombre de la consulta que se va a ejecutar
            //Se le pasan los parametros a la consulta
            pass = JsfUtil.getMD5(pass);
            consulta.setParameter("username", usr);
            consulta.setParameter("password", pass);
            resultado = consulta.getResultList();
            return resultado;   
        }catch(Exception e){
            JsfUtil.addErrorMessage("ERROR AL LOGEARSE");
            return resultado;
        }
    }
    
    
    public boolean usernameDisponible(String username){
        try{
            Query consulta = em.createNamedQuery("Usuario.countByUsername"); //Especificar nombre de la consulta que se va a ejecutar
            //Se le pasan los parametros a la consulta
            consulta.setParameter("username", username);
            Integer resultado = Integer.parseInt(consulta.getSingleResult().toString());
            if(resultado == 0){
                return true;  
            }else{
                return false;
            } 
        }catch(Exception e){
            return false;
        }
    }
}
