/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package Ejer1.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;
/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Entity (name = "Clientes")
@Table(name = "Clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_cliente")
    @SequenceGenerator(name = "id_empleado", sequenceName = "id_empleado")
    private int IDCliente;
    
    @Column(name = "Nombre", nullable=false, length = 30)
    private String Nombre;
    
    @Column(name = "Apellidos", nullable=false, length = 70)
    private String Apellidos;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FechaNac", nullable=false)
    private Date FechaNac;
    
    @Column(name = "Sexo", nullable=false, length = 1)
    private String Sexo;
    
    @Column(name = "Casado", nullable=false, columnDefinition = "int(1)")
    @Type(type="org.hibernate.type.NumericBooleanType")
    private boolean Casado;
    
    public Cliente(){}
    
    public Cliente(String Nombre, String Apellidos, Date FechaNac, String Sexo, boolean Casado) {
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Sexo = Sexo;
        this.Casado = Casado;
    }

    
    
    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
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

    public Date getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(Date FechaNac) {
        this.FechaNac = FechaNac;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public boolean isCasado() {
        return Casado;
    }

    public void setCasado(boolean Casado) {
        this.Casado = Casado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.IDCliente;
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
        final Cliente other = (Cliente) obj;
        return this.IDCliente == other.IDCliente;
    }
   
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Cliente{" + "IDCliente=" + IDCliente + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", FechaNac=" + sdf.format(FechaNac) + ", Sexo=" + Sexo + ", Casado=" + Casado + '}';
    }

    
     
   
 

}//end Cliente
