package universite_paris8.iut.osall.boom.modele.entite;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import universite_paris8.iut.osall.boom.modele.Utilitaire.PV;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;

public abstract class Acteur {

    protected Direction direction;
    private Environnement environnement;
    private String id;
    private int vitesse;
    private static int compteur = 0;
    private Position position;
    //refactorisé
    private Hitbox hitbox;
    private PV pv;
    private Arme arme;

    // Constructeur
    public Acteur(Environnement environnement, Position position, Direction direction, int vitesse, int pvMax, Hitbox hitbox) {
        this.environnement = environnement;
        this.position = position;
        this.direction = direction;
        this.vitesse = vitesse;
        this.pv = new PV(pvMax);
        this.id = "#" + compteur;

        compteur++;
        this.environnement.getActeurs().add(this);
        this.hitbox = hitbox;
    }



    public abstract void attaque();

    // Méthodes abstraites
    public abstract void agit();

    public abstract void seDeplace();

    public abstract boolean estDansHitbox();

/* *********************************************************************************************************************
                                                ACCESSEURS & MUTATEURS
********************************************************************************************************************* */

    // Accesseur pour l'ID
    public String getId() {
        return id;
    }

    // Accesseur pour la position x et y de l'Acteur
    public Position getPosition(){
        return position;
    }

    // Accesseur et mutateur pour la vitesse
    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    // Accesseur pour l'environnement
    public Environnement getEnvironnement() {
        return environnement;
    }

    // Méthode pour vérifier si l'acteur est vivant
    public boolean estVivant(){
        return getPV().getPv() > 0;
    }

    // Accesseur pour les points de vie (PV)
    public PV getPV() {
        return pv;
    }

    // Accesseur et mutateur pour la direction
    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Accesseur et mutateur pour l'arme
    public Arme getArme() {
        return arme;
    }

    public void setArme(Arme arme) {
        this.arme = arme;
    }

    // Accesseur pour la hitbox
    public Hitbox getHitbox() {
        return hitbox;
    }


    /* *********************************************************************************************************************

     ********************************************************************************************************************* */

}
