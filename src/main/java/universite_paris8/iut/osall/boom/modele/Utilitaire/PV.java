package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PV {
    private int pvMax;
    private IntegerProperty pv;

    public PV(int pvMax) {
        this.pvMax = pvMax;
        this.pv = new SimpleIntegerProperty(pvMax);
    }

    public int getPvMax() {
        return pvMax;
    }
    public int getPv() {
        return pv.get();
    }
    public void setPv(int pv) {
        this.pv.set(pv);
    }
    public IntegerProperty pvProperty() {
        return pv;
    }
    public void enleverPv(int degat) {
        if (this.pv.getValue() - degat >= 0){
            this.pv.setValue(this.pv.getValue() - degat);
        }
        else {
            this.pv.setValue(0);
        }
    }
    public void rajouterPv(int pv){
        if (this.getPv() + pv <= this.pvMax){
            this.pv.setValue(this.pv.getValue() + pv);
        }
    }
}
