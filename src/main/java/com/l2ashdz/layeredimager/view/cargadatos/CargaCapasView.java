/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2ashdz.layeredimager.view.cargadatos;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author asael
 */
public class CargaCapasView extends javax.swing.JPanel {

    /**
     * Creates new form CargaCapasView
     */
    public CargaCapasView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaInfo = new javax.swing.JTextArea();
        lblNameFile = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();

        setEnabled(false);

        txtAreaInfo.setEditable(false);
        txtAreaInfo.setColumns(20);
        txtAreaInfo.setRows(5);
        jScrollPane1.setViewportView(txtAreaInfo);

        lblNameFile.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblNameFile.setText("*No se ha escogido un archivo*");

        btnBuscar.setText("Buscar archivo");

        btnCargar.setText("Cargar capas");
        btnCargar.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNameFile)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCargar)))
                        .addGap(0, 328, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNameFile)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnCargar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNameFile;
    private javax.swing.JTextArea txtAreaInfo;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public JTextArea getTxtAreaInfo() {
        return txtAreaInfo;
    }

    public JLabel getLblNameFile() {
        return lblNameFile;
    }
}
