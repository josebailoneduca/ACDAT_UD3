/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package Ejer1;

import Ejer1.controlador.Controlador;
import Ejer1.gui.ventanas.Vista;
import Ejer1.modelo.Cliente;
import Ejer1.modelo.Empleado;
import Ejer1.modelo.Modelo;
import Ejer1.modelo.Trabajo;
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

    public static void main(String args[]) throws ParseException {
        
        creaBaseDatos();
        
        Modelo modelo=new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo,vista);
        controlador.iniciar();

    }

    public static void recogerImprimir(String entidad) {
        List entidades = manager.createQuery("FROM " + entidad).getResultList();
        System.out.println("Nº " + entidad + ": " + entidades.size());
        entidades.stream().forEach((t) -> {
            System.out.println(t);
        });
    }

    private static void creaBaseDatos() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //Gestor de persistencia
        emf = Persistence.createEntityManagerFactory("JPU");
        manager = emf.createEntityManager();

        manager.getTransaction().begin();
        //insertar clientes
        Cliente c1 = new Cliente("María", "Rosa Rosa", sdf.parse("1997-01-01"), "M", true);
        Cliente c2 = new Cliente("Miguel Ángel", "Marrón Marrón", sdf.parse("1973-02-02"), "H", false);
        Cliente c3 = new Cliente("Sandra", "Azul Azul", sdf.parse("1958-03-03"), "M", true);
        Cliente c4 = new Cliente("Pepe", "Verde Verde", sdf.parse("1976-04-04"), "H", false);
        manager.persist(c1);
        manager.persist(c2);
        manager.persist(c3);
        manager.persist(c4);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        //insertar trabajos
        Trabajo t1 = new Trabajo("T1", "Ayuntamiento Guadix: aceras");
        t1.setCliente(c1);
        Trabajo t2 = new Trabajo("T2", "Banco BB");
        t2.setCliente(c2);
        Trabajo t3 = new Trabajo("T3", "Empresa Privada");
        t3.setCliente(c3);
        Trabajo t4 = new Trabajo("T4", "Construcción edificio");
        t4.setCliente(c4);
        Trabajo t5 = new Trabajo("T5", "Ayuntamiento Guadix: parques");
        t5.setCliente(c1);
        manager.persist(t1);
        manager.persist(t2);
        manager.persist(t3);
        manager.persist(t4);
        manager.persist(t5);
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        //insertar empleados
        Empleado e1 = new Empleado("EmpA", "ApellidosA", "12345678A", 2000);
        e1.setTrabajo(t1);
        Empleado e2 = new Empleado("EmpB", "ApellidosB", "12345678B", 1850);
        e2.setTrabajo(t2);
        Empleado e3 = new Empleado("EmpC", "ApellidosC", "12345678C", 2300);
        e3.setTrabajo(t3);
        Empleado e4 = new Empleado("EmpD", "ApellidosD", "12345678D", 2500);
        e4.setTrabajo(t4);
        Empleado e5 = new Empleado("EmpE", "ApellidosE", "12345678E", 2400);
        e5.setTrabajo(t5);
        Empleado e6 = new Empleado("EmpF", "ApellidosF", "12345678F", 2200);
        e6.setTrabajo(t1);
        Empleado e7 = new Empleado("EmpG", "ApellidosG", "12345678G", 2100);
        e7.setTrabajo(t2);
        Empleado e8 = new Empleado("EmpH", "ApellidosH", "12345678H", 2600);
        e8.setTrabajo(t3);
        Empleado e9 = new Empleado("EmpI", "ApellidosI", "12345678I", 1900);
        e9.setTrabajo(t4);
        Empleado e10 = new Empleado("EmpJ", "ApellidosJ", "12345678J", 1800);
        e10.setTrabajo(t5);
        manager.persist(e1);
        manager.persist(e2);
        manager.persist(e3);
        manager.persist(e4);
        manager.getTransaction().commit();

        //Recoger de base de datos e imprimir 
        System.out.println("\nDATOS ACTUALMENTE EN LA BASE DE DATOS");
        System.out.println("-------------------------------------\n");

        recogerImprimir("cliente");
        recogerImprimir("empleado");
        recogerImprimir("trabajo");
    }
}
