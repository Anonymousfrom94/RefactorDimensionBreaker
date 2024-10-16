package universite_paris8.iut.osall.boom.modele.entite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;

public abstract class Acteur {

    protected Direction direction;
    private Environnement environnement;
    private String id;
    private int vitesse;
    private static int compteur = 0;
    private Position position;
    private int pvMax;
    private IntegerProperty pv;
    //refactor
    private Hitbox hitbox;

    public Acteur(Environnement environnement, Position position, Direction direction, int vitesse, int pvMax, Hitbox hitbox) {
        this.environnement = environnement;
        this.position = position;
        this.direction = direction;
        this.vitesse = vitesse;
        this.pvMax = pvMax;
        this.pv = new SimpleIntegerProperty(pvMax);
        this.id = "#" + compteur;
        compteur++;
        this.environnement.getActeurs().add(this);
        this.hitbox = hitbox;
    }

    public Position getPosition(){
        return position;
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

    public abstract void seDeplace();


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
    }

    //
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
        return pv.getValue() > 0;
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

    public Hitbox getHitbox() {
        return hitbox;
    }


    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }
    public StringProperty getPropertyDirection() {
        return this.direction.getNom();
    }
}

/* *********************************************************************************************************************

********************************************************************************************************************* */
    /*@Override
    public String toString() {
        return "Acteur{" +
                ", vitesse=" + vitesse +
                ", x=" + x +
                ", y=" + y +
                '}';
    }*/
}
