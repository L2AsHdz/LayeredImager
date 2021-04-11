package com.l2ashdz.layeredimager.controller.principalui;

import com.l2ashdz.layeredimager.view.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @date 11/04/2021
 * @time 12:56:33
 * @author asael
 */
public class PrincipalUIController implements ActionListener {
    
    private PrincipalView view;

    public PrincipalUIController(PrincipalView view) {
        this.view = view;
    }
    
    public void iniciar() {
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
