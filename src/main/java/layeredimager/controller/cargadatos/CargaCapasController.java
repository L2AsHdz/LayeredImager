package layeredimager.controller.cargadatos;

import layeredimager.analyzer.LayerFileAnalyzer;
import static layeredimager.controller.FileController.readFile;
import layeredimager.edd.tree.ArbolAVL;
import layeredimager.view.cargadatos.CargaFileView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import layeredimager.edd.sparsematrix.SparseMatrix;
import layeredimager.model.cap.Capa;

/**
 *
 * @date 11/04/2021
 * @time 13:22:21
 * @author asael
 */
public class CargaCapasController implements ActionListener {

    private final CargaFileView capasV;
    private ArbolAVL capas = new ArbolAVL();

    public CargaCapasController(CargaFileView capasV) {
        this.capasV = capasV;

        this.capasV.getBtnBuscar().addActionListener(this);
        this.capasV.getBtnCargar().addActionListener(this);
    }

    public void iniciar(JPanel parent) {
        parent.removeAll();
        parent.repaint();
        capasV.setSize(parent.getSize());
        capasV.getBtnCargar().setText("Cargar capas");
        capasV.setVisible(true);
        parent.add(capasV);
        parent.validate();
        capasV.limpiarCampos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == capasV.getBtnBuscar()) {
            String content = buscarArchivo();
            if (!content.isEmpty()) {
                capasV.getTxtAreaInfo().setText(content);
                capasV.getBtnCargar().setEnabled(true);
            }
        } else if (e.getSource() == capasV.getBtnCargar()) {
            cargarCapas(capasV.getTxtAreaInfo().getText());
        }
    }

    private String buscarArchivo() {
        String content = "";

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new FileNameExtensionFilter("CAP Documents", "cap"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.showOpenDialog(this.capasV);
        try {
            String path = fc.getSelectedFile().getAbsolutePath();
            capasV.getLblNameFile().setText(path);
            content = readFile(path);
        } catch (Exception ex) {
            System.out.println("se cancelo");
        }
        return content;
    }

    private void cargarCapas(String text) {
        StringBuilder texto = new StringBuilder();
        LayerFileAnalyzer analizer = new LayerFileAnalyzer();
        analizer.analyze(text);
        analizer.getInfoCaps().forEach(c -> {
            SparseMatrix matriz = new SparseMatrix();
            c.getInfoNodos().forEach(n -> {
                matriz.add(n.getColor(), n.getX(), n.getY());
            });
            capas.add(new Capa(matriz, c.getId()));
            texto.append("Capa ").append(c.getId()).append("ingresada al sistema").append("\n");
        });
        capasV.getTxtAreaInfo().setText(texto.toString());
    }

    public ArbolAVL getCapas() {
        return this.capas;
    }
}
