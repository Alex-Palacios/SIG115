/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByTipoDoc", query = "SELECT v FROM Venta v WHERE v.tipoDoc = :tipoDoc"),
    @NamedQuery(name = "Venta.findByTipoVenta", query = "SELECT v FROM Venta v WHERE v.tipoVenta = :tipoVenta"),
    @NamedQuery(name = "Venta.findByFechaCancelacion", query = "SELECT v FROM Venta v WHERE v.fechaCancelacion = :fechaCancelacion"),
    @NamedQuery(name = "Venta.findByEstado", query = "SELECT v.codVendedor.nombreVendedor,v.fechaCancelacion FROM Venta v WHERE v.estado = :estado") })
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VENTA")
    private Integer idVenta;
    @Column(name = "TIPO_DOC")
    private Integer tipoDoc;
    @Size(max = 25)
    @Column(name = "DOC_VENTA")
    private String docVenta;
    @Column(name = "TIPO_VENTA")
    private Integer tipoVenta;
    @Column(name = "FECHA_VENTA")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_VENTA")
    private BigDecimal montoVenta;
    @Column(name = "SALDO_CUENTA")
    private BigDecimal saldoCuenta;
    @Column(name = "FECHA_CANCELACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCancelacion;
    @Column(name = "DIAS")
    private Integer dias;
    @Column(name = "ESTADO")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta")
    private List<Pago> pagoList;
    @JoinColumn(name = "COD_VENDEDOR", referencedColumnName = "COD_VENDEDOR")
    @ManyToOne(optional = false)
    private Vendedor codVendedor;
    @JoinColumns({
        @JoinColumn(name = "ANIO", referencedColumnName = "ANIO"),
        @JoinColumn(name = "MES", referencedColumnName = "MES")})
    @ManyToOne(optional = false)
    private Periodo periodo;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public Venta() {
    }

    public Venta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(Integer tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getDocVenta() {
        return docVenta;
    }

    public void setDocVenta(String docVenta) {
        this.docVenta = docVenta;
    }

    public Integer getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(Integer tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(BigDecimal montoVenta) {
        this.montoVenta = montoVenta;
    }

    public BigDecimal getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(BigDecimal saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    public Vendedor getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(Vendedor codVendedor) {
        this.codVendedor = codVendedor;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Venta[ idVenta=" + idVenta + " ]";
    }
    
}
