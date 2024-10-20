package universite_paris8.iut.osall.boom.modele.entite;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
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
        super(environnement, 780, 485,14, 14, 5, 300);
        this.inventaire = FXCollections.observableArrayList();
        inventaire.add(super.getArme());
        this.equipement = null;
    }

    @Override
    public void seDeplace() {
        if (peutSeDeplacer(this, aBottesDeLevitation())) {
            int dx = 0;
            int dy = 0;
            int vitesse = getVitesse();

            if (this.direction.get().contains("haut")){
                if (this.getY() - getVitesse() > 0){
                    dy -= vitesse;
                }
            }
            if (this.direction.get().contains("bas")){
                if (this.getY() + 16 + getVitesse() < this.getEnvironnement().getHeight()){
                    dy += vitesse;
                }
            }
            if (this.direction.get().contains("gauche")){
                if (this.getX() - getVitesse() > 0){
                    dx -= vitesse;
                }
            }
            if (this.direction.get().contains("droite")){
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

    public boolean peutSeDeplacer(Acteur acteur, boolean aBottesDeLevitation) {
        int indice1, indice2;
        int obstacle;

        for (int i = 0; i < getEnvironnement().getObstacles().size(); i++) {
            obstacle = getEnvironnement().getObstacles().get(i);
            if (acteur.getDirection().contains("haut")) {
                indice1 = indice(acteur.getX(), acteur.getY() - acteur.getVitesse());
                indice2 = indice(acteur.getX() + acteur.getLargeur(), acteur.getY() - acteur.getVitesse());
                if (!obstacle(indice1, indice2, obstacle, aBottesDeLevitation)) {
                    return false;
                }
            }
            if (acteur.getDirection().contains("bas")) {
                indice1 = indice(acteur.getX(), acteur.getY() + acteur.getHauteur() + acteur.getVitesse());
                indice2 = indice(acteur.getX() + acteur.getLargeur(), acteur.getY() + acteur.getHauteur() + acteur.getVitesse());
                if (!obstacle(indice1, indice2, obstacle, aBottesDeLevitation)) {
                    return false;
                }
            }
            if (acteur.getDirection().contains("gauche")) {
                indice1 = indice(acteur.getX() - acteur.getVitesse(), acteur.getY());
                indice2 = indice(acteur.getX() - acteur.getVitesse(), acteur.getY() + acteur.getHauteur());
                if (!obstacle(indice1, indice2, obstacle, aBottesDeLevitation)) {
                    return false;
                }
            }
            if (acteur.getDirection().contains("droite")) {
                indice1 = indice(acteur.getX() + acteur.getLargeur() + acteur.getVitesse(), acteur.getY());
                indice2 = indice(acteur.getX() + acteur.getLargeur() + acteur.getVitesse(), acteur.getY() + acteur.getHauteur());
                if (!obstacle(indice1, indice2, obstacle, aBottesDeLevitation)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean obstacle(int indice1, int indice2, int obstacle, boolean aBottesDeLevitation) {
        int[] tableau = getEnvironnement().getMap().getTableau();

        if (indice1 >= 0 && indice1 < tableau.length && indice2 >= 0 && indice2 < tableau.length) {
            if ((tableau[indice1] == obstacle || tableau[indice2] == obstacle) && (obstacle != 316 || !aBottesDeLevitation)) {
                return false;
            }
            return true;
        }
        return false;
    }

/* *********************************************************************************************************************
                                                GETTER & SETTER
********************************************************************************************************************* */
    public ObservableList<Item> getInventaire() {
        return inventaire;
    }

    public String getDirection() {
        return this.direction.get();
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