package com.l2ashdz.layeredimager.edd;

/**
 *
 * @date 9/04/2021
 * @time 18:42:30
 * @author asael
 */
public class MatrixNode extends Nodo<Integer>{

    private int x;
    private int y;

    public MatrixNode() {
    }

    public MatrixNode(Integer dato) {
        super(dato);
    }

    public MatrixNode(int x, int y, Integer dato) {
        super(dato);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
