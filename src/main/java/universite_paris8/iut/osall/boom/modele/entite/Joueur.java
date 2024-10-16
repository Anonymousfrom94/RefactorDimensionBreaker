package universite_paris8.iut.osall.boom.modele.entite;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeEnBois;
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
        Direction direction = this.direction;
        if (peutSeDeplacer()) {
            int dx = 0;
            int dy = 0;
            int vitesse = getVitesse();

            if (direction.equals(Direction.HAUT)){
                if (this.getY() - getVitesse() > 0){
                    dy -= vitesse;
                }
            }
            if (direction.equals(Direction.BAS)){
                if (this.getY() + 16 + getVitesse() < this.getEnvironnement().getHeight()){
                    dy += vitesse;
                }
            }
            if (direction.equals(Direction.GAUCHE)){
                if (this.getX() - getVitesse() > 0){
                    dx -= vitesse;
                }
            }
            if (direction.equals(Direction.DROITE)){
                if (this.getX() + 16 + getVitesse() < this.getEnvironnement().getWidth()){
                    dx += vitesse;
                }
            }
            setX(getX() + dx);
            setY(getY() + dy);
        }
    }

    public Acteur chercherActeurAttaquable(){
        for(Acteur e : this.getEnvironnement().getActeurs()){
            if(e instanceof Ennemi){
                if (
                        (this.getX() - getArme().getRange() <= e.getX() && this.getX() + 16 + getArme().getRange() >= e.getX()) &&
                                (this.getY() - getArme().getRange() <= e.getY() && this.getY() + 16 + getArme().getRange() >= e.getY())
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

            this.getArme().utilise(e);

        }
    }

    public Item chercherItemRamassable(){
        for (Item item : this.getEnvironnement().getInventaireEnvironnement()){
            if (
                    (this.getX() - 10 <= item.getX() && this.getX() + 16 + 10 >= item.getX()) &&
                            (this.getY() - 10 <= item.getY() && this.getY() + 16 + 10 >= item.getY())
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
    public boolean estDansHitbox() {
        Hitbox hitbox = this.getHitbox();
        if (Direction.DROITE && hitbox.getPointLePlusADroite())
    }


    /* *********************************************************************************************************************
                                                GETTER & SETTER
********************************************************************************************************************* */
    public ObservableList<Item> getInventaire() {
        return inventaire;
    }

    public Direction getDirection() {
        return this.direction;
    }
    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    public StringProperty getPropertyDirection(){
        return this.direction;
    }

    public Equipement getEquipement() {
        return equipement;
    }
    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

/* *********************************************************************************************************************

********************************************************************************************************************* */

}