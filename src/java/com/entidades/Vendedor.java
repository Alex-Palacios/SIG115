/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "vendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedor.findAll", query = "SELECT v FROM Vendedor v"),
    @NamedQuery(name = "Vendedor.findByCodVendedor", query = "SELECT v FROM Vendedor v WHERE v.codVendedor = :codVendedor"),
    @NamedQuery(name = "Vendedor.findByNombreVendedor", query = "SELECT v FROM Vendedor v WHERE v.nombreVendedor = :nombreVendedor"),
    @NamedQuery(name = "Vendedor.findByTvVendedor", query = "SELECT v FROM Vendedor v WHERE v.tvVendedor = :tvVendedor"),
    @NamedQuery(name = "Vendedor.findByMvVendedor", query = "SELECT v FROM Vendedor v WHERE v.mvVendedor = :mvVendedor")})
public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COD_VENDEDOR")
    private String codVendedor;
    @Size(max = 100)
    @Column(name = "NOMBRE_VENDEDOR")
    private String nombreVendedor;
    @Column(name = "TV_VENDEDOR")
    private Integer tvVendedor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MV_VENDEDOR")
    private BigDecimal mvVendedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codVendedor")
    private List<Pago> pagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codVendedor")
    private List<Venta> ventaList;

    public Vendedor() {
    }

    public Vendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }

    public String getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public Integer getTvVendedor() {
        return tvVendedor;
    }

    public void setTvVendedor(Integer tvVendedor) {
        this.tvVendedor = tvVendedor;
    }

    public BigDecimal getMvVendedor() {
        return mvVendedor;
    }

    public void setMvVendedor(BigDecimal mvVendedor) {
        this.mvVendedor = mvVendedor;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
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
        hash += (codVendedor != null ? codVendedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedor)) {
            return false;
        }
        Vendedor other = (Vendedor) object;
        if ((this.codVendedor == null && other.codVendedor != null) || (this.codVendedor != null && !this.codVendedor.equals(other.codVendedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Vendedor[ codVendedor=" + codVendedor + " ]";
    }
    
}
