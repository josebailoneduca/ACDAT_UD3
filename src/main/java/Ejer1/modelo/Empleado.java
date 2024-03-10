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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Entity (name = "Empleados")
@Table(name = "Empleados")
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
        return "Empleado{" + "IDEmpleado=" + IDEmpleado + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", DNI=" + DNI + ", Sueldo=" + Sueldo + '}';
    }
 
    


}//end Empleado
