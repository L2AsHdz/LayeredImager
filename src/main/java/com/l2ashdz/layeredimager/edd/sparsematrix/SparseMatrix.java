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
        Nodo<Integer> posX;
        Nodo<Integer> posY;

        if (raiz.getNext() == null & raiz.getBelow() == null) {
            posX = new Nodo(x);
            posY = new Nodo(y);
            nuevo.setAbove(posX);
            nuevo.setPrev(posY);

            posX.setBelow(nuevo);
            posY.setNext(nuevo);

            raiz.setNext(posX);
            raiz.setBelow(posY);
        } else {
            addCordX(x);
            addCordY(y);
//            addNodoToXAxis(nuevo);
//            addNodoToYAxis(nuevo);
        }
    }

    private void addCordX(int x) {
        Nodo<Integer> tempX = getNodoX(x);
        Nodo<Integer> posX;

        //Si no existe, recorre buscando la posicion donde agregarse
        if (tempX == null) {
            tempX = raiz.getNext();

            while (tempX != null) {
                //Si tempX es mayor agrega cordX antes de tempX
                if (tempX.getDato() > x) {
                    posX = new Nodo(x);

                    posX.setPrev(tempX.getPrev());
                    posX.setNext(tempX);

                    if (tempX.getPrev() != null) {
                        tempX.getPrev().setNext(posX);
                    } else {
                        raiz.setNext(posX);
                    }

                    tempX.setPrev(posX);
                    break;
                }

                //Si no existe siguiente agrega cordX de ultimo
                if (tempX.getNext() == null) {
                    posX = new Nodo(x);
                    posX.setPrev(tempX);
                    tempX.setNext(posX);
                    break;
                }

                tempX = tempX.getNext();
            }
        }
    }

    private void addCordY(int y) {
        Nodo<Integer> tempY = getNodoY(y);
        Nodo<Integer> posY;

        if (tempY == null) {
            tempY = raiz.getBelow();

            while (tempY != null) {
                if (tempY.getDato() > y) {
                    posY = new Nodo(y);

                    posY.setAbove(tempY.getAbove());
                    posY.setBelow(tempY);

                    if (tempY.getAbove() != null) {
                        tempY.getAbove().setBelow(posY);
                    } else {
                        raiz.setBelow(posY);
                    }

                    tempY.setAbove(posY);
                    break;
                }

                if (tempY.getBelow() == null) {
                    posY = new Nodo(y);
                    posY.setAbove(tempY);
                    tempY.setBelow(posY);
                    break;
                }

                tempY = tempY.getBelow();
            }
        }
    }

    private void addNodoToXAxis(MatrixNode nuevo) {
        Nodo<Integer> tempX = getNodoX(nuevo.getX());
        
        if (tempX != null) {
            
        }
    }

    private Nodo<Integer> getNodoX(int x) {
        Nodo<Integer> tempX = raiz.getNext();

        while (tempX != null) {
            if (tempX.getDato() == x) {
                return tempX;
            }
            tempX = tempX.getNext();
        }
        return null;
    }

    private Nodo<Integer> getNodoY(int y) {
        Nodo<Integer> tempY = raiz.getBelow();

        while (tempY != null) {
            if (tempY.getDato() == y) {
                return tempY;
            }
            tempY = tempY.getBelow();
        }
        return null;
    }
}
