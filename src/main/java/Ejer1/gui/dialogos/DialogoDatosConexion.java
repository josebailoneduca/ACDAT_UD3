package prub_ud2.gui.dialogos;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
Lista de paquetes:
 */
/**
 * Diálogo para obtener los datos de conexion
 * 
 * @author Jose Javier Bailon Ortiz
 */
public class DialogoDatosConexion extends javax.swing.JDialog implements ActionListener {

    //constantes con los nombres de los drivers
    public static final String dirverMySQL = "com.mysql.cj.jdbc.Driver";
    public static final String dirverSQLite = "org.sqlite.JDBC";

    //hasmap con los valores de configuración almacenados
    private HashMap<String, String> resultado;

    /**
     * Constructor
     */
    public DialogoDatosConexion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initPropio();
    }

    /**
     * Inicializacion propia. Añade los listener y lanza el refresco inicial
     */
    private void initPropio() {
        inputDriver.addActionListener(this);
        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnBuscar.addActionListener(this);
        refrescarEstado();
    }

    /**
     * Pone el diálogo visible
     */
    public void mostrar() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();

        switch (ac) {
            case "driver" -> refrescarEstado();
            case "buscar" -> buscarArchivo();
            case "cancelar" -> {
                resultado = null;
                this.dispose();
            }
            case "aceptar" -> aceptar();
        }
    }

 
    /**
     * Muestra un panel u otro dependiendo del driver elegido
     */
    private void refrescarEstado() {
        //mostrar elementos de mysql
        if (inputDriver.getSelectedIndex() == 0) {
            ((CardLayout)panelCentral.getLayout()).show(panelCentral, "pmysql");
        //Mostrar elementos de sqlite
        } else {
            ((CardLayout)panelCentral.getLayout()).show(panelCentral, "psqlite");
        }
    }

    /**
     * Almacena los datos del formulario en un hashmap con los datos del formulario
     * en el atributo resultado y cierra el dialogo
     */
    private void guardarDatos() {
        resultado = new HashMap<String, String>();
        //mysql
        if (inputDriver.getSelectedIndex() == 0) {
            resultado.put("TIPO", "0");
            resultado.put("DRIVER", dirverMySQL);
            resultado.put("URL", "jdbc:mysql://" + inputServidor.getText() + ":" + inputPuerto.getText() + "/"+inputBaseDatos.getText());
            resultado.put("USER", inputUsuario.getText());
            resultado.put("PASSWORD", inputPassword.getText());
        //sqlite
        } else {
            resultado.put("TIPO", "1");
            resultado.put("DRIVER", dirverSQLite);
            resultado.put("URL", "jdbc:sqlite:" + inputRuta.getText());
            
        }
    }

    /**
     * Devuelve los datos almacenados
     * @return  Un hashmap con los datos
     */
    public HashMap<String, String> getDatos() {
        return this.resultado;
    }

    /**
     * Muestra un filechooser y pone la ruta del elemento devuelto como texto
     * del JTextField inputRuta
     */
    private void buscarArchivo() {
        
        JFileChooser fileChooser = new JFileChooser(inputRuta.getText());

        // Establecer filtro para mostrar solo archivos de bases de datos sqlite
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de BBDD SQLite(*.db *.sqlite *.sqlite3 *.db3)", "db","sqlite","sqlite3","db3");
        fileChooser.setFileFilter(filtro);
        
        //mostrar el jfilechooser
        int returnValue = fileChooser.showOpenDialog(this);
        
        //si ha dado ok construir dato a partir de seleccion
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            inputRuta.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    /**
     * Ejecutado cuando se pulsa el botón aceptar.
     * Lanza el guardado de datos.
     * Si se ha elegido el driver de sqlite comprueba si el archivo elegido existe
     */
    private void aceptar() {
        //comprobar si es SQLITE y en caso de serlo si existe el archivo elegido
        if (inputDriver.getSelectedIndex()==1){
            String ruta = inputRuta.getText();
            File f = new File(ruta);
            if (!f.exists() || ruta.length()<1){
                JOptionPane.showMessageDialog(this, "El archivo "+ruta+" no existe.","Archivo no encontrado",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
                guardarDatos();
                this.setVisible(false);
}
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pSuperior = new javax.swing.JPanel();
        lbDriver = new javax.swing.JLabel();
        inputDriver = new javax.swing.JComboBox<>();
        panelCentral = new javax.swing.JPanel();
        pMysql = new javax.swing.JPanel();
        lbServidor = new javax.swing.JLabel();
        lbPuerto = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        inputServidor = new javax.swing.JTextField();
        inputPuerto = new javax.swing.JTextField();
        inputUsuario = new javax.swing.JTextField();
        inputPassword = new javax.swing.JTextField();
        lbBaseDatos = new javax.swing.JLabel();
        inputBaseDatos = new javax.swing.JTextField();
        pSqlite = new javax.swing.JPanel();
        lbRuta = new javax.swing.JLabel();
        inputRuta = new javax.swing.JTextField();
        inputRuta.setText("src\\recursos\\trabajosempleadosclientes.db");
        btnBuscar = new javax.swing.JButton();
        panelInferior = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Parámetros de conexion");
        setResizable(false);

        pSuperior.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 1));
        pSuperior.setMaximumSize(new java.awt.Dimension(100, 2147483647));
        pSuperior.setMinimumSize(new java.awt.Dimension(12, 32));
        pSuperior.setOpaque(false);
        pSuperior.setPreferredSize(new java.awt.Dimension(10, 50));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 9, 0, 9};
        pSuperior.setLayout(jPanel2Layout);

        lbDriver.setText("Driver:");
        lbDriver.setMaximumSize(new java.awt.Dimension(134, 16));
        lbDriver.setMinimumSize(new java.awt.Dimension(134, 16));
        lbDriver.setPreferredSize(new java.awt.Dimension(80, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pSuperior.add(lbDriver, gridBagConstraints);

        inputDriver.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MySQL", "SQLite" }));
        inputDriver.setActionCommand("driver");
        inputDriver.setPreferredSize(new java.awt.Dimension(115, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pSuperior.add(inputDriver, gridBagConstraints);

        getContentPane().add(pSuperior, java.awt.BorderLayout.NORTH);

        panelCentral.setLayout(new java.awt.CardLayout());

        pMysql.setName("pmysql"); // NOI18N
        pMysql.setLayout(new java.awt.GridBagLayout());

        lbServidor.setText("Servidor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        pMysql.add(lbServidor, gridBagConstraints);

        lbPuerto.setText("Puerto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pMysql.add(lbPuerto, gridBagConstraints);

        lbUsuario.setText("Usuario:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pMysql.add(lbUsuario, gridBagConstraints);

        lbPassword.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pMysql.add(lbPassword, gridBagConstraints);

        inputServidor.setText("localhost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pMysql.add(inputServidor, gridBagConstraints);

        inputPuerto.setText("3306");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pMysql.add(inputPuerto, gridBagConstraints);

        inputUsuario.setText("root");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pMysql.add(inputUsuario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pMysql.add(inputPassword, gridBagConstraints);

        lbBaseDatos.setText("Base de Datos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        pMysql.add(lbBaseDatos, gridBagConstraints);

        inputBaseDatos.setText("trabajosempleados");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        pMysql.add(inputBaseDatos, gridBagConstraints);

        panelCentral.add(pMysql, "pmysql");

        pSqlite.setName("psqlite"); // NOI18N
        pSqlite.setRequestFocusEnabled(false);
        java.awt.GridBagLayout pSqliteLayout = new java.awt.GridBagLayout();
        pSqliteLayout.columnWidths = new int[] {200};
        pSqliteLayout.rowHeights = new int[] {0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0};
        pSqlite.setLayout(pSqliteLayout);

        lbRuta.setText("Ruta de archivo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pSqlite.add(lbRuta, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        pSqlite.add(inputRuta, gridBagConstraints);

        btnBuscar.setText("BUSCAR");
        btnBuscar.setActionCommand("buscar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        pSqlite.add(btnBuscar, gridBagConstraints);

        panelCentral.add(pSqlite, "psqlite");

        getContentPane().add(panelCentral, java.awt.BorderLayout.CENTER);

        panelInferior.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 50, 0};
        panelInferior.setLayout(jPanel3Layout);

        btnAceptar.setText("ACEPTAR");
        btnAceptar.setActionCommand("aceptar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panelInferior.add(btnAceptar, gridBagConstraints);

        btnCancelar.setText("CANCELAR");
        btnCancelar.setActionCommand("cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        panelInferior.add(btnCancelar, gridBagConstraints);

        getContentPane().add(panelInferior, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JTextField inputBaseDatos;
    private javax.swing.JComboBox<String> inputDriver;
    private javax.swing.JTextField inputPassword;
    private javax.swing.JTextField inputPuerto;
    private javax.swing.JTextField inputRuta;
    private javax.swing.JTextField inputServidor;
    private javax.swing.JTextField inputUsuario;
    private javax.swing.JLabel lbBaseDatos;
    private javax.swing.JLabel lbDriver;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbPuerto;
    private javax.swing.JLabel lbRuta;
    private javax.swing.JLabel lbServidor;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JPanel pMysql;
    private javax.swing.JPanel pSqlite;
    private javax.swing.JPanel pSuperior;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelInferior;
    // End of variables declaration//GEN-END:variables

}//fin DatosConexion
