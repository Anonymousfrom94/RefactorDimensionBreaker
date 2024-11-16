package universite_paris8.iut.osall.boom.modele.entite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    }

    private void setDirectionJoueur() {
        setDirection(Direction.BAS);
        getDirection().setDirectionProperty("bas");
    }

    @Override
    public void seDeplace() {
        Direction direction = this.getDirection();
        if (peutSeDeplacer() ) {
            this.getPosition().setX(getPosition().getX()+(getVitesse()*getDirection().getX()));
            this.getPosition().setY(getPosition().getY()+(getVitesse()*getDirection().getY()));
        }

    }



    public Acteur chercherActeurAttaquable(){
        Hitbox hitbox = this.getHitbox();
        Position centreJoueur = this.getPosition();

        for(Acteur e : super.getEnvironnement().getActeurs()){
            if(e instanceof Ennemi){

                for (Acteur ennemi : this.getEnvironnement().getActeurs()) {
                Position positionEnnemi = ennemi.getPosition();

                    if (hitbox.estAProximité(centreJoueur, positionEnnemi)) {
//                        System.out.println("Oh un ennemi !");
                        return ennemi;
                    }
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

    @Override
    public void agit() {
        seDeplace();
        ramasse();
        attaque();
    }

    public boolean aBottesDeLevitation() {
        return this.equipement instanceof BotteLevitation;
    }

    public boolean peutSeDeplacer() {
//       Vérifie s'il y a une collision dans la direction actuelle du joueur
        return collisionMap();  // Retourne vrai si collisionMap renvoie faux (pas de collision)

    }

    public boolean collisionMap() {

        Hitbox hitbox = this.getHitbox();
        boolean libre = true;

    // Calculer la position cible en fonction de la direction et de la vitesse
    // Position actuelle de l'objet
        int pX = this.getPosition().getX()+(getVitesse()*getDirection().getX());
        int pY = this.getPosition().getY()+(getVitesse()*getDirection().getY());

    // Récupération de l'environnement et de la carte
        Environnement e = getEnvironnement();
        Map m = e.getMap();

    // Calcul des coordonnées des coins du hitbox à la nouvelle position
        int bas = hitbox.getPointLePlusEnBas(new Position(pX, pY));
        int gauche = hitbox.getPointLePlusAGauche(new Position(pX, pY));
        int droite = hitbox.getPointLePlusADroite(new Position(pX, pY));
        int haut = hitbox.getPointLePlusEnHaut(new Position(pX, pY));

    //Limite de la map
        if (gauche < 0 || droite >= m.getWidth() || haut < 0 || bas >= m.getHeight()) {
            return false;
        }

    // Vérification si les positions sont libres
        boolean positionHautGaucheLibre = m.positionLibre(gauche, haut);
        boolean positionBasGaucheLibre = m.positionLibre(gauche, bas);
        boolean positionBasDroiteLibre = m.positionLibre(droite, bas);
        boolean positionHautDroiteLibre = m.positionLibre(droite, haut);

        if (!positionBasGaucheLibre || !positionBasDroiteLibre || !positionHautDroiteLibre || !positionHautGaucheLibre) {
            libre = false;
        }

        return libre;

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
        this.equipement = equipement;
    }


    /* *********************************************************************************************************************

********************************************************************************************************************* */

}