package com.l2ashdz.layeredimager.edd.list;

import com.l2ashdz.layeredimager.edd.Nodo;
import com.l2ashdz.layeredimager.model.Objeto;

/**
 *
 * @date 4/04/2021
 * @time 13:27:55
 * @author asael
 */
public class List {
    
    protected Nodo<Objeto> inicio;
    protected int size;

    public List() {
        this.size = 0;
    }
    
    public void add(Objeto t) {
        Nodo<Objeto> nuevo = new Nodo(t);
        
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo<Objeto> temp = inicio;
            
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            
            temp.setNext(nuevo);
        }
        size++;
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return inicio == null;
    }
    
    public Objeto get(int id) {
        Nodo<Objeto> actual = inicio;
        
        while (actual != null) {
            if (actual.getDato().getId() == id) {
                return actual.getDato();
            }
            actual = actual.getNext();
        }
        
        return null;
    }
    
    public Objeto remove(int id) {
        Nodo<Objeto> actual = inicio;
        
        if (actual.getDato().getId() == id) {
            inicio = actual.getNext();
            size--;
            return actual.getDato();
        } else {
            Nodo<Objeto> temp;
            while (actual.getNext() != null) {
                if (actual.getNext().getDato().getId() == id) {
                    temp = actual.getNext();
                    actual.setNext(temp.getNext());
                    size--;
                    return temp.getDato();
                }
                actual = actual.getNext();
            }
        }
        
        return null;
    }
    
    public void show() {
        Nodo<Objeto> actual = inicio;
        
        if (actual == null) {
            System.out.println("Lista vacia");
        } else {
            while (actual != null) {
                if (actual.getNext() != null) {
                    System.out.print(actual.getDato().getId() + "->");
                } else {
                    System.out.print(actual.getDato().getId());
                }
                actual = actual.getNext();
            }
        }
    }
}
