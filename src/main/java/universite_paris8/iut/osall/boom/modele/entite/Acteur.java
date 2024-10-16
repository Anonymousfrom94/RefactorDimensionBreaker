package universite_paris8.iut.osall.boom.modele.entite;
import javafx.beans.property.IntegerProperty;
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
    //refactor
    private Hitbox hitbox;
    private PV pv;
    private Arme arme;

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

    public Position getPosition(){
        return position;
    }

    /*
    //je men occupe
    public boolean collision(Acteur acteur) {

        Hitbox hitbox = environnement.getJoueur().getHitbox();
        Position position = acteur.getPosition();
        Direction direction = acteur.getDirection();
        int vitesse = acteur.getVitesse();

        int x = position.getX() + vitesse * direction.getX();
        int y = position.getY() + vitesse * direction.getY();

        int extremite1;
        int extremite2;

        if (direction == Direction.BAS || direction == Direction.HAUT) {
            extremite1 = hitbox.getPointLePlusAGauche(x);
            extremite2 = hitbox.getPointLePlusADroite(x);
        } else {
            extremite1 = hitbox.getPointLePlusEnHaut(y);
            extremite2 = hitbox.getPointLePlusEnBas(y);
        }
        public boolean vraie(){
            return true;
        }

        boolean collision = false;
        int cpt = extremite1;

        while (cpt <= extremite2 && !collision) {
            if (direction.equals(Direction.BAS)) {
                collision = nontraversable[(int) (hitbox.getPointLePlusEnBas(y))][cpt] != -1;
            } else if (direction.equals(Direction.HAUT)) {
                collision = nontraversable[(int) (hitbox.getPointLePlusEnHaut(y))][cpt] != -1;
            } else if (direction.equals(Direction.DROITE)) {
                collision = nontraversable[cpt][(int) (hitbox.getPointLePlusADroite(x))] != -1;
            } else if (direction.equals(Direction.GAUCHE)) {
                collision = nontraversable[cpt][(int) (hitbox.getPointLePlusAGauche(x))] != -1;
            }
            cpt++;
        }

        return collision;
    }*/

    public abstract void agit();

    public abstract void seDeplace();

    public abstract boolean estDansHitbox();

/* *********************************************************************************************************************
                                                GETTER & SETTER
********************************************************************************************************************* */

    public String getId() {
        return id;
    }

    public int getX(){
        return this.position.getY();
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
    // a verif
    public IntegerProperty getXproperty(){
        return this.position.getX().;
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


    public Direction getDirection() {
        return this.direction;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }
    public StringProperty getPropertyDirection() {
        return this.direction.getNom();
    }


    public Arme getArme() {
        return arme;
    }

    public void setArme(Arme arme) {
        this.arme = arme;
    }

    /* *********************************************************************************************************************

********************************************************************************************************************* */

}
