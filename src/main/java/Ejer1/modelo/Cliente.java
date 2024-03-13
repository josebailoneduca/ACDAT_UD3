/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package Ejer1.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;
/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Entity (name = "cliente")
@Table(name = "cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDCliente", columnDefinition = "int(4)")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_cliente")
    @SequenceGenerator(name = "id_cliente", sequenceName = "id_cliente")
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
    
    @OneToMany(mappedBy = "cliente", cascade =CascadeType.ALL)
    //Un autor para múltiples libros
    //- "One" indica relación parte izquierda y "Many" parte derecha
    //- "mappedBy": debe ser el nombre del campo (no la columna) de la parte
    //             "Many" de la relación
    //- Nota: por defecto en OneToMany el "fetch" es LAZY, por eso NO lo especificamos
    //        Lo que hace es que sólo carga el ArrayList pero no cada una de sus
    //        referencias hasta que no se utilicen, por ejemplo con un "libro.getID(..)".
    private List<Trabajo> trabajos = new ArrayList<>();
    
    
    
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

    
        public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
        //IMPORTANTE:
        //Indicamos para todos esos libros su autor es "this"
        for(Trabajo t: trabajos) t.setCliente(this);
        //Si no hacemos esto tendremos errores posteriores
    }
        
    public void addTrabajo(Trabajo trabajo){
        //Si no lo contenía ya...
        if(!trabajos.contains(trabajo)){
            trabajo.setCliente(this);//primero establezco este cliente
            trabajos.add(trabajo);//añado a su lista
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.IDCliente;
        hash = 19 * hash + Objects.hashCode(this.Nombre);
        hash = 19 * hash + Objects.hashCode(this.Apellidos);
        hash = 19 * hash + Objects.hashCode(this.FechaNac);
        hash = 19 * hash + Objects.hashCode(this.Sexo);
        hash = 19 * hash + (this.Casado ? 1 : 0);
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
        if (this.IDCliente != other.IDCliente) {
            return false;
        }
        if (this.Casado != other.Casado) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.Apellidos, other.Apellidos)) {
            return false;
        }
        if (!Objects.equals(this.Sexo, other.Sexo)) {
            return false;
        }
        return Objects.equals(this.FechaNac, other.FechaNac);
    }
    
    
     
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Cliente{" + "IDCliente=" + IDCliente + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", FechaNac=" + sdf.format(FechaNac) + ", Sexo=" + Sexo + ", Casado=" + Casado + '}';
    }

    
     
   
 

}//end Cliente
