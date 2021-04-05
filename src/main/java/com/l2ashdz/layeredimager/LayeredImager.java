package com.l2ashdz.layeredimager;

import com.l2ashdz.layeredimager.edd.list.List;
import com.l2ashdz.layeredimager.edd.list.UserList;
import com.l2ashdz.layeredimager.model.Usuario;

/**
 *
 * @date 4/04/2021
 * @time 20:35:12
 * @author asael
 */
public class LayeredImager {

    public static void main(String[] args) {
        Usuario user1 = new Usuario(1, "nombre1");
        Usuario user2 = new Usuario(2, "nombre2");
        Usuario user3 = new Usuario(3, "nombre3");
        Usuario user4 = new Usuario(4, "nombre4");
        Usuario user5 = new Usuario(5, "nombre5");
        
        List<Usuario> userList = new UserList();
        
        userList.agregar(user1);
        userList.agregar(user2);
        userList.agregar(user3);
        userList.agregar(user4);
        userList.agregar(user5);
        
        userList.mostrar();
        
        Usuario userObtenido = userList.obtener(5);
        
        System.out.println("\n" + userObtenido.getNombre());
        
        userList.eliminar(5);
        userList.eliminar(4);
        System.out.println();
        userList.mostrar();
    }
}
