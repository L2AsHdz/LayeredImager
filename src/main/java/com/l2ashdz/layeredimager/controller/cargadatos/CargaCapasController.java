package com.l2ashdz.layeredimager.controller.cargadatos;

import com.l2ashdz.layeredimager.view.cargadatos.CargaCapasView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @date 11/04/2021
 * @time 13:22:21
 * @author asael
 */
public class CargaCapasController implements ActionListener {

    CargaCapasView capasV; 

    public CargaCapasController(CargaCapasView capasV) {
        this.capasV = capasV;
    }
    
    public void iniciar(JPanel parent) {
        if (!capasV.isEnabled()) {
            parent.removeAll();
            parent.repaint();
            capasV.setSize(parent.getSize());
            capasV.setEnabled(true);
            capasV.setVisible(true);
            parent.add(capasV);
            parent.validate();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
