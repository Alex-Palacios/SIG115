/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIdCompra", query = "SELECT c FROM Compra c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Compra.findByDocCompra", query = "SELECT c FROM Compra c WHERE c.docCompra = :docCompra"),
    @NamedQuery(name = "Compra.findByTipoCompra", query = "SELECT c FROM Compra c WHERE c.tipoCompra = :tipoCompra"),
    @NamedQuery(name = "Compra.findByProcedencia", query = "SELECT c FROM Compra c WHERE c.procedencia = :procedencia"),
    @NamedQuery(name = "Compra.findByFechaCompra", query = "SELECT c FROM Compra c WHERE c.fechaCompra = :fechaCompra"),
    @NamedQuery(name = "Compra.findByMontoCompra", query = "SELECT c FROM Compra c WHERE c.montoCompra = :montoCompra")})
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COMPRA")
    private Integer idCompra;
    @Size(max = 25)
    @Column(name = "DOC_COMPRA")
    private String docCompra;
    @Column(name = "TIPO_COMPRA")
    private Integer tipoCompra;
    @Column(name = "PROCEDENCIA")
    private Integer procedencia;
    @Column(name = "FECHA_COMPRA")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_COMPRA")
    private BigDecimal montoCompra;
    @JoinColumns({
        @JoinColumn(name = "ANIO", referencedColumnName = "ANIO"),
        @JoinColumn(name = "MES", referencedColumnName = "MES")})
    @ManyToOne(optional = false)
    private Periodo periodo;
    @JoinColumn(name = "ID_PROVEEDOR", referencedColumnName = "ID_PROVEEDOR")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;

    public Compra() {
    }

    public Compra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getDocCompra() {
        return docCompra;
    }

    public void setDocCompra(String docCompra) {
        this.docCompra = docCompra;
    }

    public Integer getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(Integer tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public Integer getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(Integer procedencia) {
        this.procedencia = procedencia;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getMontoCompra() {
        return montoCompra;
    }

    public void setMontoCompra(BigDecimal montoCompra) {
        this.montoCompra = montoCompra;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
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
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Compra[ idCompra=" + idCompra + " ]";
    }
    
}
