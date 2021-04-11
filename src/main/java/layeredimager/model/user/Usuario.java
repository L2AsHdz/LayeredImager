package layeredimager.model.user;

import layeredimager.model.Objeto;

/**
 *
 * @date 4/04/2021
 * @time 21:23:40
 * @author asael
 */
public class Usuario extends Objeto {

    private String nombre;
    //private ListaImagenes

    public Usuario() {
    }

    public Usuario(int id, String nombre) {
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
