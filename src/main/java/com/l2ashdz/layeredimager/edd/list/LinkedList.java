package com.l2ashdz.layeredimager.edd.list;

import com.l2ashdz.layeredimager.edd.Nodo;

/**
 *
 * @param <T>
 * @date 4/04/2021
 * @time 13:27:55
 * @author asael
 */
public class LinkedList<T> {
    
    private Nodo<T> inicio;
    
    public void agregar(T t) {
        Nodo<T> nuevo = new Nodo(t);
        
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo temp = inicio;
            
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            
            temp.setNext(nuevo);
        }
        System.out.println("Se agrego el elemento a la lista");
    }
    
    
    
    /*public void mostrarLista() {
        Nodo<Integer> actual = inicio;
        
        if (actual == null) {
            System.out.println("La lista esta vacia");
        } else {
            while (actual != null) {
                if (actual.getNext() != null) {
                    System.out.print(actual.getDato() + "->");
                } else {
                    System.out.print(actual.getDato());
                }
                actual = actual.getNext();
            }
        }
    }*/
}
