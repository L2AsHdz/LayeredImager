package com.l2ashdz.layeredimager;

import com.l2ashdz.layeredimager.edd.list.CircularList;
import com.l2ashdz.layeredimager.edd.list.List;
import com.l2ashdz.layeredimager.model.Capa;
import com.l2ashdz.layeredimager.model.Imagen;
import com.l2ashdz.layeredimager.model.Usuario;

/**
 *
 * @date 4/04/2021
 * @time 20:35:12
 * @author asael
 */
public class LayeredImager {

    public static void main(String[] args) {
        pruebaListaIamgen();
    }
    
    private static void pruebaListaUsuario() {
        Usuario user1 = new Usuario(1, "nombre1");
        Usuario user2 = new Usuario(2, "nombre2");
        Usuario user3 = new Usuario(3, "nombre3");
        Usuario user4 = new Usuario(4, "nombre4");
        Usuario user5 = new Usuario(5, "nombre5");
        
        List userList = new List();
        
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        
        userList.show();
    }
    
    private static void pruebaListaCapas() {
        Capa cap1 = new Capa("capa1", 1);
        Capa cap2 = new Capa("capa2", 2);
        Capa cap3 = new Capa("capa3", 3);
        
        List capList = new List();
        
        capList.add(cap1);
        capList.add(cap2);
        capList.add(cap3);
        System.out.println("\n");
        capList.remove(2);
        capList.remove(3);
        capList.remove(1);
        capList.show();
    }
    
    private static void pruebaListaIamgen() {
        Imagen img1 = new Imagen("img1", 1);
        Imagen img2 = new Imagen("img1", 2);
        Imagen img3 = new Imagen("img1", 3);
        Imagen img4 = new Imagen("img1", 4);
        
        CircularList imgList = new CircularList();
        
        imgList.add(img1);
        imgList.add(img2);
        imgList.add(img3);
        imgList.add(img4);
        
        imgList.show();
        
        System.out.println("Elimando datos");
        imgList.remove(2);
        imgList.remove(1);
        imgList.remove(4);
        imgList.remove(3);
        imgList.show();
    }
}
