package com.l2ashdz.layeredimager.edd.sparsematrix;

import com.l2ashdz.layeredimager.edd.MatrixNode;
import com.l2ashdz.layeredimager.edd.Nodo;

/**
 *
 * @date 9/04/2021
 * @time 10:56:15
 * @author asael
 */
public class SparseMatrix {

    private final Nodo<Integer> raiz = new Nodo<Integer>();

    public SparseMatrix() {
    }

    public Nodo<Integer> getRaiz() {
        return raiz;
    }

    public void add(int dato, int x, int y) {
        MatrixNode nuevo = new MatrixNode(x, y, dato);
        Nodo<Integer> cordX;
        Nodo<Integer> cordY;

        if (raiz.getNext() == null & raiz.getBelow() == null) {
            cordX = new Nodo(x);
            cordY = new Nodo(y);
            nuevo.setAbove(cordX);
            nuevo.setPrev(cordY);

            cordX.setBelow(nuevo);
            cordY.setNext(nuevo);

            raiz.setNext(cordX);
            raiz.setBelow(cordY);
            System.out.println("Se agregaron las coordenadas al inicio");
        } else {

            Nodo<Integer> tempX = raiz.getNext();
            Nodo<Integer> tempY = raiz.getBelow();

            //Recorre las x buscando si ya existe la coordenada x
            while (tempX != null) {
                if (tempX.getDato() == x) {
                    break;
                }
                tempX = tempX.getNext();
            }

            //Si no existe, recorre buscando la posicion donde agregarse
            if (tempX == null) {
                tempX = raiz.getNext();
                
                while (tempX != null) {
                    //Si tempX es mayor agrega cordX antes de tempX
                    if (tempX.getDato() > x) {
                        cordX = new Nodo(x);
                        
                        cordX.setPrev(tempX.getPrev());
                        cordX.setNext(tempX);
                        
                        if (tempX.getPrev() != null) {
                            tempX.getPrev().setNext(cordX);
                        } else {
                            raiz.setNext(cordX);
                        }
                        
                        tempX.setPrev(cordX);
                        break;
                    }
                    
                    //Si no existe siguiente agrega cordX de ultimo
                    if (tempX.getNext() == null) {
                        cordX = new Nodo(x);
                        cordX.setPrev(tempX);
                        tempX.setNext(cordX);
                        break;
                    }
                    
                    tempX = tempX.getNext();
                }
            }

            while (tempY != null) {
                if (tempY.getDato() == y) {
                    break;
                }
                tempY = tempY.getBelow();
            }
            
            if (tempY == null) {
                tempY = raiz.getBelow();
                
                while (tempY != null) {
                    if (tempY.getDato() > y) {
                        cordY = new Nodo(y);
                        
                        cordY.setAbove(tempY.getAbove());
                        cordY.setBelow(tempY);
                        
                        if (tempY.getAbove() != null) {
                            tempY.getAbove().setBelow(cordY);
                        } else {
                            raiz.setBelow(cordY);
                        }
                        
                        tempY.setAbove(cordY);
                        break;
                    }
                    
                    if (tempY.getBelow() == null) {
                        cordY = new Nodo(y);
                        cordY.setAbove(tempY);
                        tempY.setBelow(cordY);
                        break;
                    }
                    
                    tempY = tempY.getBelow();
                }
            }
        }
    }
}
