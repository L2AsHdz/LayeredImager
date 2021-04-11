/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.l2ashdz.layeredimager.model.user;

import java.util.List;

/**
 *
 * @date 11/04/2021
 * @time 11:47:07
 * @author asael
 */
public class InfoUser {

    private String name;
    List<Integer> idImages;

    public InfoUser() {
    }

    public InfoUser(String name, List<Integer> idImages) {
        this.name = name;
        this.idImages = idImages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIdImages() {
        return idImages;
    }

    public void setIdImages(List<Integer> idImages) {
        this.idImages = idImages;
    }
}
