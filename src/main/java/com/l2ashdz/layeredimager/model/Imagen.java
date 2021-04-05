package com.l2ashdz.layeredimager.model;

/**
 *
 * @date 5/04/2021
 * @time 10:34:19
 * @author asael
 */
public class Imagen extends Objeto {

    private String nombre;

    public Imagen() {
    }

    public Imagen(String nombre, int id) {
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
