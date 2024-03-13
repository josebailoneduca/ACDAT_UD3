/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package Ejer1.controlador;

import Ejer1.gui.ventanas.Vista;
import Ejer1.modelo.Modelo;
import Ejer1.modelo.Trabajo;
import java.util.List;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
public class Controlador {
    Modelo modelo;
    Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar(){
       vista.setControlador(this);
       vista.setVisible(true);
    }

    public List<Trabajo> getTrabajos() {
        return modelo.getTrabajoDAO().getAllEager();
    }
    
}//end Controlador
