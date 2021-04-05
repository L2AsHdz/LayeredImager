package com.l2ashdz.layeredimager.model;

/**
 *
 * @date 4/04/2021
 * @time 21:25:10
 * @author asael
 */
public class Capa extends Objeto {

    private String nombre;
    //private MatrizCapa

    public Capa() {
    }

    public Capa(String nombre, int id) {
        super(id);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
