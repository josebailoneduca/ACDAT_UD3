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
public class ClienteDAO {

    // ATRIBUTOS Manejo persistencia    
    private final EntityManagerFactory emf;
    private final String tableName;

    //CONSTRUCTOR
    public ClienteDAO(String JPU_Name, String Table_name) {
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
    public Cliente getById(int id) throws PersistenceException {
        Cliente c = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            c = manager.find(Cliente.class, id);
        } finally {
            manager.close();//No necesitamos un commit             
        }
        return c;
    }

    
    public Cliente getByIdEager(int id) throws PersistenceException {
        Cliente c = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            c = manager.find(Cliente.class, id);
            //forzar carga
            if (c!=null)
                c.getTrabajos().size();
        } finally {
            manager.close();//No necesitamos un commit             
        }
        return c;
    }
    
    
    //b) Por Objeto completo
    //Devolverá Listado objetos empleados o NULL
    public List<Cliente> getAll() throws PersistenceException {
        List<Cliente> listaClientes = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            //forzar carga de relaciones
            listaClientes = manager.createQuery("from " + this.tableName).getResultList();
        } finally {
            manager.close();//No necesitamos un commit            
        }
        return listaClientes;
    }

    
     public List<Cliente> getAllEager() throws PersistenceException {
        List<Cliente> listaClientes = null;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();//No necesitamos transacción
            listaClientes = manager.createQuery("from " + this.tableName).getResultList();
            if (listaClientes!=null)
                //forzar carga de relaciones
                listaClientes.stream().forEach((c) -> c.getTrabajos().size());
        } finally {
            manager.close();//No necesitamos un commit            
        }
        return listaClientes;
    }
    
    
    
    //-INSERTAR/CREAR:
    public void create(Cliente c) {
        EntityManager manager = beginOperation();
        manager.persist(c);
        closeOperation(manager);
    }

    //-GUARDAR/ACTUALIZAR:
    public void update(Cliente c) {
        EntityManager manager = beginOperation();
        manager.merge(c);
        closeOperation(manager);
    }

    //-BORRAR:
    //a) Por ID
    public boolean deleteById(int id) {
        //-Primero obtengo el empleado:
        Cliente c = this.getById(id);//Nota: cuidado de no anidar operaciones

        //-Después elimino:
        //Elimino si existe
        if (c != null) {
            EntityManager manager = beginOperation();
            //usar "merge" antes para que este administrado
            manager.remove(manager.contains(c) ? c : manager.merge(c));
            closeOperation(manager);
            return true;
        } else {
            return false;
        }

    }

    //b) Por Objeto completo
    public void delete(Cliente c) {
        EntityManager manager = beginOperation();
        //Si no administrado, usar "merge" antes
        manager.remove(manager.contains(c) ? c : manager.merge(c));
        closeOperation(manager);
    }

}//end EmpleadoDAO
