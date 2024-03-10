/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package Ejer1.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Entity (name = "Trabajos")
@Table(name = "Trabajos")
public class Trabajo implements Serializable {
       private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDTrabajo", columnDefinition = "int(4)")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_trabajo")
    @SequenceGenerator(name = "id_trabajo", sequenceName = "id_trabajo")
    private int IDTrabajo;
    
    @Column(name = "Nombre", nullable=false, length = 30)
    private String Nombre;
    
    @Column(name = "Descripcion", length = 200)
    private String Descripcion;
    
    @Column(name = "IDCliente", nullable=false, columnDefinition = "int(4)")
    private int IDCliente;

    public Trabajo(){}
    
    public Trabajo(String Nombre, String Descripcion, int IDCliente) {
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.IDCliente = IDCliente;
    }

    
    
    
    
    public int getIDTrabajo() {
        return IDTrabajo;
    }

    public void setIDTrabajo(int IDTrabajo) {
        this.IDTrabajo = IDTrabajo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.IDTrabajo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trabajo other = (Trabajo) obj;
        return this.IDTrabajo == other.IDTrabajo;
    }

    @Override
    public String toString() {
        return "Trabajo{" + "IDTrabajo=" + IDTrabajo + ", Nombre=" + Nombre + ", Descripcion=" + Descripcion + ", IDCliente=" + IDCliente + '}';
    }
 

}//end Trabajos
