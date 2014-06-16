/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "inventario_mov")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioMov.findAll", query = "SELECT i FROM InventarioMov i"),
    @NamedQuery(name = "InventarioMov.findByIdMov", query = "SELECT i FROM InventarioMov i WHERE i.idMov = :idMov"),
    @NamedQuery(name = "InventarioMov.findByEstilo", query = "SELECT i FROM InventarioMov i WHERE i.estilo = :estilo"),
    @NamedQuery(name = "InventarioMov.findByMarca", query = "SELECT i FROM InventarioMov i WHERE i.marca = :marca"),
    @NamedQuery(name = "InventarioMov.findByFecha", query = "SELECT i FROM InventarioMov i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "InventarioMov.findByIngresos", query = "SELECT i FROM InventarioMov i WHERE i.ingresos = :ingresos"),
    @NamedQuery(name = "InventarioMov.findByEgresos", query = "SELECT i FROM InventarioMov i WHERE i.egresos = :egresos")})
public class InventarioMov implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MOV")
    private Integer idMov;
    @Size(max = 50)
    @Column(name = "ESTILO")
    private String estilo;
    @Size(max = 50)
    @Column(name = "MARCA")
    private String marca;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "INGRESOS")
    private Integer ingresos;
    @Column(name = "EGRESOS")
    private Integer egresos;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;

    public InventarioMov() {
    }

    public InventarioMov(Integer idMov) {
        this.idMov = idMov;
    }

    public Integer getIdMov() {
        return idMov;
    }

    public void setIdMov(Integer idMov) {
        this.idMov = idMov;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIngresos() {
        return ingresos;
    }

    public void setIngresos(Integer ingresos) {
        this.ingresos = ingresos;
    }

    public Integer getEgresos() {
        return egresos;
    }

    public void setEgresos(Integer egresos) {
        this.egresos = egresos;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMov != null ? idMov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioMov)) {
            return false;
        }
        InventarioMov other = (InventarioMov) object;
        if ((this.idMov == null && other.idMov != null) || (this.idMov != null && !this.idMov.equals(other.idMov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.InventarioMov[ idMov=" + idMov + " ]";
    }
    
}
