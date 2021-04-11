package layeredimager.model;

/**
 *
 * @date 5/04/2021
 * @time 10:45:51
 * @author asael
 */
public abstract class Objeto {

    private int id;

    public Objeto(int id) {
        this.id = id;
    }

    public Objeto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
