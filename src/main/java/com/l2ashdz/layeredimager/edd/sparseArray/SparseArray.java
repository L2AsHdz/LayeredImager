package com.l2ashdz.layeredimager.edd.sparseArray;

import com.l2ashdz.layeredimager.edd.Nodo;
import com.l2ashdz.layeredimager.model.Capa;

/**
 *
 * @date 9/04/2021
 * @time 10:56:15
 * @author asael
 */
public class SparseArray {
    
    private final Nodo principalNode = new Nodo();

    public SparseArray() {
    }
    
    public void add(Capa d, int x, int y) {
        
        if (principalNode.getNext() == null & principalNode.getBelow() == null) {
            Nodo<Capa> nuevo = new Nodo(d);
            Nodo xAxis = new Nodo(x);
            Nodo yAxis = new Nodo(y);
            
            nuevo.setAbove(xAxis);
            nuevo.setPrev(yAxis);
            
            xAxis.setBelow(nuevo);
            yAxis.setNext(nuevo);
            
            principalNode.setNext(xAxis);
            principalNode.setBelow(yAxis);
        }
    }
}
