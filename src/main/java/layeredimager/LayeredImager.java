package layeredimager;

import layeredimager.controller.principalui.PrincipalUIController;
import layeredimager.view.PrincipalView;

/**
 *
 * @date 4/04/2021
 * @time 20:35:12
 * @author asael
 */
public class LayeredImager {

    public static void main(String[] args) {
        PrincipalView view = new PrincipalView();
        PrincipalUIController controller = new PrincipalUIController(view);
        controller.iniciar();
    }
    
    /*public static void pruebaLecturaUser() {
        String text = """
                      userM:;
                      userB:;
                      userA:13,14;
                      userY:8,9,10;
                      """;
        
        StringReader reader = new StringReader(text);
        
        UserLexer lexer;
        UserParser parser = null;
        
        try {
            lexer = new UserLexer(reader);
            parser = new UserParser(lexer);
            parser.parse();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        List<InfoUser> infoUsers = parser.getInfoUsers();
        
        infoUsers.forEach(u -> {
            System.out.println("User: " + u.getName());
            System.out.print("Images: ");
            u.getIdImages().forEach(i -> {
                System.out.print(i + ",");
            });
            System.out.println("\n");
        });
    }
    
    public static void pruebaLecturaImage() {
        String text = """
                      5{1,5,9,4}
                      1{6}
                      11{}
                      3{1,2}
                      3{1,2,6,7}
                      3{1,2,7,4,66,51,2}
                      """;
        
        StringReader reader = new StringReader(text);
        
        ImageLexer lexer;
        ImageParser parser = null;
        
        try {
            lexer = new ImageLexer(reader);
            parser = new ImageParser(lexer);
            parser.parse();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        List<PreImagen> preImagenes = parser.getPreImagenes();
        
        preImagenes.forEach(pi ->{
            System.out.println("Imagen: " + pi.getId());
            System.out.print("Capas: ");
            pi.getCapas().forEach(c -> {
                System.out.print(c + ",");
            });
            System.out.println("\n");
        });
    }
    
    public static void pruebaLecturaCap(){
        String text = """
                      1{
                        4,9,#3498DB;
                        1,1,#28B463;
                        2,4,#F4D03F;
                      }
                      
                      2{
                        2,9,#3498DB;
                        1,1,#28B463;
                      }
                      
                      3{
                        4,7,#3498DB;
                        1,10,#28B463;
                        2,6,#F4D03F;
                      }
                      
                      6{
                        4,7,#3498DB;
                        1,10,#28B463;
                        2,6,#F4D03F;
                        4,6,#F4D03F;
                        6,1,#F4D03F;
                        1,6,#F4D03F;
                        3,6,#F4D03F;
                      }
                      """;
        StringReader reader = new StringReader(text);
        
        CapLexer lexer;
        CapParser parser = null;
        
        try {
            lexer = new CapLexer(reader);
            parser = new CapParser(lexer);
            parser.parse();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        /*ArbolAVL arbol = parser.getCapas();
        arbol.inOrden(arbol.getRaiz());
        System.out.println();
        Capa cap = (Capa) arbol.get(1);
        System.out.println(cap.getName() + "\n");
        
    }

    public static void pruebaMatriz() {
        SparseMatrix matriz = new SparseMatrix();

        matriz.add(Integer.parseInt("e74c3c", 16), 3, 3);
        matriz.add(2, 5, 5);
        matriz.add(3, 1, 1);
        matriz.add(9, 6, 1);
        matriz.add(4, 4, 4);
        matriz.add(5, 2, 7);
        matriz.add(6, 2, 9);
        matriz.add(7, 10, 10);
        matriz.add(8, 1, 7);
        matriz.add(8, 2, 10);
        
        matriz.add(8, 4, 1);
        matriz.add(8, 11, 1);
        matriz.add(8, 2, 5);
        matriz.add(8, 7, 5);
        matriz.add(8, 11, 5);
        matriz.add(8, 2, 6);
        matriz.add(8, 4, 6);
        matriz.add(8, 7, 15);
        matriz.add(8, 11, 15);
        
        
        matriz.add(7, 1, 1);
        matriz.add(7, 1, 2);
        matriz.add(7, 1, 3);
        matriz.add(7, 1, 4);
        matriz.add(7, 2, 1);
        matriz.add(7, 2, 3);
        matriz.add(7, 2, 5);
        matriz.add(7, 7, 1);
        matriz.add(7, 7, 2);
        matriz.add(231, 1, 5);
        
        var generator = new SparseMatrixGraphvizCodeGenerator(matriz);

        saveFile("matriz.dot", generator.generate());
    }

    private static void pruebaArbolAVL() {
        UserArbolAVL arbol = new UserArbolAVL();

        Usuario user3 = new Usuario("nombre3", null);
        Usuario user1 = new Usuario("nombre1", null);
        Usuario user2 = new Usuario("nombre2", null);
        Usuario user8 = new Usuario("nombre41", null);
        Usuario user4 = new Usuario("nombre4", null);
        Usuario user5 = new Usuario("nombre5", null);
        Usuario user6 = new Usuario("nombre8", null);
        Usuario user7 = new Usuario("nombre14", null);
        Usuario user9 = new Usuario("menos11", null);
        Usuario user10 = new Usuario("nombre544", null);

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

        Usuario userR = (Usuario) arbol.get("nombre1");

        arbol.remove("menos11");

        System.out.println();
        arbol.inOrden(arbol.getRaiz());

        System.out.println("\n" + userR.getNombre());
    }

    private static void pruebaListaCapas() {
        Capa cap1 = new Capa();
        Capa cap2 = new Capa();
        Capa cap3 = new Capa();

        Lista capList = new Lista();

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
        Imagen img1 = new Imagen(4, null);
        Imagen img2 = new Imagen(1, null);
        Imagen img3 = new Imagen(2, null);
        Imagen img4 = new Imagen(3, null);

        CircularList imgList = new CircularList();

        imgList.add(img1);
        imgList.add(img2);
        imgList.add(img3);
        imgList.add(img4);

        imgList.show();
        imgList.sort();
        imgList.show();

        System.out.println("Elimando datos");
        imgList.remove(2);
        imgList.remove(1);
        imgList.show();

        Imagen img = imgList.get(4);

        System.out.println("Objeto obtenido");
        System.out.println(img.getNombre());
    }*/
}
