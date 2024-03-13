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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Entity (name = "empleado")
@Table(name = "empleado")
public class Empleado implements Serializable {

        private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDEmpleado", columnDefinition = "int(4)")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_empleado")
    @SequenceGenerator(name = "id_empleado", sequenceName = "id_empleado")
    private int IDEmpleado;
    
    @Column(name = "Nombre", nullable=false, length = 30)
    private String Nombre;
    
    @Column(name = "Apellidos", nullable=false, length = 70)
    private String Apellidos;
    
    @Column(name = "DNI", nullable=false, length = 9)
    private String DNI;
    
    @Column(name = "Sueldo", nullable=false)
    private double Sueldo;
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    //Aquí sí necesitamos indicar LAZY porque por defecto es EAGER, y no 
    //interesa que cargue TODO el contenido que referencia el objeto autor:
    //- Es algo curioso porque podríamos pensar que no debería cargarlo pero
    //  lo hace y carga todos los campos del autor.
    //- Con LAZY sólo se cargará cuando se haga alguna operación como un "get"
    @JoinColumn(name = "IDTrabajo")
    private Trabajo trabajo;
    
    
    
    
    public Empleado(){}
    public Empleado(String Nombre, String Apellidos, String DNI, double Sueldo) {
      
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.DNI = DNI;
        this.Sueldo = Sueldo;
    }

        
    public int getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(int IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public double getSueldo() {
        return Sueldo;
    }

    public void setSueldo(double Sueldo) {
        this.Sueldo = Sueldo;
    }

    
        public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.IDEmpleado;
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
        final Empleado other = (Empleado) obj;
        return this.IDEmpleado == other.IDEmpleado;
    }

    @Override
    public String toString() {
        return "Empleado{" + "IDEmpleado=" + IDEmpleado + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", DNI=" + DNI + ", Sueldo=" + Sueldo +", Trabajo="+trabajo.getIDTrabajo()+ '}';
    }
 
    


}//end Empleado
