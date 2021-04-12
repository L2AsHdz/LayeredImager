package layeredimager.controller.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import layeredimager.view.user.AddUserView;

/**
 *
 * @date 12/04/2021
 * @time 06:00:42
 * @author asael
 */
public class AddUserController implements ActionListener {

    private AddUserView addUserV;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
