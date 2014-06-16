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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Proveedor.findByNombreProveedor", query = "SELECT p FROM Proveedor p WHERE p.nombreProveedor = :nombreProveedor"),
    @NamedQuery(name = "Proveedor.findByTipoProveedor", query = "SELECT p FROM Proveedor p WHERE p.tipoProveedor = :tipoProveedor"),
    @NamedQuery(name = "Proveedor.findByTcProveedor", query = "SELECT p FROM Proveedor p WHERE p.tcProveedor = :tcProveedor"),
    @NamedQuery(name = "Proveedor.findByMcProveedor", query = "SELECT p FROM Proveedor p WHERE p.mcProveedor = :mcProveedor")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROVEEDOR")
    private Integer idProveedor;
    @Size(max = 100)
    @Column(name = "NOMBRE_PROVEEDOR")
    private String nombreProveedor;
    @Column(name = "TIPO_PROVEEDOR")
    private Integer tipoProveedor;
    @Column(name = "TC_PROVEEDOR")
    private Integer tcProveedor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MC_PROVEEDOR")
    private BigDecimal mcProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private List<InventarioMov> inventarioMovList;

    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Integer getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(Integer tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public Integer getTcProveedor() {
        return tcProveedor;
    }

    public void setTcProveedor(Integer tcProveedor) {
        this.tcProveedor = tcProveedor;
    }

    public BigDecimal getMcProveedor() {
        return mcProveedor;
    }

    public void setMcProveedor(BigDecimal mcProveedor) {
        this.mcProveedor = mcProveedor;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @XmlTransient
    public List<InventarioMov> getInventarioMovList() {
        return inventarioMovList;
    }

    public void setInventarioMovList(List<InventarioMov> inventarioMovList) {
        this.inventarioMovList = inventarioMovList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Proveedor[ idProveedor=" + idProveedor + " ]";
    }
    
}
