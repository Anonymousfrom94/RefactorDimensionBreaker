package universite_paris8.iut.osall.boom.modele.entite;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.Level;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.item.Equipement.BotteLevitation;
import universite_paris8.iut.osall.boom.modele.item.Equipement.Equipement;
import universite_paris8.iut.osall.boom.modele.item.Item;

public class Joueur extends Acteur {


    private final ObservableList<Item> inventaire;
    private Equipement equipement;
    


    public Joueur(Environnement environnement) {
        super(environnement, new Position(10,10), Direction.BAS, 5,300, new Hitbox(14,14));
        this.inventaire = FXCollections.observableArrayList();
        inventaire.add(super.getArme());
        this.equipement = null;
    }

    @Override
    public void seDeplace() {
        Direction direction = this.getDirection();
        if (peutSeDeplacer()) {
            int dx = 0;
            int dy = 0;
            int vitesse = super.getVitesse();

            if (direction.equals(Direction.HAUT)){
                if (this.getPosition().getY() - super.getVitesse() > 0){
                    dy -= vitesse;
                }
            }
            if (direction.equals(Direction.BAS)){
                if (this.getPosition().getY() + 16 + super.getVitesse() < super.getEnvironnement().getHeight()){
                    dy += vitesse;
                }
            }
            if (direction.equals(Direction.GAUCHE)){
                if (this.getPosition().getX() - super.getVitesse() > 0){
                    dx -= vitesse;
                }
            }
            if (direction.equals(Direction.DROITE)){
                if (this.getPosition().getX() + 16 + super.getVitesse() < super.getEnvironnement().getWidth()){
                    dx += vitesse;
                }
            }
            getPosition().setX(getPosition().getX() + dx);
            getPosition().setY(getPosition().getY() + dy);
        }
    }


    public Acteur chercherActeurAttaquable(){
        for(Acteur e : super.getEnvironnement().getActeurs()){
            if(e instanceof Ennemi){
                if (
                        (this.getPosition().getX() - super.getArme().getRange() <= e.getX() && this.getPosition().getX() + 16 + super.getArme().getRange() >= e.getX()) &&
                                (this.getPosition().getY() - super.getArme().getRange() <= e.getPosition().getY() && this.getPosition().getY() + 16 + super.getArme().getRange() >= e.getPosition().getY())
                ){
                    System.out.println("ennemie proche");
                    return e;
                }

            }
        }
        System.out.println("Pas d'ennemie");
        return null;
    }

    @Override
    public void attaque() {

        Acteur e = chercherActeurAttaquable();

        if (e != null && e!=this) {

            super.getArme().utilise(e);

        }
    }

    public Item chercherItemRamassable(){
        for (Item item : this.getEnvironnement().getInventaireEnvironnement()){
            if (
                    (this.getX() - 10 <= item.getX() && this.getX() + 16 + 10 >= item.getX()) &&
                            (this.getPosition().getY() - 10 <= item.getPosition().getYProperty() && this.getPosition().getY() + 16 + 10 >= item.getPosition().getYProperty())
            ){
                return item;
            }

        }
        return null;
    }

    public void ramasse() {

        Item item = chercherItemRamassable();
        if (item != null){
            this.inventaire.add(item);
            System.out.println(this.inventaire);
            getEnvironnement().getInventaireEnvironnement().remove(item);
        }

    }

    @Override
    public void agit() {
        seDeplace();
        ramasse();
//        attaque();
    }

    public boolean aBottesDeLevitation() {
        return this.equipement instanceof BotteLevitation;
    }

    public boolean peutSeDeplacer(){}

    @Override
    /*public boolean estDansHitbox() {
        Hitbox hitbox = this.getHitbox();
        if (Direction.DROITE && hitbox.getPointLePlusADroite());
    }*/




    /* *********************************************************************************************************************
                                                GETTER & SETTER
********************************************************************************************************************* */
    public ObservableList<Item> getInventaire() {
        return inventaire;
    }

    public Direction getDirection() {
        return super.direction;
    }
    public void setDirection(Direction d) {
        super.direction=d;
    }

    public Equipement getEquipement() {
        return equipement;
    }
    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public Position getPosition() {
        
    }

    /* *********************************************************************************************************************

********************************************************************************************************************* */

}