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
            addNodoToXAxis(nuevo);
            addNodoToYAxis(nuevo);
        }
    }

    private void addCordX(int x) {
        Nodo<Integer> tempX = getNodoX(x);
        Nodo<Integer> posX;

        if (tempX == null) {
            tempX = raiz.getNext();

            while (tempX != null) {
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
        Nodo<Integer> nodoX = getNodoX(nuevo.getX());
        MatrixNode nodeTemp;

        if (nodoX != null) {
            nodeTemp = (MatrixNode) nodoX.getBelow();

            if (nodeTemp == null) {
                nuevo.setAbove(nodoX);
                nodoX.setBelow(nuevo);
            } else {
                while (nodeTemp != null) {
                    if (nodeTemp.getY() == nuevo.getY()) {
                        nodeTemp.setDato(nuevo.getDato());
                        break;
                    }

                    if (nodeTemp.getY() > nuevo.getY()) {
                        nuevo.setAbove(nodeTemp.getAbove());
                        nuevo.setBelow(nodeTemp);

                        nodeTemp.getAbove().setBelow(nuevo);
                        nodeTemp.setAbove(nuevo);
                        break;
                    }

                    if (nodeTemp.getBelow() == null) {
                        nuevo.setAbove(nodeTemp);
                        nodeTemp.setBelow(nuevo);
                        break;
                    }
                    nodeTemp = (MatrixNode) nodeTemp.getBelow();
                }
            }
        }
    }

    private void addNodoToYAxis(MatrixNode nuevo) {
        Nodo<Integer> nodoY = getNodoY(nuevo.getY());
        MatrixNode nodeTemp;

        if (nodoY != null) {
            nodeTemp = (MatrixNode) nodoY.getNext();
            
            if (nodeTemp == null) {
                nuevo.setPrev(nodoY);
                nodoY.setNext(nuevo);
            } else {
                while (nodeTemp != null) {
                    if (nodeTemp.getX() == nuevo.getX()) {
                        nodeTemp.setDato(nuevo.getDato());
                        break;
                    }
                    
                    if (nodeTemp.getX() > nuevo.getX()) {
                        nuevo.setPrev(nodeTemp.getPrev());
                        nuevo.setNext(nodeTemp);
                        
                        nodeTemp.getPrev().setNext(nuevo);
                        nodeTemp.setPrev(nuevo);
                        break;
                    }
                    
                    if (nodeTemp.getNext() == null) {
                        nuevo.setPrev(nodeTemp);
                        nodeTemp.setNext(nuevo);
                        break;
                    }
                    nodeTemp = (MatrixNode) nodeTemp.getNext();
                }
            }
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
    
    public int getSizeXAxis() {
        Nodo<Integer> tempX = raiz.getNext();
        int size = 0;
        while (tempX != null) {
            size = tempX.getDato();
            tempX = tempX.getNext();
        }
        
        return size;
    }
    
    public int getSizeYAxis() {
        Nodo<Integer> tempY = raiz.getBelow();
        int size = 0;
        while (tempY != null) {
            size++;
            tempY = tempY.getBelow();
        }
        
        return size;
    }
}
