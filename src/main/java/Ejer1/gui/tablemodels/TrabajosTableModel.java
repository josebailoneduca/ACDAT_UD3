/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */
package Ejer1.gui.tablemodels;

import Ejer1.modelo.Cliente;
import Ejer1.modelo.Trabajo;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Tablemodel para la tabla de trabajos
 *
 * @author Jose Javier Bailon Ortiz
 */
public class TrabajosTableModel extends AbstractTableModel {

    //ATRIBUTOS:
    private List<Trabajo> listaTrabajos;//lista actual de trabajos
    private String[] columnas = {"ID", "Nombre", "Descripcion", "Cliente"};

    //METODOS:
    //Constructor
    public TrabajosTableModel(List<Trabajo> listaTrabajos) {
        this.listaTrabajos = listaTrabajos;
        this.columnas = columnas;
    }

    @Override
    public int getRowCount() {
        return this.listaTrabajos.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Trabajo t = listaTrabajos.get(rowIndex);
        switch (columnIndex) {
            case 0 -> value = t.getIDTrabajo();
            case 1 -> value = t.getNombre();
            case 2 -> value = t.getDescripcion();
            case 3 -> {
                Cliente c = t.getCliente();
                if (c != null) {
                    value = t.getCliente().getIDCliente();
                }
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
