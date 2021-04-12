package layeredimager.controller.cargadatos;

import layeredimager.analyzer.ImageFileAnalyzer;
import static layeredimager.controller.FileController.readFile;
import layeredimager.edd.tree.ArbolAVL;
import layeredimager.view.cargadatos.CargaFileView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import layeredimager.edd.list.CircularList;
import layeredimager.edd.list.Lista;
import layeredimager.model.image.Imagen;

/**
 *
 * @date 11/04/2021
 * @time 14:14:37
 * @author asael
 */
public class CargaImagesController implements ActionListener {

    private final CargaFileView imagesV;
    private CircularList images = new CircularList();
    private ArbolAVL capas;

    public CargaImagesController(CargaFileView imagesV) {
        this.imagesV = imagesV;

        this.imagesV.getBtnBuscar().addActionListener(this);
        this.imagesV.getBtnCargar().addActionListener(this);
    }

    public void iniciar(JPanel parent) {
        parent.removeAll();
        parent.repaint();
        imagesV.setSize(parent.getSize());
        imagesV.getBtnCargar().setText("Cargar imagenes");
        imagesV.setVisible(true);
        parent.add(imagesV);
        parent.validate();
        imagesV.limpiarCampos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == imagesV.getBtnBuscar()) {
            String content = buscarArchivo();
            if (!content.isEmpty()) {
                imagesV.getTxtAreaInfo().setText(content);
                imagesV.getBtnCargar().setEnabled(true);
            }
        } else if (e.getSource() == imagesV.getBtnCargar()) {
            cargarDatos(imagesV.getTxtAreaInfo().getText());
        }
    }

    private String buscarArchivo() {
        String content = "";

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("IMAGE Documents", "im"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.showOpenDialog(this.imagesV);
        try {
            String path = fc.getSelectedFile().getAbsolutePath();
            imagesV.getLblNameFile().setText(path);
            content = readFile(path);
        } catch (Exception ex) {
            System.out.println("se cancelo");
        }
        return content;
    }

    private void cargarDatos(String text) {
        StringBuilder texto = new StringBuilder();
        ImageFileAnalyzer analyzer = new ImageFileAnalyzer();
        analyzer.analyze(text);

        analyzer.getPreImagenes().forEach(i -> {
            boolean canSaved = true;
            Lista capasList = new Lista();
            for (Integer c : i.getCapas()) {
                try {
                    capasList.add(capas.get(c));
                } catch (Exception e) {
                    canSaved = false;
                    texto.append("La capa ").append(c).append(" no existe en el sistema\n");
                }
            }
            if (canSaved) {
                images.add(new Imagen(i.getId(), capasList));
                texto.append("Imagen ").append(i.getId()).append(" ingresada al sistema\n");
            } else {
                texto.append("No se ingreso al sistema la imagen ").append(i.getId()).append("\n");
            }
        });
        images.sort();
        images.show();
        imagesV.getTxtAreaInfo().setText(texto.toString());
    }

    public void setCapas(ArbolAVL capas) {
        this.capas = capas;
    }

    public CircularList getImages() {
        return images;
    }
}
