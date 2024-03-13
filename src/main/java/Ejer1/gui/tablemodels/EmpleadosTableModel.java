/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package Ejer1.gui.tablemodels;

import Ejer1.modelo.Empleado;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Tablemodel para la tabla de empleados
 *
 * @author Jose Javier Bailon Ortiz
 */
public class EmpleadosTableModel extends AbstractTableModel {

    //ATRIBUTOS:
    private List<Empleado> listaEmpleados;//lista actual de empleados
    private String[] columnas={"ID","Nombre","Apellidos","DNI","Sueldo","Trabajo"};// nombres de las columnas

    //METODOS:
    //Constructor
    public EmpleadosTableModel(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    @Override
    public int getRowCount() {
        return this.listaEmpleados.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado e = listaEmpleados.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0 -> value = e.getIDEmpleado();
            case 1 -> value = e.getNombre();
            case 2 -> value = e.getApellidos();
            case 3 -> value = e.getDNI();
            case 4 -> value = e.getSueldo();
            case 5 -> {
                if (e.getTrabajo()!=null)
                    value = e.getTrabajo().getIDTrabajo();
            }

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
