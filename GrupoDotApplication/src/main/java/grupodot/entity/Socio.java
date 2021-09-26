/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupodot.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "socio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Socio.findAll", query = "SELECT s FROM Socio s"),
    @NamedQuery(name = "Socio.findById", query = "SELECT s FROM Socio s WHERE s.id = :id"),
    @NamedQuery(name = "Socio.findByNombre", query = "SELECT s FROM Socio s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Socio.findByTasa", query = "SELECT s FROM Socio s WHERE s.tasa = :tasa"),
    @NamedQuery(name = "Socio.findByMontoMaximo", query = "SELECT s FROM Socio s WHERE s.montoMaximo = :montoMaximo")})
public class Socio implements Serializable, Comparable<Socio> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "tasa")
    private float tasa;
    @Basic(optional = false)
    @Column(name = "monto_maximo")
    private long montoMaximo;

    public Socio() {
    }

    public Socio(Integer id) {
        this.id = id;
    }

    public Socio(Integer id, String nombre, float tasa, long montoMaximo) {
        this.id = id;
        this.nombre = nombre;
        this.tasa = tasa;
        this.montoMaximo = montoMaximo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getTasa() {
        return tasa;
    }

    public void setTasa(float tasa) {
        this.tasa = tasa;
    }

    public long getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(long montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socio)) {
            return false;
        }
        Socio other = (Socio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grupodot.entity.Socio[ id=" + id + " ]";
    }

    /**
     *
     * Este es el método que usa Java para ordenar un iterable de Socios.
     *
     * Compara entre dos socios cual tiene menor tasa de ínteres, retorna un
     * número mayor a cero si el primer socio tiene mayor tasa de ínteres,
     * retorna un número menor a cero en caso de que el segundo socio tenga
     * mayor tasa de ínteres. Si ambos socios tienen la misma tasa de ínteres
     * los compara por su monto máximo, retorna un número mayor a cero si el
     * primer socio tiene un mayor monto máximo y retorna un número menor a cero
     * si el segundo socio tiene el mayor monto máximo.
     *
     *
     * De la forma en la que se implementó, Java ordenaría un Iterable<T> de
     * socios de menor a mayor haciendo uso del retorno de este método, en caso
     * de que retorne cero hace uso del orden referido a la clase "natural
     * ordering"
     *
     * @param o (socio)
     * @return int
     */
    @Override
    public int compareTo(Socio o) {
        int tasa = (int) (this.tasa - o.tasa);

        if (tasa == 0) {
            if (this.montoMaximo > o.montoMaximo) {
                return 1;
            } else if (this.montoMaximo == o.montoMaximo) {
                return 0;
            }
            return -1;
        }
        return tasa;
    }

}
