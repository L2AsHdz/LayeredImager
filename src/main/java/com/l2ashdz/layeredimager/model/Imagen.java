package com.l2ashdz.layeredimager.model;

import com.l2ashdz.layeredimager.edd.list.Lista;

/**
 *
 * @date 5/04/2021
 * @time 10:34:19
 * @author asael
 */
public class Imagen extends Objeto {

    private String nombre;
    Lista capas;

    public Imagen() {
    }

    public Imagen(int id) {
        super(id);
        this.nombre = "Imagen_" + String.valueOf(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista getCapas() {
        return capas;
    }

    public void setCapas(Lista capas) {
        this.capas = capas;
    }
}
