/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alex
 */
@Embeddable
public class PeriodoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO")
    private int anio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MES")
    private int mes;

    public PeriodoPK() {
    }

    public PeriodoPK(int anio, int mes) {
        this.anio = anio;
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) anio;
        hash += (int) mes;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoPK)) {
            return false;
        }
        PeriodoPK other = (PeriodoPK) object;
        if (this.anio != other.anio) {
            return false;
        }
        if (this.mes != other.mes) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.PeriodoPK[ anio=" + anio + ", mes=" + mes + " ]";
    }
    
}
