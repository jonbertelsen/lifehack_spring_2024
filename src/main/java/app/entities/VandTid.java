package app.entities;

import java.util.Date;

/**
 * @author Daniel Rouvillain
 */

public class VandTid {

    private int vandtidId;

    private Date dato;

    private int planteId;

    public VandTid(int vandtidId, Date dato, int planteId) {
        this.vandtidId = vandtidId;
        this.dato = dato;
        this.planteId = planteId;
    }

    public int getVandtidId() {
        return vandtidId;
    }

    public Date getDato() {
        return dato;
    }

    public int getPlanteId() {
        return planteId;
    }
}
