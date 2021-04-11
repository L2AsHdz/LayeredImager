package com.l2ashdz.layeredimager.model.image;

import java.util.List;

/**
 *
 * @date 11/04/2021
 * @time 03:39:14
 * @author asael
 */
public class PreImagen {

    private int id;
    private List<Integer> capas;

    public PreImagen() {
    }

    public PreImagen(int id, List<Integer> capas) {
        this.id = id;
        this.capas = capas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getCapas() {
        return capas;
    }

    public void setCapas(List<Integer> capas) {
        this.capas = capas;
    }
}
