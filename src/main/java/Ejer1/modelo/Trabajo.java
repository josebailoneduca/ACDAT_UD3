/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package Ejer1.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
@Entity(name = "trabajo")
@Table(name = "trabajo")
public class Trabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDTrabajo", columnDefinition = "int(4)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_trabajo")
    @SequenceGenerator(name = "id_trabajo", sequenceName = "id_trabajo")
    private int IDTrabajo;

    @Column(name = "Nombre", nullable = false, length = 30)
    private String Nombre;

    @Column(name = "Descripcion", length = 200)
    private String Descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    //Aquí sí necesitamos indicar LAZY porque por defecto es EAGER, y no 
    //interesa que cargue TODO el contenido que referencia el objeto autor:
    //- Es algo curioso porque podríamos pensar que no debería cargarlo pero
    //  lo hace y carga todos los campos del autor.
    //- Con LAZY sólo se cargará cuando se haga alguna operación como un "get"
    @JoinColumn(name = "IDCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "trabajo", cascade = CascadeType.ALL)
    //Un autor para múltiples libros
    //- "One" indica relación parte izquierda y "Many" parte derecha
    //- "mappedBy": debe ser el nombre del campo (no la columna) de la parte
    //             "Many" de la relación
    //- Nota: por defecto en OneToMany el "fetch" es LAZY, por eso NO lo especificamos
    //        Lo que hace es que sólo carga el ArrayList pero no cada una de sus
    //        referencias hasta que no se utilicen, por ejemplo con un "libro.getID(..)".
    private List<Empleado> empleados = new ArrayList<>();

    public Trabajo() {
    }

    public Trabajo(String Nombre, String Descripcion) {
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Empleado> getEmpleadoss() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
        //IMPORTANTE:
        //Indicamos para todos esos libros su autor es "this"
        for (Empleado e : empleados) {
            e.setTrabajo(this);
        }
        //Si no hacemos esto tendremos errores posteriores
    }

    public void addEmpleado(Empleado empleado) {
        //Si no lo contenía ya...
        if (!empleados.contains(empleado)) {
            empleado.setTrabajo(this);
            empleados.add(empleado);//añado a su lista
        }
    }

    @Override
    public String toString() {
        return "Trabajo{" + "IDTrabajo=" + IDTrabajo + ", Nombre=" + Nombre + ", Descripcion=" + Descripcion + ", cliente=" + cliente.getIDCliente() + '}';
    }

}//end Trabajos
