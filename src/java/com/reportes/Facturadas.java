/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reportes;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class Facturadas {
    private Date dia;
    private Integer numVentas;
    private BigDecimal montoVentas;
    private Integer facturadas;
    private BigDecimal montoFacturadas;
    private Integer noFacturadas;
    private BigDecimal montoNoFacturadas;

    public Facturadas() {
        
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Integer getNumVentas() {
        return numVentas;
    }

    public void setNumVentas(Integer numVentas) {
        this.numVentas = numVentas;
    }

    public BigDecimal getMontoVentas() {
        return montoVentas;
    }

    public void setMontoVentas(BigDecimal montoVentas) {
        this.montoVentas = montoVentas;
    }

    public Integer getFacturadas() {
        return facturadas;
    }

    public void setFacturadas(Integer facturadas) {
        this.facturadas = facturadas;
    }

    public BigDecimal getMontoFacturadas() {
        return montoFacturadas;
    }

    public void setMontoFacturadas(BigDecimal montoFacturadas) {
        this.montoFacturadas = montoFacturadas;
    }

    public Integer getNoFacturadas() {
        return noFacturadas;
    }

    public void setNoFacturadas(Integer noFacturadas) {
        this.noFacturadas = noFacturadas;
    }

    public BigDecimal getMontoNoFacturadas() {
        return montoNoFacturadas;
    }

    public void setMontoNoFacturadas(BigDecimal montoNoFacturadas) {
        this.montoNoFacturadas = montoNoFacturadas;
    }

    
    
}