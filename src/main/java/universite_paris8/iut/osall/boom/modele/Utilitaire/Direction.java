package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.beans.property.StringProperty;

public enum  Direction {


    GAUCHE(-1,0),
    DROITE(1,0),
    HAUT(0,-1),
    BAS(0,1);


    private int x,y;


    private StringProperty nom;
    private Direction(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public StringProperty getDirectionProperty() { return nom; }

    public void setDirection(String nom) {
        this.nom.set(nom);
    }

    public int getX() {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

}