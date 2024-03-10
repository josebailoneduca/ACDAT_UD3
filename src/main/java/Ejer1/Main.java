/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package Ejer1;

import Ejer1.modelo.Cliente;
import Ejer1.modelo.Empleado;
import Ejer1.modelo.Trabajo;
import Ejer1.modelo.TrabajoEmpleado;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Jose Javier BO
 */
public class Main {
      private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String args []) throws ParseException{
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        //Gestor de persistencia
        emf = Persistence.createEntityManagerFactory("JPUSQLITE");
        manager = emf.createEntityManager();

        
        

        manager.getTransaction().begin();
        
        //insertar clientes
        Cliente c1 = new Cliente("María","Rosa Rosa",sdf.parse("1997-01-01"),"M",true);
        Cliente c2 = new Cliente("Miguel Ángel","Marrón Marrón",sdf.parse("1973-02-02"),"H",false);
        Cliente c3 = new Cliente("Sandra","Azul Azul",sdf.parse("1958-03-03"),"M",true);
        Cliente c4 = new Cliente("Pepe","Verde Verde",sdf.parse("1976-04-04"),"H",false);
        manager.persist(c1);
        manager.persist(c2);
        manager.persist(c3);
        manager.persist(c4);

        //insertar empleados
        Empleado e1 = new Empleado( "EmpA", "ApellidosA", "12345678A", 2000);
        Empleado e2 = new Empleado( "EmpB", "ApellidosB", "12345678B", 1850);
        Empleado e3 = new Empleado( "EmpC", "ApellidosC", "12345678C", 2300);
        Empleado e4 = new Empleado( "EmpD", "ApellidosD", "12345678D", 2500);
        manager.persist(e1);
        manager.persist(e2);
        manager.persist(e3);
        manager.persist(e4);

        //insertar trabajos
        Trabajo t1 = new Trabajo("T1", "Ayuntamiento", 1);
        Trabajo t2 = new Trabajo("T2", "Banco", 2);
        Trabajo t3 = new Trabajo("T3", "EmpresaPrivada", 3);
        Trabajo t4 = new Trabajo("T4", "Edificio", 4);
        manager.persist(t1);
        manager.persist(t2);
        manager.persist(t3);
        manager.persist(t4);


        //insertar trabajosEmpleados
        TrabajoEmpleado te1 = new TrabajoEmpleado(1, 4);
        TrabajoEmpleado te2 = new TrabajoEmpleado(2, 1);
        TrabajoEmpleado te3 = new TrabajoEmpleado(2, 2);
        TrabajoEmpleado te4 = new TrabajoEmpleado(3, 1);
        TrabajoEmpleado te5 = new TrabajoEmpleado(3, 2);
        TrabajoEmpleado te6 = new TrabajoEmpleado(3, 3);
        manager.persist(te1);
        manager.persist(te2);
        manager.persist(te3);
        manager.persist(te4);
        manager.persist(te5);
        manager.persist(te6);

        
        
        
        
        
        //3. Realización de las operaciones con "Commit"
        manager.getTransaction().commit();
        

        //Recoger de base de datos e imprimir 
        System.out.println("\nDATOS ACTUALMENTE EN LA BASE DE DATOS");
        System.out.println("-------------------------------------\n");
                
                
        recogerImprimir("Clientes");
        recogerImprimir("Empleados");
        recogerImprimir("Trabajos");
        recogerImprimir("TrabajosEmpleados");
        
    }
    
    public static void recogerImprimir(String entidad){
        List entidades= manager.createQuery("FROM "+entidad).getResultList();
        System.out.println("Nº "+entidad+": "+entidades.size());
        entidades.stream().forEach((t) -> {System.out.println(t);});
    }
}
