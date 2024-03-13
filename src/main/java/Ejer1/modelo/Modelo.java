/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package Ejer1.modelo;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class Modelo {
    private static String tablaCliente="cliente";
    private static String tablaEmpleado="empleado";
    private static String tablaTrabajo="trabajo";
    private static String JPU_NAME="JPU";
    private final ClienteDAO clienteDAO;
    private final EmpleadoDAO empleadoDAO;
    private final TrabajoDAO trabajoDAO;

    public Modelo() {
        this.clienteDAO = new ClienteDAO(JPU_NAME, tablaCliente);
        this.empleadoDAO = new EmpleadoDAO(JPU_NAME, tablaEmpleado);
        this.trabajoDAO = new TrabajoDAO(JPU_NAME, tablaTrabajo);
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public EmpleadoDAO getEmpleadoDAO() {
        return empleadoDAO;
    }

    public TrabajoDAO getTrabajoDAO() {
        return trabajoDAO;
    }
    
    
    

}//end Modelo
