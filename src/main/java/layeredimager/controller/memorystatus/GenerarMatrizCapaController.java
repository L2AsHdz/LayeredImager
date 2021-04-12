package layeredimager.controller.memorystatus;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import static layeredimager.controller.FileController.saveFile;
import layeredimager.edd.TreeNode;
import layeredimager.edd.sparsematrix.SparseMatrix;
import layeredimager.view.memorystatus.GenerarMatrizCapaView;
import layeredimager.edd.tree.ArbolAVL;
import layeredimager.generator.sparsematrix.SparseMatrixGraphvizCodeGenerator;
import layeredimager.model.Objeto;
import layeredimager.model.cap.Capa;

/**
 *
 * @date 12/04/2021
 * @time 00:53:42
 * @author asael
 */
public class GenerarMatrizCapaController implements ActionListener {

    private GenerarMatrizCapaView generarMView;
    private ArbolAVL capas;

    public GenerarMatrizCapaController(GenerarMatrizCapaView generarMView, ArbolAVL capas) {
        this.generarMView = generarMView;
        this.capas = capas;

        this.generarMView.getBtnGenerar().addActionListener(this);
    }

    public void iniciar(JPanel parent) {
        parent.removeAll();
        parent.repaint();
        generarMView.setSize(parent.getSize());
        generarMView.setVisible(true);
        parent.add(generarMView);
        parent.validate();
        cargarDatos(capas.getRaiz());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String idCapa = (String) generarMView.getCbCapas().getSelectedItem();
        Capa capa = (Capa) capas.get(Integer.parseInt(idCapa));
        generarArchivoDot(capa.getMatriz());
        generarPng();
        abrirarchivo("matriz.png");
    }

    private void cargarDatos(TreeNode<Objeto> root) {
        if (root != null) {
            cargarDatos(root.getLeft());
            generarMView.getCbCapas().addItem("" + root.getDato().getId());
            cargarDatos(root.getRight());
        }
    }

    private void generarArchivoDot(SparseMatrix matriz) {
        var generator = new SparseMatrixGraphvizCodeGenerator(matriz);
        saveFile("matriz.dot", generator.generate());
    }

    private void generarPng() {
        try {
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "matriz.png", "matriz.dot");
            pbuilder.redirectErrorStream(true);
            pbuilder.start();

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void abrirarchivo(String archivo) {
        try {
            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }
}
