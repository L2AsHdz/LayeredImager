package layeredimager.controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import layeredimager.edd.TreeNode;
import layeredimager.edd.tree.UserArbolAVL;
import layeredimager.model.user.Usuario;
import layeredimager.view.user.DeleteUserView;

/**
 *
 * @date 12/04/2021
 * @time 06:26:41
 * @author asael
 */
public class DeleteUserController implements ActionListener {

    private DeleteUserView deleteUV;
    private UserArbolAVL users;

    public DeleteUserController(DeleteUserView deleteUV) {
        this.deleteUV = deleteUV;
        
        this.deleteUV.getBtnDeleteUser().addActionListener(this);
    }
    
    public void iniciar(JPanel parent) {
        parent.removeAll();
        parent.repaint();
        deleteUV.setSize(parent.getSize());
        deleteUV.setVisible(true);
        parent.add(deleteUV);
        parent.validate();
        deleteUV.limpiarCampos();
        cargarDatos(users.getRaiz());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = (String) deleteUV.getCbUsers().getSelectedItem();
        users.remove(userName);
        deleteUV.getLblInfo().setText("Usuario " + userName + " eliminado exitosamente");
    }

    private void cargarDatos(TreeNode<Usuario> root) {
        if (root != null) {
            cargarDatos(root.getLeft());
            deleteUV.getCbUsers().addItem(root.getDato().getNombre());
            cargarDatos(root.getRight());
        }
    }

    public void setUsers(UserArbolAVL users) {
        this.users = users;
    }
}
