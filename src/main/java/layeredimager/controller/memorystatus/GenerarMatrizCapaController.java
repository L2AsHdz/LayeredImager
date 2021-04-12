package layeredimager.controller.memorystatus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import layeredimager.edd.TreeNode;
import layeredimager.view.memorystatus.GenerarMatrizCapaView;
import layeredimager.edd.tree.ArbolAVL;
import layeredimager.model.Objeto;
import layeredimager.model.cap.Capa;
import layeredimager.imagegenerator.sparsematrix.SparseMatrixImageGenerator;

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
        generarMView.limpiarCampos();
        cargarDatos(capas.getRaiz());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String idCapa = (String) generarMView.getCbCapas().getSelectedItem();
        Capa capa = (Capa) capas.get(Integer.parseInt(idCapa));
        var matrixImageG = new SparseMatrixImageGenerator(capa.getMatriz());
        matrixImageG.generate();
    }

    private void cargarDatos(TreeNode<Objeto> root) {
        if (root != null) {
            cargarDatos(root.getLeft());
            generarMView.getCbCapas().addItem("" + root.getDato().getId());
            cargarDatos(root.getRight());
        }
    }
}
