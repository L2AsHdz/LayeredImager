package com.l2ashdz.layeredimager.edd.list;

import com.l2ashdz.layeredimager.edd.Nodo;

/**
 *
 * @param <T>
 * @date 4/04/2021
 * @time 13:27:55
 * @author asael
 */
public abstract class List<T> {
    
    protected Nodo<T> inicio;
    protected int size = 0;
    
    public void agregar(T t) {
        Nodo<T> nuevo = new Nodo(t);
        
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo<T> temp = inicio;
            
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
    
    public abstract T obtener(int id);
    
    public abstract T eliminar(int id);
    
    public abstract void mostrar();
}
