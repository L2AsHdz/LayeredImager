package layeredimager.codegenerator.list;

import layeredimager.codegenerator.CodeGenerator;
import layeredimager.edd.Nodo;
import layeredimager.edd.list.CircularList;
import layeredimager.model.Objeto;
import layeredimager.model.cap.Capa;
import layeredimager.model.image.Imagen;

/**
 *
 * @date 12/04/2021
 * @time 04:04:40
 * @author asael
 */
public class ListGraphvizCodeGenerator extends CodeGenerator {

    private CircularList images;

    public ListGraphvizCodeGenerator(CircularList images) {
        this.images = images;
    }

    @Override
    public String generate() {
        text = new StringBuilder();

        generateHeader();
        generarNodosImagen();
        generarNodosCaps();
        addLine("}", 0);

        return text.toString();
    }

    private void generateHeader() {
        addLine("""
                digraph g {
                    node[shape=rect,width=1,height=.1];""", 0);
    }

    private void generarNodosImagen() {
        Nodo<Imagen> primero = images.getPrimero();
        Nodo<Imagen> actual = primero;

        if (actual == null) {
            System.out.println("Lista vacia");
        } else {
            do {
                String name = actual.getDato().getNombre();
                text.append("    ").append(name).append("[label = \"")
                        .append(name).append("\"];\n");
                actual = actual.getNext();
            } while (actual != primero);
        }
    }

    private void generarNodosCaps() {
        Nodo<Imagen> primero = images.getPrimero();
        Nodo<Imagen> actual = primero;

        if (actual != null) {
            do {
                Nodo<Objeto> inicio = actual.getDato().getCapas().getInicio();
                Nodo<Objeto> actualC = inicio;

                if (actualC != null) {
                    int idI = actual.getDato().getId();
                    int idC = actualC.getDato().getId();
                    String nameImg = "img" + idI + idC;
                    String nameCap = ((Capa) inicio.getDato()).getName();
                    
                    while (actualC != null) {
                        text.append("    ").append(nameImg).append("[label = \"")
                                .append(nameCap).append("\" ];\n");
                        actualC = actualC.getNext();
                    }
                }

                actual = actual.getNext();
            } while (actual != primero);
        }
    }
}
