package universite_paris8.iut.osall.boom.modele.entite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import universite_paris8.iut.osall.boom.modele.DeplacementStrategie.DeplacementSimple;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.item.Equipement.BotteLevitation;
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

    private void setDirectionJoueur() {
        setDirection(Direction.BAS);
        getDirection().setDirectionProperty("bas");
    }

    @Override
    public void seDeplace() {

        getDeplacementStrategie().deplacement();
    }

    public Acteur chercherActeurAttaquable(){
        Hitbox hitbox = this.getHitbox();
        Position centreJoueur = this.getPosition();

        for(Acteur e : super.getEnvironnement().getActeurs()){
            if(e instanceof Ennemi){


                Position positionEnnemi = e.getPosition();

                if (hitbox.estAProximité(centreJoueur, positionEnnemi)) {
                    System.out.println("Oh un ennemi !");
                    return e;

                }

            }
        }
//        System.out.println("Pas d'ennemie");
        return null;
    }

    @Override
    public void attaque() {

        Acteur e = chercherActeurAttaquable();

        if (e != null && e!=this) {

            super.getArme().utilise(e);

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