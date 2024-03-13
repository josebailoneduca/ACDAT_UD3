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
public class TrabajoDAO {

    // ATRIBUTOS Manejo persistencia    
    private final EntityManagerFactory emf;
    private final String tableName;

    //CONSTRUCTOR
    public TrabajoDAO(String JPU_Name, String Table_name) {
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
    public Trabajo getById(int id) throws PersistenceException {
        Trabajo t = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            t = manager.find(Trabajo.class, id);
        } finally {
            manager.close();//No necesitamos un commit             
        }
        return t;
    }

    public Trabajo getByIdEager(int id) throws PersistenceException {
        Trabajo t = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            t = manager.find(Trabajo.class, id);
            //forzar carga
            if (t != null) {
                t.getCliente().getIDCliente();
                t.getEmpleadoss().size();
            }
        } finally {
            manager.close();//No necesitamos un commit             
        }
        return t;
    }

    //b) Por Objeto completo
    //Devolverá Listado objetos empleados o NULL
    public List<Trabajo> getAll() throws PersistenceException {
        List<Trabajo> listaTrabajos = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            //forzar carga de relaciones
            listaTrabajos = manager.createQuery("from " + this.tableName).getResultList();
        } finally {
            manager.close();//No necesitamos un commit            
        }
        return listaTrabajos;
    }

    public List<Trabajo> getAllEager() throws PersistenceException {
        List<Trabajo> listaTrabajo = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            listaTrabajo = manager.createQuery("from " + this.tableName).getResultList();
            if (listaTrabajo != null) //forzar carga de relaciones
            {
                listaTrabajo.stream().forEach((t) -> {
                    t.getCliente().getIDCliente();
                    t.getEmpleadoss().size();
                });
            }
        } finally {
            manager.close();//No necesitamos un commit            
        }
        return listaTrabajo;
    }

    //-INSERTAR/CREAR:
    public void create(Trabajo t) {
        EntityManager manager = beginOperation();
        manager.persist(t);
        closeOperation(manager);
    }

    //-GUARDAR/ACTUALIZAR:
    public void update(Trabajo t) {
        EntityManager manager = beginOperation();
        manager.merge(t);
        closeOperation(manager);
    }

    //-BORRAR:
    //a) Por ID
    public boolean deleteById(int id) {
        //-Primero obtengo el empleado:
        Trabajo t = this.getById(id);//Nota: cuidado de no anidar operaciones

        //-Después elimino:
        //Elimino si existe
        if (t != null) {
            EntityManager manager = beginOperation();
            //usar "merge" antes para que este administrado
            manager.remove(manager.contains(t) ? t : manager.merge(t));
            closeOperation(manager);
            return true;
        } else {
            return false;
        }

    }

    //b) Por Objeto completo
    public void delete(Trabajo t) {
        EntityManager manager = beginOperation();
        //Si no administrado, usar "merge" antes
        manager.remove(manager.contains(t) ? t : manager.merge(t));
        closeOperation(manager);
    }

}//end EmpleadoDAO
