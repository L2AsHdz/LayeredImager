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
        generateRank();
        generarPointers();
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

                    while (actualC != null) {
                        int idI = actual.getDato().getId();
                        int idC = actualC.getDato().getId();
                        String nameImg = "img" + idI + idC;
                        String nameCap = ((Capa) inicio.getDato()).getName();
                        text.append("    ").append(nameImg).append("[label = \"")
                                .append(nameCap).append("\" ];\n");
                        actualC = actualC.getNext();
                    }
                }
                actual = actual.getNext();
            } while (actual != primero);
        }
    }

    private void generateRank() {
        Nodo<Imagen> primero = images.getPrimero();
        Nodo<Imagen> actual = primero;

        text.append("    {rank = same; ");
        if (actual != null) {
            do {
                String name = actual.getDato().getNombre();
                text.append(name).append("; ");

                actual = actual.getNext();
            } while (actual != primero);
        }
        text.append("}\n");
    }

    private void generarPointers() {
        Nodo<Imagen> primero = images.getPrimero();
        Nodo<Imagen> actual = primero;

        if (actual != null) {
            do {
                String name = actual.getDato().getNombre();
                String nameP = actual.getPrev().getDato().getNombre();
                String nameN = actual.getNext().getDato().getNombre();

                if (actual == primero) {
                    text.append("    ").append(name).append(" -> ")
                            .append(nameN).append(";\n");
                    text.append("    ").append(name).append(" -> ")
                            .append(nameP).append("[constraint=false]").append(";\n");
                } else {
                    text.append("    ").append(name).append(" -> ")
                            .append(nameN).append(";\n");
                    text.append("    ").append(name).append(" -> ")
                            .append(nameP).append(";\n");
                }

                actual = actual.getNext();
            } while (actual != primero);
        }
    }
}
