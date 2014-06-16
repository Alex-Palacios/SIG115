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
import javax.persistence.Lob;
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
@Table(name = "bitacora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findByIdBitacora", query = "SELECT b FROM Bitacora b WHERE b.idBitacora = :idBitacora"),
    @NamedQuery(name = "Bitacora.findByFechaTransac", query = "SELECT b FROM Bitacora b WHERE b.fechaTransac = :fechaTransac"),
    @NamedQuery(name = "Bitacora.findByHoraTransac", query = "SELECT b FROM Bitacora b WHERE b.horaTransac = :horaTransac"),
    @NamedQuery(name = "Bitacora.findByTipoTransaccion", query = "SELECT b FROM Bitacora b WHERE b.tipoTransaccion = :tipoTransaccion")})
public class Bitacora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BITACORA")
    private Integer idBitacora;
    @Column(name = "FECHA_TRANSAC")
    @Temporal(TemporalType.DATE)
    private Date fechaTransac;
    @Column(name = "HORA_TRANSAC")
    @Temporal(TemporalType.TIME)
    private Date horaTransac;
    @Column(name = "TIPO_TRANSACCION")
    private Integer tipoTransaccion;
    @Lob
    @Size(max = 65535)
    @Column(name = "TRANSACCION")
    private String transaccion;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Usuario idUser;

    public Bitacora() {
    }

    public Bitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Date getFechaTransac() {
        return fechaTransac;
    }

    public void setFechaTransac(Date fechaTransac) {
        this.fechaTransac = fechaTransac;
    }

    public Date getHoraTransac() {
        return horaTransac;
    }

    public void setHoraTransac(Date horaTransac) {
        this.horaTransac = horaTransac;
    }

    public Integer getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(Integer tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuario idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Bitacora[ idBitacora=" + idBitacora + " ]";
    }
    
}
