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
@Entity (name = "TrabajosEmpleados")
@Table(name = "TrabajosEmpleados")
public class TrabajoEmpleado implements Serializable {
       private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", columnDefinition = "int(4)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_trabajoempleado")
    @SequenceGenerator(name = "id_trabajoempleado", sequenceName = "id_trabajoempleado")
    private int ID;
    
    @Column(name = "IDTrabajo", nullable=false, columnDefinition = "int(4)")
    private int IDTrabajo;

    @Column(name = "IDEmpleado", nullable=false, columnDefinition = "int(4)")
    private int IDEmpleado;
    
    
    public TrabajoEmpleado(){}
    public TrabajoEmpleado( int IDTrabajo, int IDEmpleado) {
        this.IDTrabajo = IDTrabajo;
        this.IDEmpleado = IDEmpleado;
    }

    
    
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDTrabajo() {
        return IDTrabajo;
    }

    public void setIDTrabajo(int IDTrabajo) {
        this.IDTrabajo = IDTrabajo;
    }

    public int getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(int IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.ID;
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
        final TrabajoEmpleado other = (TrabajoEmpleado) obj;
        return this.ID == other.ID;
    }

    @Override
    public String toString() {
        return "TrabajoEmpleado{" + "ID=" + ID + ", IDTrabajo=" + IDTrabajo + ", IDEmpleado=" + IDEmpleado + '}';
    }
 
    
}//end TrabajoEmpleado
