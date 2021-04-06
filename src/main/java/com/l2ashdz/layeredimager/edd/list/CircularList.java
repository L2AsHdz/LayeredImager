package com.l2ashdz.layeredimager.edd.list;

import com.l2ashdz.layeredimager.edd.Nodo;
import com.l2ashdz.layeredimager.model.Imagen;

/**
 *
 * @date 5/04/2021
 * @time 11:38:47
 * @author asael
 */
public class CircularList {

    private Nodo<Imagen> primero;
    private Nodo<Imagen> ultimo;
    private int size;

    public CircularList() {
        this.size = 0;
    }

    public void add(Imagen i) {
        Nodo<Imagen> nuevo = new Nodo(i);

        if (primero == null) {
            nuevo.setNext(nuevo);
            nuevo.setPrev(nuevo);
            primero = nuevo;
        } else {
            ultimo.setNext(nuevo);
            nuevo.setPrev(ultimo);

            primero.setPrev(nuevo);
            nuevo.setNext(primero);
        }
        size++;
        ultimo = nuevo;
    }

    public Imagen remove(int id) {
        Nodo<Imagen> actual = primero;

        if (actual.getDato().getId() == id) {
            primero = actual.getNext();
            primero.setPrev(ultimo);
            ultimo.setNext(primero);
            size--;

            if (actual == actual.getNext()) primero = null;

            return actual.getDato();
        } else {
            do {
                if (actual.getDato().getId() == id) {
                    if (actual == ultimo) {
                        ultimo = actual.getPrev();
                        primero.setPrev(ultimo);
                        ultimo.setNext(primero);
                    } else {
                        actual.getPrev().setNext(actual.getNext());
                        actual.getNext().setPrev(actual.getPrev());
                    }

                    size--;
                    return actual.getDato();
                }
                actual = actual.getNext();
            } while (actual != primero);
        }
        return null;
    }

    public void show() {
        Nodo<Imagen> actual = primero;

        if (actual == null) {
            System.out.println("Lista vacia");
        } else {
            do {
                System.out.println(actual.getPrev().getDato().getId() + "<-" + actual.getDato().getId() + "->" + actual.getNext().getDato().getId());
                actual = actual.getNext();
            } while (actual != primero);

        }
    }
}
