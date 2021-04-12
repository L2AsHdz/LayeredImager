package layeredimager.imagegenerator.tree;

import layeredimager.codegenerator.tree.AvlTreeGraphvizCodeGenerator;
import static layeredimager.controller.FileController.abrirArchivo;
import static layeredimager.controller.FileController.saveFile;
import layeredimager.edd.tree.ArbolAVL;
import layeredimager.imagegenerator.ImageGenerator;

/**
 *
 * @date 12/04/2021
 * @time 02:26:43
 * @author asael
 */
public class CapsImageGenerator extends ImageGenerator {
    
    private ArbolAVL capas;

    public CapsImageGenerator(ArbolAVL capas) {
        this.capas = capas;
    }

    @Override
    public void generate() {
        generarArchivoDot(capas);
        generarPng("arbolCapas.dot", "arbolCapas.png");
        abrirArchivo("arbolCapas.png");
    }

    private void generarArchivoDot(ArbolAVL capas) {
        var generator = new AvlTreeGraphvizCodeGenerator(capas);
        saveFile("arbolCapas.dot", generator.generate());
    }
}
