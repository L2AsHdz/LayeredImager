package layeredimager.controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import layeredimager.edd.list.Lista;
import layeredimager.edd.tree.UserArbolAVL;
import layeredimager.model.user.Usuario;
import layeredimager.view.user.AddUserView;

/**
 *
 * @date 12/04/2021
 * @time 06:00:42
 * @author asael
 */
public class AddUserController implements ActionListener {

    private AddUserView addUserV;
    private UserArbolAVL users;

    public AddUserController(AddUserView addUserV) {
        this.addUserV = addUserV;

        this.addUserV.getBtnAddUser().addActionListener(this);
    }

    public void iniciar(JPanel parent) {
        parent.removeAll();
        parent.repaint();
        addUserV.setSize(parent.getSize());
        addUserV.setVisible(true);
        parent.add(addUserV);
        parent.validate();
        addUserV.limpiarCampos();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String name = addUserV.getTxtUserName().getText();
        if (!name.trim().isEmpty()) {
            users.add(new Usuario(name, new Lista()));
            addUserV.getTxtUserName().setText("");
            addUserV.getLblInfo().setText("Usuario " + name + " agregado existosamente");
        } else {
            addUserV.getLblInfo().setText("El nombre no puede ser vacio");
        }
    }

    public void setUsers(UserArbolAVL users) {
        this.users = users;
    }
}
