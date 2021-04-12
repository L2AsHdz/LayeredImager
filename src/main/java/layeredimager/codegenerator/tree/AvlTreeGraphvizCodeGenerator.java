package layeredimager.codegenerator.tree;

import layeredimager.edd.TreeNode;
import layeredimager.edd.tree.ArbolAVL;
import layeredimager.codegenerator.CodeGenerator;
import layeredimager.model.Objeto;
import layeredimager.model.cap.Capa;

/**
 *
 * @date 12/04/2021
 * @time 01:28:03
 * @author asael
 */
public class AvlTreeGraphvizCodeGenerator extends CodeGenerator {

    private ArbolAVL capas;

    public AvlTreeGraphvizCodeGenerator(ArbolAVL capas) {
        this.capas = capas;
    }

    @Override
    public String generate() {
        text = new StringBuilder();

        generateHeader();
        generateNodos(capas.getRaiz());
        generarPointers(capas.getRaiz());
        addLine("}", 0);

        return text.toString();
    }

    private void generateHeader() {
        addLine("""
                digraph grafica{
                    rankdir=TB;
                    node [shape = record];""", 0);
    }

    private void generateNodos(TreeNode<Objeto> root) {
        if (root != null) {
            String id = ((Capa)root.getDato()).getName();
            generateNodos(root.getLeft());
            text.append("    ").append(id);
            if (root.getLeft() == null && root.getRight() == null) {
                text.append("[ label =\"").append(id).append("\"];\n");
            } else {
                text.append("[ label =\"<C0>|").append(id)
                        .append("|<C1>\" ];\n");
            }
            generateNodos(root.getRight());
        }
    }

    private void generarPointers(TreeNode<Objeto> root) {
        if (root != null) {
            String name = ((Capa)root.getDato()).getName();
            generarPointers(root.getLeft());
            if (root.getLeft() != null) {
                String nameL = ((Capa)root.getLeft().getDato()).getName();
                text.append("    ").append(name).append(":C0").append(" -> ")
                        .append(nameL).append(";\n");
            }
            if (root.getRight() != null) {
                String nameR = ((Capa)root.getRight().getDato()).getName();
                text.append("    ").append(name).append(":C1").append(" -> ")
                        .append(nameR).append(";\n");
            }
            generarPointers(root.getRight());
        }
    }

}
