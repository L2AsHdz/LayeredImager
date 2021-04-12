package layeredimager.controller.cargadatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import static layeredimager.controller.FileController.readFile;
import layeredimager.edd.list.CircularList;
import layeredimager.edd.tree.ArbolAVL;
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
        
    }

    public void setImages(CircularList images) {
        this.images = images;
    }

    public ArbolAVL getUsers() {
        return users;
    }
}
