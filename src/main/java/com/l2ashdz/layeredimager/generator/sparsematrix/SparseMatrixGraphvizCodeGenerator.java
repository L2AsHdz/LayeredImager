package com.l2ashdz.layeredimager.generator.sparsematrix;

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
        addLine(generateInitPunters(), 1);
        addLine(generateRankX(), 1);
        //generateNodos();
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

        if (currentY != null & currentY.getBelow() != null) {
            addLine("Y" + currentY.getDato() + " -> Y" + currentY.getBelow().getDato() + ";", 1);
            currentY = currentY.getBelow();
        }
        while (currentY != null) {
            int y = currentY.getDato();
            addLine("Y" + y + " -> Y" + currentY.getAbove().getDato() + ";", 1);
            if (currentY.getBelow() != null) addLine("Y" + y + " -> Y" + currentY.getBelow().getDato() + ";", 1);
            currentY = currentY.getBelow();
        }
    }
    
    private void generateXPointers() {
        Nodo<Integer> currentX = matriz.getRaiz().getNext();

        if (currentX != null & currentX.getNext() != null) {
            addLine("X" + currentX.getDato() + " -> X" + currentX.getNext().getDato() + ";", 1);
            currentX = currentX.getNext();
        }
        while (currentX != null) {
            int x = currentX.getDato();
            addLine("X" + x + " -> X" + currentX.getPrev().getDato() + ";", 1);
            if (currentX.getNext() != null) addLine("X" + x + " -> X" + currentX.getNext().getDato() + ";", 1);
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

    private String generateInitPunters() {
        StringBuilder punteros = new StringBuilder();
        punteros.append("Mt -> Y")
                .append(matriz.getRaiz().getBelow().getDato())
                .append(";")
                .append("\n");

        punteros.append("    Mt -> X")
                .append(matriz.getRaiz().getNext().getDato())
                .append(";");

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

    /*private void generateNodos() {
        Nodo<Integer> currentY = matriz.getPrincipalNode().getBelow();

        while (currentY != null) {
            int y = (int) currentY.getDato();
            
            Nodo<Integer> currentNodo = currentY.getNext();
            while (currentNodo != null) {
                String hex  = Integer.toHexString(currentNodo.getDato());
                int x = getXAxis(currentNodo);
                addLine("X"+x+"_Y"+y+" [label = \"#"+hex+"\", width = 1.5, group = "+(x+1)+" ];", 1);
                currentNodo = currentNodo.getNext();
            }
            
            currentY = currentY.getBelow();
        }
    }

    private int getXAxis(Nodo<Integer> currentNodo) {
        Nodo<Integer> xAxis = matriz.getPrincipalNode().getNext();
        
        while (xAxis != null) {
            Nodo<Integer> nodeX = xAxis.getBelow();
            
            if (nodeX == currentNodo) {
                return xAxis.getDato();
            } else {
                nodeX = nodeX.getBelow();
                
                while (nodeX != null) {
                    if (nodeX == currentNodo) {
                        return xAxis.getDato();
                    }
                    nodeX = nodeX.getBelow();
                }
            }
            
            xAxis = xAxis.getNext();
        }
        return -1;
    }*/
}
