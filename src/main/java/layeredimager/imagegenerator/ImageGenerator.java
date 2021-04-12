package layeredimager.imagegenerator;

import java.io.IOException;
import layeredimager.codegenerator.CodeGenerator;

/**
 *
 * @date 12/04/2021
 * @time 02:23:01
 * @author asael
 */
public abstract class ImageGenerator {
    
    protected CodeGenerator codeGenerator;
    
    public abstract void generate();

    protected void generarPng(String archivoDot, String archivoSalida) {
        try {
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", archivoSalida, archivoDot);
            pbuilder.redirectErrorStream(true);
            pbuilder.start();

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
