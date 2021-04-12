package layeredimager.codegenerator.tree;

import layeredimager.edd.TreeNode;
import layeredimager.edd.tree.UserArbolAVL;
import layeredimager.codegenerator.CodeGenerator;
import layeredimager.model.user.Usuario;

/**
 *
 * @date 12/04/2021
 * @time 02:09:29
 * @author asael
 */
public class UserAvlTreeGraphvizCodeGenerator extends CodeGenerator {
    
    private UserArbolAVL users; 

    public UserAvlTreeGraphvizCodeGenerator(UserArbolAVL users) {
        this.users = users;
    }

    @Override
    public String generate() {
        text = new StringBuilder();

        generateHeader();
        generateNodos(users.getRaiz());
        generarPointers(users.getRaiz());
        addLine("}", 0);

        return text.toString();
    }
    
    private void generateHeader() {
        addLine("""
                digraph arbolUsers{
                    rankdir=TB;
                    node [shape = record];""", 0);
    }
    
    private void generateNodos(TreeNode<Usuario> root) {
        if (root != null) {
            String id = root.getDato().getNombre();
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
    
    private void generarPointers(TreeNode<Usuario> root) {
        if (root != null) {
            String name = root.getDato().getNombre();
            generarPointers(root.getLeft());
            if (root.getLeft() != null) {
                String nameL = root.getLeft().getDato().getNombre();
                text.append("    ").append(name).append(":C0").append(" -> ")
                        .append(nameL).append(";\n");
            }
            if (root.getRight() != null) {
                String nameR = root.getRight().getDato().getNombre();
                text.append("    ").append(name).append(":C1").append(" -> ")
                        .append(nameR).append(";\n");
            }
            generarPointers(root.getRight());
        }
    }

}
