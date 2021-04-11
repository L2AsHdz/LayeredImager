package layeredimager.controller.principalui;

import layeredimager.controller.cargadatos.CargaCapasController;
import layeredimager.controller.cargadatos.CargaImagesController;
import layeredimager.edd.list.CircularList;
import layeredimager.edd.tree.ArbolAVL;
import layeredimager.view.PrincipalView;
import layeredimager.view.cargadatos.CargaFileView;
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
    
    private ArbolAVL capas;
    private CircularList imagenes;
    
    //Vista y Controllador para cargar capas
    CargaFileView capasV = new CargaFileView();
    CargaCapasController capasC = new CargaCapasController(capasV);
    
    //Vista y controlador para cargar imagenes
    CargaFileView imagesV = new CargaFileView();
    CargaImagesController imagesC = new CargaImagesController(imagesV);

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
            imagesC.setCapas(capasC.getCapas());
            imagesC.iniciar(view.getPnlDesk());
        } else if (e.getSource() == view.getItmCargaUsers()) {
            
        }
    }

}
