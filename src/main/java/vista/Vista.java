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
    protected boolean simulando;

    /**
     * Creates new form Vista
     * @param controlador
     */
    public Vista(Controlador controlador) {
        initComponents();
        this.controlador = controlador;
        simulando = false;
        
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
        for(String i : controlador.getListaTiposPlantas()){
            boxModel.addElement(i);
        }

        TipoPlantas.setModel(boxModel);
        
        TipoPlantasActionPerformed(null);
        
    }
    
    public void actualizarLista(){
        
        String[] fila = new String[7];
        DefaultTableModel tableModel = (DefaultTableModel) TablaPlantas.getModel();
        ArrayList<JSONObject> lista = controlador.getEstadosPlantas();
        
        tableModel.setRowCount(0);
        
        for(JSONObject planta : lista){
            
            fila[0] = planta.getString("Nombre");
            fila[1] = "" + planta.getInt("Ubicacion");
            
            if(!planta.getBoolean("Muerta")){
                fila[2] = "" + planta.getString("Etapa");
                fila[3] = "" + planta.getDouble("hMax") + " / " + String.format("%.2f", planta.getDouble("Humedad")) + " / " + planta.getDouble("hMin");
                fila[4] = "" + planta.getDouble("tMax") + " / " + String.format("%.1f",planta.getDouble("Temperatura")) + " / " + planta.getDouble("tMin");
                fila[5] = "" + planta.getDouble("lMax") + " / " + String.format("%.2f",planta.getDouble("Luminosidad")) + " / " + planta.getDouble("lMin");
                fila[6] = "" + planta.getInt("DuracionActual") + " / " + planta.getInt("DuracionLimite");
            }else{
                fila[2] = "Muerta";
                fila[3] = "" + String.format("%.2f",planta.getDouble("Humedad"));
                fila[4] = "" + String.format("%.1f",planta.getDouble("Temperatura"));
                fila[5] = "" + String.format("%.2f",planta.getDouble("Luminosidad"));
                fila[6] = "";
            }
            
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
        btnSimularPaso = new javax.swing.JButton();
        btnSimularValores = new javax.swing.JButton();
        btnSimulacionContinua = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PlantasDisponibles = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        TipoPlantas = new javax.swing.JComboBox<>();
        PlantaSeleccionadaAgregar = new javax.swing.JTextField();
        btnCrear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UbicacionAgregar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnBorrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        UbicacionBorrar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaPlantas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Ubicacion", "Etapa", "Humedad", "Temperatura", "Luminosidad", "Duracion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaPlantas);

        btnSimularPaso.setText("Simular Paso");
        btnSimularPaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularPasoActionPerformed(evt);
            }
        });

        btnSimularValores.setText("Simular Valores");
        btnSimularValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimularValoresActionPerformed(evt);
            }
        });

        btnSimulacionContinua.setText("Simulacion Continua");
        btnSimulacionContinua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimulacionContinuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSimularPaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimularValores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimulacionContinua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnSimularPaso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimularValores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSimulacionContinua)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        PlantaSeleccionadaAgregar.setEditable(false);

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        jLabel2.setText("Planta:");

        jLabel3.setText("Ubicacion:");

        UbicacionAgregar.setToolTipText("");
        UbicacionAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UbicacionAgregarKeyTyped(evt);
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
                            .addComponent(PlantaSeleccionadaAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(UbicacionAgregar))))
                .addContainerGap(397, Short.MAX_VALUE))
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
                            .addComponent(PlantaSeleccionadaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(UbicacionAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(btnCrear)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agregar", jPanel2);

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        jLabel4.setText("Ubicacion:");

        UbicacionBorrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UbicacionBorrarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel4)
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBorrar)
                    .addComponent(UbicacionBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(852, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(UbicacionBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(btnBorrar)
                .addContainerGap(338, Short.MAX_VALUE))
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
            for(String planta : controlador.getListaPlantasPerennes()){
                listModel.addElement(planta);
            }
        }else{
            for(String planta : controlador.getListaPlantasNoPerennes()){
                listModel.addElement(planta);
            }
        }
        
        PlantasDisponibles.setModel(listModel);
        
    }//GEN-LAST:event_TipoPlantasActionPerformed

    private void PlantasDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlantasDisponiblesMouseClicked
        PlantaSeleccionadaAgregar.setText(PlantasDisponibles.getSelectedValue().toString());
    }//GEN-LAST:event_PlantasDisponiblesMouseClicked

    private void UbicacionAgregarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UbicacionAgregarKeyTyped
       
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
        
    }//GEN-LAST:event_UbicacionAgregarKeyTyped

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        
        String nombre = PlantaSeleccionadaAgregar.getText();
        String ubicacion = UbicacionAgregar.getText();
        
        if(nombre.length() <= 0 || ubicacion.length() <= 0){
            
            JOptionPane.showMessageDialog(null, "Debe seleccionar una planta y una ubicacion");
            
        }else{
            
            if(controlador.ubicacionEstaOcupada(Integer.valueOf(ubicacion))){
                
                JOptionPane.showMessageDialog(null, "La ubicacion seleccionada ya esta ocupada");
                
            }else{
                
                if(!controlador.crearPlanta(nombre, Integer.valueOf(ubicacion))){
                    JOptionPane.showMessageDialog(null, "Error inesperado al crear la nueva planta");
                }else{
                    UbicacionAgregar.setText("");
                } 
            }
            
        }                     

    }//GEN-LAST:event_btnCrearActionPerformed

    private void UbicacionBorrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UbicacionBorrarKeyTyped
        
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_UbicacionBorrarKeyTyped

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        String ubicacion = UbicacionBorrar.getText();
        
        if(ubicacion.length() <= 0){
            
            JOptionPane.showMessageDialog(null, "Debe ingresar una ubicacion");
            
        }else{
            
            if(!controlador.ubicacionEstaOcupada(Integer.valueOf(ubicacion))){
                
                JOptionPane.showMessageDialog(null, "Esta ubicacion no contiene nunguna planta");
                
            }else{
                
                if(JOptionPane.showConfirmDialog(null, "Desea remover la planta ingresada?", "Confirmacion", JOptionPane.YES_NO_OPTION) == 0){
                    
                    if(!controlador.eliminarPlanta(Integer.valueOf(ubicacion))){
                        JOptionPane.showMessageDialog(null, "Error inesperado al remover la planta");
                    }
                    UbicacionBorrar.setText("");
                }
                
            }
            
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSimularValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularValoresActionPerformed
        controlador.simularParametros();
    }//GEN-LAST:event_btnSimularValoresActionPerformed

    private void btnSimularPasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimularPasoActionPerformed
        controlador.avanzarPaso();
    }//GEN-LAST:event_btnSimularPasoActionPerformed

    private void btnSimulacionContinuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimulacionContinuaActionPerformed
        
        if(!simulando){
            btnSimularPaso.setEnabled(false);
            btnSimularValores.setEnabled(false);
            btnSimulacionContinua.setText("Detener Simulacion");
            controlador.activarSimulador();
            simulando = true;
        }else{
            btnSimularPaso.setEnabled(true);
            btnSimularValores.setEnabled(true);
            btnSimulacionContinua.setText("Simulacion Continua");
            controlador.desactivarSimulador();
            simulando = false;
        }
        
        
    }//GEN-LAST:event_btnSimulacionContinuaActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField PlantaSeleccionadaAgregar;
    private javax.swing.JList<String> PlantasDisponibles;
    private javax.swing.JTable TablaPlantas;
    private javax.swing.JComboBox<String> TipoPlantas;
    private javax.swing.JTextField UbicacionAgregar;
    private javax.swing.JTextField UbicacionBorrar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnSimulacionContinua;
    private javax.swing.JButton btnSimularPaso;
    private javax.swing.JButton btnSimularValores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
