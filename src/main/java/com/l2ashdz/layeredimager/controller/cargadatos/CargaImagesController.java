package com.l2ashdz.layeredimager.controller.cargadatos;

import com.l2ashdz.layeredimager.analyzer.ImageFileAnalyzer;
import static com.l2ashdz.layeredimager.controller.FileController.readFile;
import com.l2ashdz.layeredimager.edd.tree.ArbolAVL;
import com.l2ashdz.layeredimager.model.image.PreImagen;
import com.l2ashdz.layeredimager.view.cargadatos.CargaFileView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @date 11/04/2021
 * @time 14:14:37
 * @author asael
 */
public class CargaImagesController implements ActionListener {

    private final CargaFileView imagesV;
    private ArbolAVL capas;
    private List<PreImagen> preImagenes;

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
        ImageFileAnalyzer analyzer = new ImageFileAnalyzer();
        analyzer.analyze(text);
        preImagenes = analyzer.getPreImagenes();
        imagesV.getTxtAreaInfo().setText("Imagenes cargadas al sistema exitosamente!");
    }

    public void setCapas(ArbolAVL capas) {
        this.capas = capas;
    }

    public List<PreImagen> getPreImagenes() {
        return preImagenes;
    }
}
