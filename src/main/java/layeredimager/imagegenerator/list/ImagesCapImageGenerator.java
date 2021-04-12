package layeredimager.imagegenerator.list;

import layeredimager.codegenerator.list.ListGraphvizCodeGenerator;
import static layeredimager.controller.FileController.abrirArchivo;
import static layeredimager.controller.FileController.saveFile;
import layeredimager.edd.list.CircularList;
import layeredimager.imagegenerator.ImageGenerator;

/**
 *
 * @date 12/04/2021
 * @time 04:13:52
 * @author asael
 */
public class ImagesCapImageGenerator extends ImageGenerator {

    private CircularList images;

    public ImagesCapImageGenerator(CircularList images) {
        this.images = images;
    }

    @Override
    public void generate() {
        generarArchivoDot(images);
        generarPng("listImages.dot", "listImages.png");
        abrirArchivo("listImages.png");
    }
    
    private void generarArchivoDot(CircularList images) {
        var generator = new ListGraphvizCodeGenerator(images);
        saveFile("listImages.dot", generator.generate());
    }
}
