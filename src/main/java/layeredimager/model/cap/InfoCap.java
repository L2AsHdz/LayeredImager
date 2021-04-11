package layeredimager.model.cap;

import java.util.List;

/**
 *
 * @date 11/04/2021
 * @time 15:14:27
 * @author asael
 */
public class InfoCap {

    private int id;
    List<InfoNodo> infoNodos;

    public InfoCap() {
    }

    public InfoCap(int id, List<InfoNodo> infoNodos) {
        this.id = id;
        this.infoNodos = infoNodos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<InfoNodo> getInfoNodos() {
        return infoNodos;
    }

    public void setInfoNodos(List<InfoNodo> infoNodos) {
        this.infoNodos = infoNodos;
    }
}
