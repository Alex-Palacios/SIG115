/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reportes;

import java.math.BigDecimal;

/**
 *
 * @author Alex
 */
public class VentasVendedor {
    private String vendedor;
    private Integer numContado;
    private Integer numCredito;
    private Integer totalVenta;
    private BigDecimal montoContado;
    private BigDecimal montoCredito;
    private BigDecimal montoTotal;
    private BigDecimal porcentaje;

    public VentasVendedor() {
        
    }

    public VentasVendedor(String vendedor, Integer numContado, Integer numCredito, Integer totalVenta, BigDecimal montoContado, BigDecimal montoCredito, BigDecimal montoTotal, BigDecimal porcentaje) {
        this.vendedor = vendedor;
        this.numContado = numContado;
        this.numCredito = numCredito;
        this.totalVenta = totalVenta;
        this.montoContado = montoContado;
        this.montoCredito = montoCredito;
        this.montoTotal = montoTotal;
        this.porcentaje = porcentaje;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Integer getNumContado() {
        return numContado;
    }

    public void setNumContado(Integer numContado) {
        this.numContado = numContado;
    }

    public Integer getNumCredito() {
        return numCredito;
    }

    public void setNumCredito(Integer numCredito) {
        this.numCredito = numCredito;
    }

    public Integer getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Integer totalVenta) {
        this.totalVenta = totalVenta;
    }

    public BigDecimal getMontoContado() {
        return montoContado;
    }

    public void setMontoContado(BigDecimal montoContado) {
        this.montoContado = montoContado;
    }

    public BigDecimal getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(BigDecimal montoCredito) {
        this.montoCredito = montoCredito;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    

    
    
}
