package com.l2ashdz.layeredimager.edd;

/**
 *
 * @param <T>
 * @date 4/04/2021
 * @time 13:25:00
 * @author asael
 */
public class Nodo<T> {

    private T dato;
    private Nodo next;
    private Nodo prev;
    private Nodo above;
    private Nodo below;

    public Nodo() {
    }

    public Nodo(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public Nodo getPrev() {
        return prev;
    }

    public void setPrev(Nodo prev) {
        this.prev = prev;
    }

    public Nodo getAbove() {
        return above;
    }

    public void setAbove(Nodo above) {
        this.above = above;
    }

    public Nodo getBelow() {
        return below;
    }

    public void setBelow(Nodo below) {
        this.below = below;
    }
}
