package layeredimager.controller.cargadatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import layeredimager.analyzer.UserFileAnalizer;
import static layeredimager.controller.FileController.readFile;
import layeredimager.edd.list.CircularList;
import layeredimager.edd.list.Lista;
import layeredimager.edd.tree.ArbolAVL;
import layeredimager.model.image.Imagen;
import layeredimager.view.cargadatos.CargaFileView;

/**
 *
 * @date 11/04/2021
 * @time 20:45:22
 * @author asael
 */
public class CargaUsersController implements ActionListener {

    private final CargaFileView usersV;
    private ArbolAVL users = new ArbolAVL();
    private CircularList images;

    public CargaUsersController(CargaFileView usersV) {
        this.usersV = usersV;
        
        this.usersV.getBtnBuscar().addActionListener(this);
        this.usersV.getBtnCargar().addActionListener(this);
    }
    
    public void iniciar(JPanel parent) {
        parent.removeAll();
        parent.repaint();
        usersV.setSize(parent.getSize());
        usersV.getBtnCargar().setText("Cargar usuarios");
        usersV.setVisible(true);
        parent.add(usersV);
        parent.validate();
        usersV.limpiarCampos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == usersV.getBtnBuscar()) {
            String content = buscarArchivo();
            if (!content.isEmpty()) {
                usersV.getTxtAreaInfo().setText(content);
                usersV.getBtnCargar().setEnabled(true);
            }
        } else if (e.getSource() == usersV.getBtnCargar()) {
            cargarDatos(usersV.getTxtAreaInfo().getText());
        }
    }

    private String buscarArchivo() {
        String content = "";

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("USER Documents", "usr"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.showOpenDialog(this.usersV);
        try {
            String path = fc.getSelectedFile().getAbsolutePath();
            usersV.getLblNameFile().setText(path);
            content = readFile(path);
        } catch (Exception ex) {
            System.out.println("se cancelo");
        }
        return content;
    }

    private void cargarDatos(String text) {
        StringBuilder texto = new StringBuilder();
        UserFileAnalizer analyzer = new UserFileAnalizer();
        analyzer.analyze(text);
        
        analyzer.getInfoUsers().forEach(u -> {
            boolean canSaved = true;
            Lista imagesList = new Lista();
            for (Integer i : u.getIdImages()) {
                Imagen img = images.get(i);
                if (img != null) {
                    imagesList.add(img);
                } else {
                    canSaved = false;
                    texto.append("La imagen ").append(i).append(" no existe en el sistema\n");
                }
            }
            if (canSaved) {
                //users.add(o);
                texto.append("Usuario ").append(u.getName()).append(" ingresado al sistema");
            } else {
                texto.append("No se ingreso al sistema el usuario ").append(u.getName()).append("\n");
            }
        });
        
    }

    public void setImages(CircularList images) {
        this.images = images;
    }

    public ArbolAVL getUsers() {
        return users;
    }
}
