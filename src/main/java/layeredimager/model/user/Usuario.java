package layeredimager.model.user;

import layeredimager.edd.list.Lista;

/**
 *
 * @date 4/04/2021
 * @time 21:23:40
 * @author asael
 */
public class Usuario {

    private String nombre;
    private Lista imagenes;

    public Usuario() {
    }

    public Usuario(String nombre, Lista imagenes) {
        this.nombre = nombre;
        this.imagenes = imagenes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista getImagenes() {
        return imagenes;
    }

    public void setImagenes(Lista imagenes) {
        this.imagenes = imagenes;
    }
}
