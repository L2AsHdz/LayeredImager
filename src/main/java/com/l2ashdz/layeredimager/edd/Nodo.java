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
    private Nodo<T> next;
    private Nodo<T> prev;
    private Nodo<T> above;
    private Nodo<T> below;

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

    public Nodo<T> getNext() {
        return next;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }

    public Nodo<T> getPrev() {
        return prev;
    }

    public void setPrev(Nodo<T> prev) {
        this.prev = prev;
    }

    public Nodo<T> getAbove() {
        return above;
    }

    public void setAbove(Nodo<T> above) {
        this.above = above;
    }

    public Nodo<T> getBelow() {
        return below;
    }

    public void setBelow(Nodo<T> below) {
        this.below = below;
    }
}
