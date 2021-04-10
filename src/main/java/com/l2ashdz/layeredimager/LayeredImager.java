package com.l2ashdz.layeredimager;

import com.l2ashdz.layeredimager.edd.list.CircularList;
import com.l2ashdz.layeredimager.edd.list.List;
import com.l2ashdz.layeredimager.edd.sparsematrix.SparseMatrix;
import com.l2ashdz.layeredimager.edd.tree.ArbolAVL;
import com.l2ashdz.layeredimager.generator.sparsematrix.SparseMatrixGraphvizCodeGenerator;
import com.l2ashdz.layeredimager.model.Capa;
import com.l2ashdz.layeredimager.model.Imagen;
import com.l2ashdz.layeredimager.model.Usuario;
import static com.l2ashdz.layeredimager.controller.FileController.saveFile;

/**
 *
 * @date 4/04/2021
 * @time 20:35:12
 * @author asael
 */
public class LayeredImager {

    public static void main(String[] args) {
        //pruebaListaIamgen();
        //pruebaArbolAVL();
        pruebaMatriz();
    }
    
    public static void pruebaMatriz() {
        SparseMatrix matriz = new SparseMatrix();
        
        matriz.add(1, 3, 3);
        matriz.add(2, 5, 5);
        matriz.add(3, 1, 1);
        matriz.add(9, 6, 1);
        matriz.add(4, 4, 4);
        matriz.add(5, 2, 7);
        matriz.add(6, 2, 9);
        matriz.add(7, 10, 10);
        matriz.add(8, 1, 7);
        var generator = new SparseMatrixGraphvizCodeGenerator(matriz); 
        
        saveFile("matriz.dot", generator.generate());
    }

    private static void pruebaArbolAVL() {
        ArbolAVL arbol = new ArbolAVL();

        Usuario user1 = new Usuario(10, "nombre1");
        Usuario user2 = new Usuario(5, "nombre2");
        Usuario user3 = new Usuario(13, "nombre3");
        Usuario user4 = new Usuario(1, "nombre4");
        Usuario user5 = new Usuario(6, "nombre5");
        Usuario user6 = new Usuario(17, "nombre5");
        Usuario user7 = new Usuario(16, "nombre5");
        Usuario user8 = new Usuario(0, "nombre5");
        Usuario user9 = new Usuario(-1, "menos1");
        Usuario user10 = new Usuario(-5, "nombre544");

        arbol.add(user1);
        arbol.add(user2);
        arbol.add(user3);
        arbol.add(user4);
        arbol.add(user5);
        arbol.add(user6);
        arbol.add(user7);
        arbol.add(user8);
        arbol.add(user9);
        arbol.add(user10);

        arbol.inOrden(arbol.getRaiz());

        Usuario userR = (Usuario) arbol.get(-1);
        
        arbol.remove(0);
        
        System.out.println();
        arbol.inOrden(arbol.getRaiz());

        System.out.println("\n" + userR.getNombre());
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
        Imagen img2 = new Imagen("img2", 2);
        Imagen img3 = new Imagen("img3", 3);
        Imagen img4 = new Imagen("img4", 4);

        CircularList imgList = new CircularList();

        imgList.add(img1);
        imgList.add(img2);
        imgList.add(img3);
        imgList.add(img4);

        imgList.show();

        System.out.println("Elimando datos");
        imgList.remove(2);
        imgList.remove(1);
        imgList.show();

        Imagen img = imgList.get(4);

        System.out.println("Objeto obtenido");
        System.out.println(img.getNombre());
    }
}
