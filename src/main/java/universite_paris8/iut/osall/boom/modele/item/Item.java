package universite_paris8.iut.osall.boom.modele.item;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;

public class Item {
    private String nom;
    private IntegerProperty x;
    private IntegerProperty y;
    private Environnement environnement;
    private String id;
    private static int compteur;

    public Item(Environnement environnement, String nom, int x, int y) {
        this.environnement = environnement;
        this.nom = nom;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.id = "I" + compteur ;
        compteur++;
    }

/* *********************************************************************************************************************
                                          GETTER & SETTER & BOOLEAN
********************************************************************************************************************* */
    public int getX() {
        return x.getValue();
    }

    public IntegerProperty getXProperty() {
        return x;
    }

    public int getY() {
        return y.getValue();
    }

    public IntegerProperty getYProperty() {
        return y;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    /* *********************************************************************************************************************

********************************************************************************************************************* */
    @Override
    public String toString() {
        return "Item{" +
                "nom='" + nom + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", id='" + id + '\'' +
                '}';
    }
}
