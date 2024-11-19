package universite_paris8.iut.osall.boom.modele.entite;

import universite_paris8.iut.osall.boom.modele.DeplacementStrategie.DeplacementStrategie;
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
    private Hitbox hitbox;
    private PV pv;
    private Arme arme;
    private DeplacementStrategie deplacementStrategie;

    public Acteur(Environnement environnement, Position position, Direction direction, int vitesse, int pvMax, Hitbox hitbox) {
        this.environnement = environnement;
        this.position = position;
        this.direction = direction;
        this.vitesse = vitesse;
        this.pv = new PV(pvMax);
        this.id = "#" + compteur;
        this.arme = null;
        compteur++;
        this.environnement.getActeurs().add(this);
        this.hitbox = hitbox;
        this.deplacementStrategie=null;
    }



    // Méthodes abstraites
    public abstract void agit();
    public abstract void attaque();

    public void seDeplace(){
        getDeplacementStrategie().deplacement();
    }

/* *********************************************************************************************************************
                                                ACCESSEURS & MUTATEURS
********************************************************************************************************************* */

    public String getId() {
        return id;
    }

    public Position getPosition(){
        return position;
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

    public PV getPV() {
        return pv;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Arme getArme() {
        return arme;
    }

    public void setArme(Arme arme) {
        this.arme = arme;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public DeplacementStrategie getDeplacementStrategie(){
        return deplacementStrategie;
    }

    public void setDeplacementStrategie(DeplacementStrategie deplacementStrategie){
       this.deplacementStrategie = deplacementStrategie;
    }

    public boolean estDansLaPortee(Position joueurPosition, Position ennemiPosition, int range) {
        int distanceX = joueurPosition.getX() - ennemiPosition.getX();
        int distanceY = joueurPosition.getY() - ennemiPosition.getY();
        return (distanceX * distanceX + distanceY * distanceY) <= (range * range);
    }


    /* *********************************************************************************************************************

     ********************************************************************************************************************* */

}
