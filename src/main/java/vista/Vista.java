/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.Controlador;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;

/**
 *
 * @author luciano
 */
public class Vista extends javax.swing.JFrame {
    
    protected Controlador controlador;
    protected ArrayList<String> plantasPerennes;
    protected ArrayList<String> plantasNoPerennes;

    /**
     * Creates new form Vista
     * @param controlador
     */
    public Vista(Controlador controlador) {
        initComponents();
        this.controlador = controlador;
        
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
        boxModel.addElement("Perennes");
        boxModel.addElement("NoPerennes");
        TipoPlantas.setModel(boxModel);
        
        plantasPerennes = controlador.getListaPlantasPerennes();
        plantasNoPerennes = controlador.getListaPlantasNoPerennes();
        
        TipoPlantasActionPerformed(null);
        
    }
    
    public void llenarLista(){
        
        String[] fila = new String[5];
        DefaultTableModel tableModel = (DefaultTableModel) TablaPlantas.getModel();
        ArrayList<JSONObject> lista = controlador.getEstadosPlantas();
        
        tableModel.setRowCount(0);
        
        for(JSONObject planta : lista){
            fila[0] = planta.getString("Nombre");
            fila[1] = "" + planta.getInt("Ubicacion");
            fila[2] = "" + planta.getDouble("hMax") + " / " + planta.getDouble("Humedad") + " / " + planta.getDouble("hMin");
            fila[3] = "" + planta.getDouble("tMax") + " / " + planta.getDouble("Temperatura") + " / " + planta.getDouble("tMin");
            fila[4] = "" + planta.getDouble("lMax") + " / " + planta.getDouble("Luminosidad") + " / " + planta.getDouble("lMin");
            tableModel.addRow(fila);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPlantas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PlantasDisponibles = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        TipoPlantas = new javax.swing.JComboBox<>();
        PlantaSeleccionada = new javax.swing.JTextField();
        btnCrear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UbicacionSeleccionada = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaPlantas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Ubicacion", "Humedad", "Temperatura", "Luminosidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaPlantas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Plantas", jPanel1);

        PlantasDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlantasDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(PlantasDisponibles);

        jLabel1.setText("Tipo de Planta:");

        TipoPlantas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoPlantasActionPerformed(evt);
            }
        });

        PlantaSeleccionada.setEditable(false);

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        jLabel2.setText("Planta:");

        jLabel3.setText("Ubicacion:");

        UbicacionSeleccionada.setToolTipText("");
        UbicacionSeleccionada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UbicacionSeleccionadaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TipoPlantas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrear)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PlantaSeleccionada, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(UbicacionSeleccionada))))
                .addContainerGap(343, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TipoPlantas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PlantaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(UbicacionSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(btnCrear)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agregar", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1062, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Quitar", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TipoPlantasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoPlantasActionPerformed
            
        DefaultListModel listModel = new DefaultListModel();

        if(TipoPlantas.getSelectedIndex() == 0){
            for(String planta : plantasPerennes){
                listModel.addElement(planta);
            }
        }else{
            for(String planta : plantasNoPerennes){
                listModel.addElement(planta);
            }
        }
        
        PlantasDisponibles.setModel(listModel);
        
    }//GEN-LAST:event_TipoPlantasActionPerformed

    private void PlantasDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlantasDisponiblesMouseClicked
        PlantaSeleccionada.setText(PlantasDisponibles.getSelectedValue().toString());
    }//GEN-LAST:event_PlantasDisponiblesMouseClicked

    private void UbicacionSeleccionadaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UbicacionSeleccionadaKeyTyped
       
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        
    }//GEN-LAST:event_UbicacionSeleccionadaKeyTyped

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        
        String nombre = PlantaSeleccionada.getText();
        String ubicacion = UbicacionSeleccionada.getText();
        
        if(nombre.length() <= 0 || ubicacion.length() <= 0){
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar una planta y una ubicacion");
            
        }else{
            
            if(controlador.getUbicacionesOcupadas().contains(Integer.valueOf(ubicacion))){
                
                JOptionPane.showMessageDialog(null, "La ubicacion seleccionada ya esta ocupada");
                
            }else{
                
                if(!controlador.crearPlanta(nombre, Integer.valueOf(ubicacion))){
                    JOptionPane.showMessageDialog(null, "Error inesperado al crear la nueva planta");
                }else{
                    llenarLista();
                }
                
            }
            
        }
        
    }//GEN-LAST:event_btnCrearActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PlantaSeleccionada;
    private javax.swing.JList<String> PlantasDisponibles;
    private javax.swing.JTable TablaPlantas;
    private javax.swing.JComboBox<String> TipoPlantas;
    private javax.swing.JTextField UbicacionSeleccionada;
    private javax.swing.JButton btnCrear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
