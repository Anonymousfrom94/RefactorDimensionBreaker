package universite_paris8.iut.osall.boom.modele.entite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import universite_paris8.iut.osall.boom.modele.DeplacementStrategie.DeplacementSimple;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.item.Equipement.Equipement;
import universite_paris8.iut.osall.boom.modele.item.Item;

import static java.lang.Thread.sleep;

public class Joueur extends Acteur {


    private final ObservableList<Item> inventaire;
    private Equipement equipement;



    public Joueur(Environnement environnement) {
        super(environnement, new Position(780,550), Direction.ARRET, 5,300, new Hitbox(16,16));
//        setDirectionJoueur();
        this.inventaire = FXCollections.observableArrayList();
        inventaire.add(super.getArme());
        this.equipement = null;
        setDeplacementStrategie(new DeplacementSimple(this));
    }

    //controller
    private void setDirectionJoueur() {
        setDirection(Direction.BAS);
        getDirection().setDirectionProperty("bas");
    }

    @Override
    public void seDeplace() {

        getDeplacementStrategie().deplacement();
    }

    public Acteur chercherActeurAttaquable() {

        Position centreJoueur = this.getPosition();
        int range = super.getArme().getRange();

        if (super.getArme() == null) {
            return null;
        }

        for (Acteur cible : super.getEnvironnement().getActeurs()) {
            if (cible instanceof Ennemi && cible != this) {
                Position positionEnnemi = cible.getPosition();
                if (estDansLaPortee(centreJoueur, positionEnnemi, range)) {
                    System.out.println("Un ennemi est à portée");
                    return cible;
                }
            }
        }
        return null;
    }

    public boolean estDansLaPortee(Position joueurPosition, Position ennemiPosition, int range) {
        int distanceX = joueurPosition.getX() - ennemiPosition.getX();
        int distanceY = joueurPosition.getY() - ennemiPosition.getY();
        return (distanceX * distanceX + distanceY * distanceY) <= (range * range);
    }


    @Override
    public void attaque() {
        if (super.getArme() == null) {
            System.out.println("Vous n'avez pas d'arme équipée !");
            return;
        }

        Acteur cible = chercherActeurAttaquable();
        if (cible != null) {
            super.getArme().utilise(cible);
            System.out.println("Vous avez attaqué un ennemi !");
        } else {
            System.out.println("Aucun ennemi à portée !");
        }
    }


    public Item chercherItemRamassable() {
        Hitbox hitbox = this.getHitbox();
        Position centreJoueur = this.getPosition();


        for (Item item : this.getEnvironnement().getInventaireEnvironnement()) {
            Position positionItem = item.getPosition();

            if (hitbox.estAProximité(centreJoueur, positionItem)) {
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

    public void retirerDeInventaire(Item item){
        this.inventaire.remove(item);
    }

    @Override
    public void agit() {
        seDeplace();
        ramasse();
//        attaque();
    }





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
        this.setDeplacementStrategie(new DeplacementSimple(this));
        this.equipement = equipement;
    }


    /* *********************************************************************************************************************

********************************************************************************************************************* */

}