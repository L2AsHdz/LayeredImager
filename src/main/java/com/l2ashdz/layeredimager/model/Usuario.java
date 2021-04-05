package com.l2ashdz.layeredimager.model;

/**
 *
 * @date 4/04/2021
 * @time 21:23:40
 * @author asael
 */
public class Usuario {

    private int id;
    private String nombre;
    //private ListaImagenes

    public Usuario() {
    }

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
