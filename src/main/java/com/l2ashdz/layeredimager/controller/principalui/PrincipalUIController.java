package com.l2ashdz.layeredimager.controller.principalui;

import com.l2ashdz.layeredimager.controller.cargadatos.CargaCapasController;
import com.l2ashdz.layeredimager.view.PrincipalView;
import com.l2ashdz.layeredimager.view.cargadatos.CargaCapasView;
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
    
    //Vista y Controllador para cargar capas
    CargaCapasView capasV = new CargaCapasView();
    CargaCapasController capasC = new CargaCapasController(capasV);

    public PrincipalUIController(PrincipalView view) {
        this.view = view;
        
        this.view.getItmCargaCaps().addActionListener(this);
        this.view.getItmCargaImages().addActionListener(this);
        this.view.getItmCargaUsers().addActionListener(this);
        
        this.view.getItmUser().addActionListener(this);
        this.view.getItmImage().addActionListener(this);
        
        this.view.getItmListImg().addActionListener(this);
        this.view.getItmTreeCaps().addActionListener(this);
        this.view.getItmCaps().addActionListener(this);
        this.view.getItmImgCaps().addActionListener(this);
        this.view.getItmTreeUsers().addActionListener(this);
    }
    
    public void iniciar() {
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == view.getItmCargaCaps()) {
            capasC.iniciar(view.getPnlDesk());
        } else if (e.getSource() == view.getItmCargaImages()) {
            
        } else if (e.getSource() == view.getItmCargaUsers()) {
            
        }
    }

}
