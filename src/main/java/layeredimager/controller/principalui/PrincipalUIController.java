package layeredimager.controller.principalui;

import layeredimager.controller.cargadatos.CargaCapasController;
import layeredimager.controller.cargadatos.CargaImagesController;
import layeredimager.view.PrincipalView;
import layeredimager.view.cargadatos.CargaFileView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import layeredimager.controller.cargadatos.CargaUsersController;
import layeredimager.controller.memorystatus.GenerarMatrizCapaController;
import layeredimager.controller.user.AddUserController;
import layeredimager.imagegenerator.list.ImagesCapImageGenerator;
import layeredimager.imagegenerator.tree.CapsImageGenerator;
import layeredimager.imagegenerator.tree.UsersImageGenerator;
import layeredimager.view.memorystatus.GenerarMatrizCapaView;
import layeredimager.view.user.AddUserView;

/**
 *
 * @date 11/04/2021
 * @time 12:56:33
 * @author asael
 */
public class PrincipalUIController implements ActionListener {
    
    private PrincipalView view;
    
    //Vista y Controllador para cargar capas
    CargaFileView capasV = new CargaFileView();
    CargaCapasController capasC = new CargaCapasController(capasV);
    
    //Vista y controlador para cargar imagenes
    CargaFileView imagesV = new CargaFileView();
    CargaImagesController imagesC = new CargaImagesController(imagesV);
    
    //Vista y controlador para cargar usuarios
    CargaFileView usersV = new CargaFileView();
    CargaUsersController usersC = new CargaUsersController(usersV);
    
    //Vista y controlador para generar matriz de capa
    GenerarMatrizCapaView generarMatrizV = new GenerarMatrizCapaView();
    GenerarMatrizCapaController generarMatrizC = new GenerarMatrizCapaController(generarMatrizV, capasC.getCapas());
    
    //Vista y controlador para add User
    AddUserView addUserV = new AddUserView();
    AddUserController addUserC = new AddUserController(addUserV);
    
    public PrincipalUIController(PrincipalView view) {
        this.view = view;
        
        this.view.getItmCargaCaps().addActionListener(this);
        this.view.getItmCargaImages().addActionListener(this);
        this.view.getItmCargaUsers().addActionListener(this);
        
        this.view.getItmAddUser().addActionListener(this);
        this.view.getItmDeleteUser().addActionListener(this);
        this.view.getItmEditUser().addActionListener(this);
        this.view.getItmAddImage().addActionListener(this);
        
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
            usersC.setImages(imagesC.getImages());
            usersC.iniciar(view.getPnlDesk());
        } else if (e.getSource() == view.getItmCaps()) {
            generarMatrizC.iniciar(view.getPnlDesk());
        } else if (e.getSource() == view.getItmTreeCaps()) {
            var capGenerator = new CapsImageGenerator(capasC.getCapas());
            capGenerator.generate();
        } else if (e.getSource() == view.getItmTreeUsers()) {
            var usersImageG = new UsersImageGenerator(usersC.getUsers());
            usersImageG.generate();
        } else if (e.getSource() == view.getItmListImg()) {
            var imageG = new ImagesCapImageGenerator(imagesC.getImages());
            imageG.generate();
        } else if (e.getSource() == view.getItmAddUser()) {
            addUserC.setUsers(usersC.getUsers());
            addUserC.iniciar(view.getPnlDesk());
        }
    }

    
}
