package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import universite_paris8.iut.osall.boom.modele.Attribut.PV;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;

public abstract class Acteur {

    public final StringProperty direction;
    private Environnement environnement;
    private String id;
    private int vitesse;
    private static int compteur = 0;
    private IntegerProperty x, y;
    private int largeur, hauteur;
    private PV pv;
    private Arme arme;

    public Acteur(Environnement environnement, int x, int y, int largeur, int hauteur, int vitesse, int pvMax) {
        this.environnement = environnement;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.direction = new SimpleStringProperty("");
        this.vitesse = vitesse;
        this.pv = new PV(pvMax);
        this.id = "#" + compteur;
        compteur++;
        this.environnement.getActeurs().add(this);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.arme = new EpeEnBois(environnement);
    }

    public abstract void agit();

    public abstract void seDeplace();

    public abstract void attaque();

/* *********************************************************************************************************************
                                                GETTER & SETTER
********************************************************************************************************************* */

    public String getId() {
        return id;
    }

    public int getX(){
        return this.x.getValue();
    }
    public void setX(int x) {
        this.x.setValue(x);
    }
    public int getY(){
        return this.y.getValue();
    }
    public void setY(int y) {
        this.y.setValue(y);
    }
    public IntegerProperty getXproperty(){
        return this.x;
    }
    public IntegerProperty getYproperty(){
        return this.y;
    }
    public int getVitesse() {
        return vitesse;
    }
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
    public Environnement getEnvironnement() {
        return environnement;
    }
    public boolean estVivant(){
        return getPV().getPv() > 0;
    }
    public PV getPV() {return pv;}
    public int getLargeur() {
        return largeur;
    }
    public int getHauteur() {
        return hauteur;
    }

    public String getDirection() {
        return this.direction.get();
    }
    public void setDirection(String direction) {
        this.direction.set(direction);
    }
    public StringProperty getPropertyDirection(){
        return this.direction;
    }

    public Arme getArme() {
        return arme;
    }

    public void setArme(Arme arme) {
        this.arme = arme;
    }

    /* *********************************************************************************************************************

********************************************************************************************************************* */
    @Override
    public String toString() {
        return "Acteur{" +
                ", vitesse=" + vitesse +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
