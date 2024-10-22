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
        this.vitesse = vitesse;
        this.pv = new PV(pvMax);
        this.id = "#" + compteur;
        this.position = position;
        this.direction = direction;
        compteur++;
        this.environnement.getActeurs().add(this);
        this.hitbox = hitbox;
    }

    // Accesseur pour la position
    public Position getPosition(){
        return position;
    }

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

    // Accesseurs et mutateurs pour la position (coordonnées X et Y)
    public int getX(){
        return this.position.getX(); // Correction : c'était getY() à la place de getX()
    }
    public void setX(int x) {
        this.position.setX(x);
    }
    public int getY(){
        return this.position.getY();
    }
    public void setY(int y) {
        this.position.setY(y);
    }
    public IntegerProperty getXProperty(){
        return this.position.getXProperty();
    }
    public IntegerProperty getYProperty(){
        return this.position.getYProperty();
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
