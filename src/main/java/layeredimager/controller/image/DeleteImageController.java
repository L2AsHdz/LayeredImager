package layeredimager.controller.image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import layeredimager.edd.Nodo;
import layeredimager.edd.TreeNode;
import layeredimager.edd.tree.UserArbolAVL;
import layeredimager.model.Objeto;
import layeredimager.model.user.Usuario;
import layeredimager.view.image.DeleteImageView;

/**
 *
 * @date 12/04/2021
 * @time 08:54:22
 * @author asael
 */
public class DeleteImageController implements ActionListener {

    private DeleteImageView deleteIV;
    private UserArbolAVL users;

    public DeleteImageController(DeleteImageView deleteIV) {
        this.deleteIV = deleteIV;
        
        this.deleteIV.getBtnVerImages().addActionListener(this);
        this.deleteIV.getBtnDelete().addActionListener(this);
    }
    
    public void iniciar(JPanel parent) {
        parent.removeAll();
        parent.repaint();
        deleteIV.setSize(parent.getSize());
        deleteIV.setVisible(true);
        parent.add(deleteIV);
        parent.validate();
        deleteIV.limpiarCampos();
        cargarDatosUsers(users.getRaiz());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteIV.getBtnVerImages()) {
            String nameUser = (String) deleteIV.getCbUsers().getSelectedItem();
            cargarDatosImages(users.get(nameUser).getImagenes().getInicio());
            deleteIV.getLblInfo().setText("Imagenes del usuario " + nameUser + " cargadas");
        } else if (e.getSource() == deleteIV.getBtnDelete()) {
            String nameUser = (String) deleteIV.getCbUsers().getSelectedItem();
            String idImage = (String) deleteIV.getCbImages().getSelectedItem();
            
            users.get(nameUser).getImagenes().remove(Integer.parseInt(idImage));
            deleteIV.getLblInfo().setText("Imagen " + idImage + " eliminada del usuario " + nameUser);
            deleteIV.getCbImages().removeAllItems();
            cargarDatosImages(users.get(nameUser).getImagenes().getInicio());
        }
    }

    private void cargarDatosUsers(TreeNode<Usuario> root) {
        if (root != null) {
            cargarDatosUsers(root.getLeft());
            deleteIV.getCbUsers().addItem(root.getDato().getNombre());
            cargarDatosUsers(root.getRight());
        }
    }

    public void setUsers(UserArbolAVL users) {
        this.users = users;
    }

    private void cargarDatosImages(Nodo<Objeto> inicio) {
        Nodo<Objeto> actual = inicio;
        
        if (actual == null) {
            deleteIV.getCbImages().removeAllItems();
        } else {
            deleteIV.getBtnDelete().setEnabled(true);
            while (actual != null) {
                deleteIV.getCbImages().addItem("" + actual.getDato().getId());
                actual = actual.getNext();
            }
        }
    }
}
