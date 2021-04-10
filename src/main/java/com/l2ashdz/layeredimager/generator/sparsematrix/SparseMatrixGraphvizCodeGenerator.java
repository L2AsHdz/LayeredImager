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
        generateXAxis();
        addLine("Mt -> U0;", 1);
        addLine("Mt -> A0;", 1);
        addLine("{ rank = same; Mt; A1; }", 1);
        generateNodos();
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
        Nodo<Integer> actual = matriz.getPrincipalNode().getBelow();

        while (actual != null) {
            int y = actual.getDato();
            addLine("Y" + y + " [label = \"" + y + "\", width = 1.5, group = 1 ];", 1);
            actual = actual.getBelow();
        }
    }

    private void generateXAxis() {
        Nodo<Integer> actual = matriz.getPrincipalNode().getNext();

        int i = 2;
        while (actual != null) {
            int x = actual.getDato();
            addLine("X" + x + " [label = \"" + x + "\", width = 1.5, group = " + i + " ];", 1);
            actual = actual.getNext();
            i++;
        }
    }

    private void generateNodos() {
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
    }

}
