package com.l2ashdz.layeredimager.edd;

/**
 *
 * @param <T>
 * @date 4/04/2021
 * @time 13:25:00
 * @author asael
 */
public class Node<T> {

    private T dato;
    private Node next;
    private Node prev;
    private Node above;
    private Node below;

    public Node() {
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getAbove() {
        return above;
    }

    public void setAbove(Node above) {
        this.above = above;
    }

    public Node getBelow() {
        return below;
    }

    public void setBelow(Node below) {
        this.below = below;
    }
}
