/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package Ejer1.gui.tablemodels;

import Ejer1.modelo.Cliente;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Tablemodel para la tabla de empleados
 *
 * @author Jose Javier Bailon Ortiz
 */
public class ClientesTableModel extends AbstractTableModel {

    //ATRIBUTOS:
    private List<Cliente> listaClientes;//lista actual de empleados
    private String[] columnas={"ID","Nombre","Apellidos","FechaNac","Sexo","Casado"};// nombres de las columnas

    //METODOS:
    //Constructor
    public ClientesTableModel(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public int getRowCount() {
        return this.listaClientes.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Cliente c = listaClientes.get(rowIndex);
        switch (columnIndex) {
            case 0 -> value = c.getIDCliente();
            case 1 -> value = c.getNombre();
            case 2 -> value = c.getApellidos();
            case 3 -> value = new SimpleDateFormat("dd-MM-yyy").format(c.getFechaNac());
            case 4 -> value = c.getSexo();
            case 5 -> value = (c.isCasado())?"SI":"NO";

            default -> {
                value = null;
                throw new AssertionError();
            }
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}//end EncuestasTableModel
