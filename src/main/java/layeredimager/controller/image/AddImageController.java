package layeredimager.controller.image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import layeredimager.edd.Nodo;
import layeredimager.edd.TreeNode;
import layeredimager.edd.list.CircularList;
import layeredimager.edd.tree.UserArbolAVL;
import layeredimager.model.image.Imagen;
import layeredimager.model.user.Usuario;
import layeredimager.view.image.AddImageView;

/**
 *
 * @date 12/04/2021
 * @time 08:26:30
 * @author asael
 */
public class AddImageController implements ActionListener {

    private AddImageView addImageV;
    private UserArbolAVL users;
    private CircularList images;

    public AddImageController(AddImageView addImageV) {
        this.addImageV = addImageV;
        
        this.addImageV.getBtnAgregar().addActionListener(this);
    }
    
    public void iniciar(JPanel parent) {
        parent.removeAll();
        parent.repaint();
        addImageV.setSize(parent.getSize());
        addImageV.setVisible(true);
        parent.add(addImageV);
        parent.validate();
        addImageV.limpiarCampos();
        cargarDatosUsers(users.getRaiz());
        cargarDatosImages(images.getPrimero());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String nameUser = (String) addImageV.getCbUsers().getSelectedItem();
        String idImagen = (String) addImageV.getCbImages().getSelectedItem();
        
        Usuario user = users.get(nameUser);
        Imagen image = (Imagen) user.getImagenes().get(Integer.parseInt(idImagen));
        
        if (image == null) {
            user.getImagenes().add(images.get(Integer.parseInt(idImagen)));
            addImageV.getLblInfo().setText("Imagen " + idImagen + " agregada al usuario " + nameUser);
        } else {
            addImageV.getLblInfo().setText("La imagen " + idImagen + " ya existe en el usuario " + nameUser);
        } 
    }

    public void setUsers(UserArbolAVL users) {
        this.users = users;
    }

    public void setImages(CircularList images) {
        this.images = images;
    }

    private void cargarDatosUsers(TreeNode<Usuario> root) {
        if (root != null) {
            cargarDatosUsers(root.getLeft());
            addImageV.getCbUsers().addItem(root.getDato().getNombre());
            cargarDatosUsers(root.getRight());
        }
    }

    private void cargarDatosImages(Nodo<Imagen> primero) {
        Nodo<Imagen> actual = primero;
        if (actual == null) {
            System.out.println("Lista vacia");
        } else {
            do {
                addImageV.getCbImages().addItem("" +actual.getDato().getId());
                actual = actual.getNext();
            } while (actual != primero);

        }
    }
}
