package layeredimager.imagegenerator.tree;

import layeredimager.codegenerator.tree.UserAvlTreeGraphvizCodeGenerator;
import static layeredimager.controller.FileController.abrirArchivo;
import static layeredimager.controller.FileController.saveFile;
import layeredimager.edd.tree.UserArbolAVL;
import layeredimager.imagegenerator.ImageGenerator;

/**
 *
 * @date 12/04/2021
 * @time 02:45:17
 * @author asael
 */
public class UsersImageGenerator extends ImageGenerator {
    
    private UserArbolAVL users;

    public UsersImageGenerator(UserArbolAVL users) {
        this.users = users;
    }

    @Override
    public void generate() {
        generarArchivoDot(users);
        generarPng("arbolUsers.dot", "arbolUsers.png");
        abrirArchivo("arbolUsers.png");
    }
    
    private void generarArchivoDot(UserArbolAVL users) {
        var generator = new UserAvlTreeGraphvizCodeGenerator(users);
        saveFile("arbolUsers.dot", generator.generate());
    }

}
