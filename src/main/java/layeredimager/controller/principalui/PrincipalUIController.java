package layeredimager.controller.principalui;

import java.awt.Desktop;
import layeredimager.controller.cargadatos.CargaCapasController;
import layeredimager.controller.cargadatos.CargaImagesController;
import layeredimager.view.PrincipalView;
import layeredimager.view.cargadatos.CargaFileView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import static layeredimager.controller.FileController.abrirArchivo;
import static layeredimager.controller.FileController.saveFile;
import layeredimager.controller.cargadatos.CargaUsersController;
import layeredimager.controller.memorystatus.GenerarMatrizCapaController;
import layeredimager.edd.tree.ArbolAVL;
import layeredimager.generator.tree.AvlTreegraphvizCodeGenerator;
import layeredimager.view.memorystatus.GenerarMatrizCapaView;

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
            usersC.setImages(imagesC.getImages());
            usersC.iniciar(view.getPnlDesk());
        } else if (e.getSource() == view.getItmCaps()) {
            generarMatrizC.iniciar(view.getPnlDesk());
        } else if (e.getSource() == view.getItmTreeCaps()) {
            generarImagenCapas(capasC.getCapas());
        }
    }

    private void generarImagenCapas(ArbolAVL capas) {
        generarArchivoDot(capas);
        generarPng();
        abrirArchivo("arbolCapas.png");
    }

    private void generarArchivoDot(ArbolAVL capas) {
        var generator = new AvlTreegraphvizCodeGenerator(capas);
        saveFile("arbolCapas.dot", generator.generate());
    }

    private void generarPng() {
        try {
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "arbolCapas.png", "arbolCapas.dot");
            pbuilder.redirectErrorStream(true);
            pbuilder.start();

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
