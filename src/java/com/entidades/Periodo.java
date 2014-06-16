/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "periodo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p"),
    @NamedQuery(name = "Periodo.findByAnio", query = "SELECT p FROM Periodo p WHERE p.periodoPK.anio = :anio"),
    @NamedQuery(name = "Periodo.findByMes", query = "SELECT p FROM Periodo p WHERE p.periodoPK.mes = :mes"),
    @NamedQuery(name = "Periodo.findByTvPeriodo", query = "SELECT p FROM Periodo p WHERE p.tvPeriodo = :tvPeriodo"),
    @NamedQuery(name = "Periodo.findByMvPeriodo", query = "SELECT p FROM Periodo p WHERE p.mvPeriodo = :mvPeriodo"),
    @NamedQuery(name = "Periodo.findByTcPeriodo", query = "SELECT p FROM Periodo p WHERE p.tcPeriodo = :tcPeriodo"),
    @NamedQuery(name = "Periodo.findByMcPeriodo", query = "SELECT p FROM Periodo p WHERE p.mcPeriodo = :mcPeriodo")})
public class Periodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeriodoPK periodoPK;
    @Column(name = "TV_PERIODO")
    private Integer tvPeriodo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MV_PERIODO")
    private BigDecimal mvPeriodo;
    @Column(name = "TC_PERIODO")
    private Integer tcPeriodo;
    @Column(name = "MC_PERIODO")
    private BigDecimal mcPeriodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodo")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodo")
    private List<Venta> ventaList;

    public Periodo() {
    }

    public Periodo(PeriodoPK periodoPK) {
        this.periodoPK = periodoPK;
    }

    public Periodo(int anio, int mes) {
        this.periodoPK = new PeriodoPK(anio, mes);
    }

    public PeriodoPK getPeriodoPK() {
        return periodoPK;
    }

    public void setPeriodoPK(PeriodoPK periodoPK) {
        this.periodoPK = periodoPK;
    }

    public Integer getTvPeriodo() {
        return tvPeriodo;
    }

    public void setTvPeriodo(Integer tvPeriodo) {
        this.tvPeriodo = tvPeriodo;
    }

    public BigDecimal getMvPeriodo() {
        return mvPeriodo;
    }

    public void setMvPeriodo(BigDecimal mvPeriodo) {
        this.mvPeriodo = mvPeriodo;
    }

    public Integer getTcPeriodo() {
        return tcPeriodo;
    }

    public void setTcPeriodo(Integer tcPeriodo) {
        this.tcPeriodo = tcPeriodo;
    }

    public BigDecimal getMcPeriodo() {
        return mcPeriodo;
    }

    public void setMcPeriodo(BigDecimal mcPeriodo) {
        this.mcPeriodo = mcPeriodo;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodoPK != null ? periodoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.periodoPK == null && other.periodoPK != null) || (this.periodoPK != null && !this.periodoPK.equals(other.periodoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Periodo[ periodoPK=" + periodoPK + " ]";
    }
    
}
