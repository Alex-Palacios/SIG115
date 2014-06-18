/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores.util;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Alex
 */

@ManagedBean(name = "funciones")
@SessionScoped
public class Funciones {

    public Funciones() {
    }
    
    public String formatFecha(Date fecha){
        return JsfUtil.setFechaFormateada(fecha, 1);
    }
    
    public List<Integer> anios(int rango){
        return JsfUtil.ultimosAnios(rango);
    }
    
    public String nombreMes(int mes){
        String nombre = "";
        switch(mes){
            case 1: nombre = "ENERO";break;
            case 2: nombre = "FEBRERO";break;
            case 3: nombre = "MARZO";break;
            case 4: nombre = "ABRIL";break;
            case 5: nombre = "MAYO";break;
            case 6: nombre = "JUNIO";break;
            case 7: nombre = "JULIO";break;
            case 8: nombre = "AGOSTO";break;
            case 9: nombre = "SEPTIEMBRE";break;
            case 10: nombre = "OCTUBRE";break;
            case 11: nombre = "NOVIEMBRE";break;
            case 12: nombre = "DICIEMBRE";break;
        }
        return nombre;
    }
    
    
    
}
