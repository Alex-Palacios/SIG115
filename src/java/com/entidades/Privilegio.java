/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "privilegio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilegio.findAll", query = "SELECT p FROM Privilegio p"),
    @NamedQuery(name = "Privilegio.findByCodPrivilegio", query = "SELECT p FROM Privilegio p WHERE p.codPrivilegio = :codPrivilegio"),
    @NamedQuery(name = "Privilegio.findByNombrePrivilegio", query = "SELECT p FROM Privilegio p WHERE p.nombrePrivilegio = :nombrePrivilegio"),
    @NamedQuery(name = "Privilegio.findByAutorizado", query = "SELECT p FROM Privilegio p WHERE p.autorizado = :autorizado")})
public class Privilegio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PRIVILEGIO")
    private Integer codPrivilegio;
    @Size(max = 100)
    @Column(name = "NOMBRE_PRIVILEGIO")
    private String nombrePrivilegio;
    @Column(name = "AUTORIZADO")
    private Boolean autorizado;
    @JoinColumn(name = "COD_ROL", referencedColumnName = "COD_ROL")
    @ManyToOne(optional = false)
    private Rol codRol;

    public Privilegio() {
    }

    public Privilegio(Integer codPrivilegio) {
        this.codPrivilegio = codPrivilegio;
    }

    public Integer getCodPrivilegio() {
        return codPrivilegio;
    }

    public void setCodPrivilegio(Integer codPrivilegio) {
        this.codPrivilegio = codPrivilegio;
    }

    public String getNombrePrivilegio() {
        return nombrePrivilegio;
    }

    public void setNombrePrivilegio(String nombrePrivilegio) {
        this.nombrePrivilegio = nombrePrivilegio;
    }

    public Boolean getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Boolean autorizado) {
        this.autorizado = autorizado;
    }

    public Rol getCodRol() {
        return codRol;
    }

    public void setCodRol(Rol codRol) {
        this.codRol = codRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPrivilegio != null ? codPrivilegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilegio)) {
            return false;
        }
        Privilegio other = (Privilegio) object;
        if ((this.codPrivilegio == null && other.codPrivilegio != null) || (this.codPrivilegio != null && !this.codPrivilegio.equals(other.codPrivilegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Privilegio[ codPrivilegio=" + codPrivilegio + " ]";
    }
    
}
