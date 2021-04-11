package com.l2ashdz.layeredimager.generator.sparsematrix;

import com.l2ashdz.layeredimager.edd.MatrixNode;
import com.l2ashdz.layeredimager.edd.Nodo;
import com.l2ashdz.layeredimager.edd.sparsematrix.SparseMatrix;
import com.l2ashdz.layeredimager.generator.Generator;

/**
 *
 * @date 9/04/2021
 * @time 11:51:59
 * @author asael
 */
public class SparseMatrixGraphvizCodeGenerator extends Generator {

    private SparseMatrix matriz;

    public SparseMatrixGraphvizCodeGenerator(SparseMatrix matriz) {
        this.matriz = matriz;
    }

    @Override
    public String generate() {
        text = new StringBuilder();

        generateHeader();
        generateYAxis();
        generateYPointers();
        generateXAxis();
        generateXPointers();
        addLine(generateInitPointers(), 1);
        addLine(generateRankX(), 1);
        genereteMatrixNodes();
        generetePoinsters();
        generateRanks();
        addLine("}", 0);

        return text.toString();
    }

    private void generateHeader() {
        addLine("""
                digraph SparseMatrix {
                    node [shape=box]

                    Mt[ label = "Matriz", width = 1.5, group = 1 ];

                    e0[ shape = point, width = 0 ];
                    e1[ shape = point, width = 0 ];
                """, 0);
    }

    private void generateYAxis() {
        Nodo<Integer> currentY = matriz.getRaiz().getBelow();

        while (currentY != null) {
            int y = currentY.getDato();
            addLine("Y" + y + " [label = \"" + y + "\", width = 1.5, group = 1 ];", 1);
            currentY = currentY.getBelow();
        }
    }

    private void generateYPointers() {
        Nodo<Integer> currentY = matriz.getRaiz().getBelow();

        if (currentY != null && currentY.getBelow() != null) {
            addLine("Y" + currentY.getDato() + " -> Y" + currentY.getBelow().getDato() + ";", 1);
            currentY = currentY.getBelow();
        }
        while (currentY != null) {
            int y = currentY.getDato();
            addLine("Y" + y + " -> Y" + currentY.getAbove().getDato() + ";", 1);
            if (currentY.getBelow() != null) {
                addLine("Y" + y + " -> Y" + currentY.getBelow().getDato() + ";", 1);
            }
            currentY = currentY.getBelow();
        }
    }

    private void generateXPointers() {
        Nodo<Integer> currentX = matriz.getRaiz().getNext();

        if (currentX != null && currentX.getNext() != null) {
            addLine("X" + currentX.getDato() + " -> X" + currentX.getNext().getDato() + ";", 1);
            currentX = currentX.getNext();
        }
        while (currentX != null) {
            int x = currentX.getDato();
            addLine("X" + x + " -> X" + currentX.getPrev().getDato() + ";", 1);
            if (currentX.getNext() != null) {
                addLine("X" + x + " -> X" + currentX.getNext().getDato() + ";", 1);
            }
            currentX = currentX.getNext();
        }
    }

    private void generateXAxis() {
        Nodo<Integer> currentX = matriz.getRaiz().getNext();

        int i = 2;
        while (currentX != null) {
            int x = currentX.getDato();
            addLine("X" + x + " [label = \"" + x + "\", width = 1.5, group = " + i + " ];", 1);
            currentX = currentX.getNext();
            i++;
        }
    }

    private String generateInitPointers() {
        StringBuilder punteros = new StringBuilder();
        if (matriz.getRaiz().getBelow() != null) {
            punteros.append("Mt -> Y")
                    .append(matriz.getRaiz().getBelow().getDato())
                    .append(";")
                    .append("\n");
        }

        if (matriz.getRaiz().getNext() != null) {
            punteros.append("    Mt -> X")
                    .append(matriz.getRaiz().getNext().getDato())
                    .append(";");
        }

        return punteros.toString();
    }

    private String generateRankX() {
        Nodo<Integer> currentX = matriz.getRaiz().getNext();
        StringBuilder texto = new StringBuilder();
        texto.append("{ rank = same; Mt; ");

        while (currentX != null) {
            int x = currentX.getDato();
            texto.append(" X").append(x).append("; ");
            currentX = currentX.getNext();
        }
        texto.append("}");

        return texto.toString();
    }

    private void genereteMatrixNodes() {
        StringBuilder nodos;
        MatrixNode currentNode;

        for (int i = 1; i <= matriz.getSizeYAxis(); i++) {
            int g = 2;
            for (int j = 1; j <= matriz.getSizeXAxis(); j++) {
                currentNode = matriz.getMatrixNode(j, i);
                if (currentNode != null) {
                    nodos = new StringBuilder();
                    String hex = Integer.toHexString(currentNode.getDato());
                    nodos.append(currentNode.getX())
                            .append(currentNode.getY())
                            .append(" [label = \"#")
                            .append(hex)
                            .append("\", width = 1.5, group = ")
                            .append(g)
                            .append("];");
                    addLine(nodos.toString(), 1);
                }
                if (matriz.getNodoX(j) != null) {
                    g++;
                }
            }
            addLine("", 0);
        }

    }

    private void generetePoinsters() {
        StringBuilder pointers;
        Nodo<Integer> nodoY = matriz.getRaiz().getBelow();
        MatrixNode currentNode;

        while (nodoY != null) {
            pointers = new StringBuilder();
            currentNode = (MatrixNode) nodoY.getNext();
            int y = nodoY.getDato();
            int x = currentNode.getX();
            pointers.append("Y").append(y).append(" -> ").append(x).append(y).append(";");

            while (currentNode != null) {
                int xCurrent = currentNode.getX();

                int xNext = 0;
                if (currentNode.getNext() != null) {
                    xNext = ((MatrixNode) currentNode.getNext()).getX();
                }

                int yBelow = 0;
                if (currentNode.getBelow() != null) {
                    yBelow = ((MatrixNode) currentNode.getBelow()).getY();
                }
                int xPrev;
                int yAbove;

                if (currentNode.getPrev() instanceof MatrixNode n) {
                    xPrev = n.getX();
                    pointers.append("\n    ").append(xCurrent).append(y).append(" -> ").append(xPrev).append(y);
                } else {
                    pointers.append("\n    ").append(xCurrent).append(y).append(" -> ").append("Y").append(y);
                }

                if (currentNode.getAbove() instanceof MatrixNode n) {
                    yAbove = n.getY();
                    pointers.append("\n    ").append(xCurrent).append(y).append(" -> ").append(xCurrent).append(yAbove);
                } else {
                    pointers.append("\n    ").append("X").append(xCurrent).append(" -> ").append(xCurrent).append(y).append(";");
                    pointers.append("\n    ").append(xCurrent).append(y).append(" -> ").append("X").append(xCurrent);
                }

                if (xNext != 0) {
                    pointers.append("\n    ").append(xCurrent).append(y).append(" -> ").append(xNext).append(y);
                }
                if (yBelow != 0) {
                    pointers.append("\n    ").append(xCurrent).append(y).append(" -> ").append(xCurrent).append(yBelow);
                }

                currentNode = (MatrixNode) currentNode.getNext();
            }
            pointers.append("\n");
            addLine(pointers.toString(), 1);
            nodoY = nodoY.getBelow();
        }
    }

    private void generateRanks() {
        Nodo<Integer> nodoY = matriz.getRaiz().getBelow();
        MatrixNode currentNode;
        StringBuilder texto;

        while (nodoY != null) {
            texto = new StringBuilder();
            currentNode = (MatrixNode) nodoY.getNext();
            texto.append("{ rank = same; Y").append(nodoY.getDato()).append("; ");
            while (currentNode != null) {
                int x = currentNode.getX();
                int y = currentNode.getY();
                texto.append(x).append(y).append("; ");
                currentNode = (MatrixNode) currentNode.getNext();
            }
            texto.append("}");
            addLine(texto.toString(), 1);
            nodoY = nodoY.getBelow();
        }
    }
}
