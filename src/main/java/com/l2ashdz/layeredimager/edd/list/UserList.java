package com.l2ashdz.layeredimager.edd.list;

import com.l2ashdz.layeredimager.edd.Nodo;
import com.l2ashdz.layeredimager.model.Usuario;

/**
 *
 * @date 5/04/2021
 * @time 01:20:10
 * @author asael
 */
public class UserList extends List<Usuario> {

    @Override
    public Usuario obtener(int id) {
        Nodo<Usuario> actual = inicio;
        
        while (actual != null) {
            if (actual.getDato().getId() == id) {
                return actual.getDato();
            }
            actual = actual.getNext();
        }
        
        return null;
    }

    @Override
    public Usuario eliminar(int id) {
        Nodo<Usuario> actual = inicio;
        
        if (actual.getDato().getId() == id) {
            inicio = actual.getNext();
            size--;
            return actual.getDato();
        } else {
            Nodo<Usuario> temp;
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

    @Override
    public void mostrar() {
        Nodo<Usuario> actual = inicio;
        
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
