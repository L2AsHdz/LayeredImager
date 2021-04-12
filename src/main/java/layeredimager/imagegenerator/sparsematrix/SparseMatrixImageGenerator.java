package layeredimager.imagegenerator.sparsematrix;

import layeredimager.codegenerator.sparsematrix.SparseMatrixGraphvizCodeGenerator;
import static layeredimager.controller.FileController.abrirArchivo;
import static layeredimager.controller.FileController.saveFile;
import layeredimager.edd.sparsematrix.SparseMatrix;
import layeredimager.imagegenerator.ImageGenerator;

/**
 *
 * @date 12/04/2021
 * @time 02:39:03
 * @author asael
 */
public class SparseMatrixImageGenerator extends ImageGenerator {
    
    private SparseMatrix matriz;

    public SparseMatrixImageGenerator(SparseMatrix matriz) {
        this.matriz = matriz;
    }

    @Override
    public void generate() {
        generarArchivoDot(matriz);
        generarPng("matriz.dot", "matriz.png");
        abrirArchivo("matriz.png");
    }
    
    private void generarArchivoDot(SparseMatrix matriz) {
        var generator = new SparseMatrixGraphvizCodeGenerator(matriz);
        saveFile("matriz.dot", generator.generate());
    }

}
