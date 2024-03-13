/*
 *  LICENCIA ADRIÁN PR
 *  Aquí: nbfs://nbhost/SystmanagerFileSystmanager/Tmanagerplates/Licenses/license-default.txt para cambiar la licencia.
 *  Aquí: nbfs://nbhost/SystmanagerFileSystmanager/Tmanagerplates/Classes/Class.java para editar el tipo de plantilla.  
 *  Lista de paquetes:
 */
package Ejer1.modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 * EmpleadoDAO
 *
 * @author Adrián PR
 */
public class EmpleadoDAO {

    // ATRIBUTOS Manejo persistencia    
    private final EntityManagerFactory emf;
    private final String tableName;

    //CONSTRUCTOR
    public EmpleadoDAO(String JPU_Name, String Table_name) {
        this.emf = Persistence.createEntityManagerFactory(JPU_Name);
        this.tableName = Table_name;
        //Nota: aquí no es buena idea establecer el "manager" directamente,
        //      porque cada transacción debe tener el suyo propio, puede dar conflictos.
    }

    //MÉTODOS INICIO y CIERRE Operaciones
    public EntityManager beginOperation() {
        //Nuevo gestor persistencia
        EntityManager manager = emf.createEntityManager();
        //Inicio del control de la persistencia
        manager.getTransaction().begin();
        return manager;
    }

    public void closeOperation(EntityManager manager) {
        //Realización de transacción
        manager.getTransaction().commit();
        //Cierre del control de persistencia 
        manager.close();
    }

    //MÉTODOS_CRUD
    //-BUSCAR:
    //a) Por ID
    // Devolverá objeto Empleado o NULL
    public Empleado getById(int id) throws PersistenceException {
        Empleado e = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            e = manager.find(Empleado.class, id);
        } finally {
            manager.close();//No necesitamos un commit             
        }
        return e;
    }

    
    public Empleado getByIdEager(int id) throws PersistenceException {
        Empleado e = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            e = manager.find(Empleado.class, id);
            //forzar carga
            if (e!=null){
                e.getTrabajo().getIDTrabajo();
            }
        } finally {
            manager.close();//No necesitamos un commit             
        }
        return e;
    }
    
    
    //b) Por Objeto completo
    //Devolverá Listado objetos empleados o NULL
    public List<Empleado> getAll() throws PersistenceException {
        List<Empleado> listaEmpleados = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            //forzar carga de relaciones
            listaEmpleados = manager.createQuery("from " + this.tableName).getResultList();
        } finally {
            manager.close();//No necesitamos un commit            
        }
        return listaEmpleados;
    }

    
     public List<Empleado> getAllEager() throws PersistenceException {
        List<Empleado> listaEmpleados = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            listaEmpleados = manager.createQuery("from " + this.tableName).getResultList();
            if (listaEmpleados!=null)
                //forzar carga de relaciones
                listaEmpleados.stream().forEach((e) -> {
                    e.getTrabajo().getIDTrabajo();
                });
        } finally {
            manager.close();//No necesitamos un commit            
        }
        return listaEmpleados;
    }
    
    
    
    //-INSERTAR/CREAR:
    public void create(Empleado e) {
        EntityManager manager = beginOperation();
        manager.persist(e);
        closeOperation(manager);
    }

    //-GUARDAR/ACTUALIZAR:
    public void update(Empleado e) {
        EntityManager manager = beginOperation();
        manager.merge(e);
        closeOperation(manager);
    }

    //-BORRAR:
    //a) Por ID
    public boolean deleteById(int id) {
        //-Primero obtengo el empleado:
        Empleado e = this.getById(id);//Nota: cuidado de no anidar operaciones

        //-Después elimino:
        //Elimino si existe
        if (e != null) {
            EntityManager manager = beginOperation();
            //usar "merge" antes para que este administrado
            manager.remove(manager.contains(e) ? e : manager.merge(e));
            closeOperation(manager);
            return true;
        } else {
            return false;
        }

    }

    //b) Por Objeto completo
    public void delete(Empleado e) {
        EntityManager manager = beginOperation();
        //Si no administrado, usar "merge" antes
        manager.remove(manager.contains(e) ? e : manager.merge(e));
        closeOperation(manager);
    }

}//end EmpleadoDAO
