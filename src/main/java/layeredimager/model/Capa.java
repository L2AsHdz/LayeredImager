package layeredimager.model;

import layeredimager.edd.sparsematrix.SparseMatrix;

/**
 *
 * @date 4/04/2021
 * @time 21:25:10
 * @author asael
 */
public class Capa extends Objeto {

    private String name;
    private SparseMatrix matriz;
    
    public Capa() {
    }

    public Capa(SparseMatrix matriz, int id) {
        super(id);
        this.name = "Capa_" + String.valueOf(id);
        this.matriz = matriz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SparseMatrix getMatriz() {
        return matriz;
    }

    public void setMatriz(SparseMatrix matriz) {
        this.matriz = matriz;
    }
}
