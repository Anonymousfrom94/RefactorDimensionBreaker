package universite_paris8.iut.osall.boom.modele.Environnement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Boss;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Squelette;
import universite_paris8.iut.osall.boom.modele.item.Arme.BatonElectrique;
import universite_paris8.iut.osall.boom.modele.item.Arme.Dague;
import universite_paris8.iut.osall.boom.modele.item.Arme.Sniper;
import universite_paris8.iut.osall.boom.modele.item.Consommable.PotionHeal;
import universite_paris8.iut.osall.boom.modele.item.Consommable.TotemResurrection;
import universite_paris8.iut.osall.boom.modele.item.Equipement.BotteLevitation;
import universite_paris8.iut.osall.boom.modele.item.Equipement.CeintureTP;
import universite_paris8.iut.osall.boom.modele.item.Equipement.CouronneTemporel;
import universite_paris8.iut.osall.boom.modele.item.Item;

import java.util.ArrayList;
import java.util.Map;

public class Environnement {
    private Map map;
   // private int width;
   // private int height;
    private Joueur joueur;
    private ObservableList<Acteur> acteurs;
    private ObservableList<Item> inventaireEnvironnement;
//    private int largeurTuile;
 //   private int hauteurTuile;
    private ArrayList<Integer> obstacles;
    private ArrayList<Integer> blocNoSpawn;
    private int compteurKill;
    private int[] infoTuile;


    public Environnement() {
//        largeurTuile = 16;
//        hauteurTuile = 16;
//        this.width = 100 * largeurTuile;
//        this.height = 100 * hauteurTuile;
        this.acteurs = FXCollections.observableArrayList();
        this.inventaireEnvironnement = FXCollections.observableArrayList();
        this.obstacles = new ArrayList<>();
        this.blocNoSpawn = new ArrayList<>();
        this.compteurKill = 0;
        this.infoTuile = new int[3];
        this.infoTuile[0] = 16;  // Taille d'une tuile
        this.infoTuile[1] = 100; // Nombre de colonnes
        this.infoTuile[2] = 100; // Nombre de lignes
        init();
    }

    public void unTour(int temps) {
        joueur.ramasse();

        // Déplacement des acteurs (ennemis)
        for (int i = acteurs.size() - 1; i >= 0; i--) {
            Acteur acteur = acteurs.get(i);
            if (!acteur.estVivant()) {
                System.out.println("Mort de : " + acteur);
                acteurs.remove(i);
                if (acteur instanceof Ennemi) {
                    compteurKill++;
                }

                System.out.println("Nombre d'ennemis tués : " + compteurKill);
            }
            if (acteur instanceof Ennemi){
                acteur.seDeplace();
            }
        }
        if (acteurs.size() == 1 && compteurKill == 50){
            new Boss(this);
        }
        finDeGame(temps);
    }

    public void init() {
        this.map = new Map(this);
        this.joueur = new Joueur(this);

        allAddObs();
        allAddNoSpawn();
    }

    public void spawnItemEtEnnemie(){
        for (int i = 0; i < 50; i++){
            new Squelette(this);
            // Rajouter les ennemis en fonction
        }
        // Spawn des 4 totem de résurrection
        this.getInventaireEnvironnement().add(new TotemResurrection(this.getJoueur()));
        this.getInventaireEnvironnement().add(new TotemResurrection(this.getJoueur(), 1300, 50));
        this.getInventaireEnvironnement().add(new TotemResurrection(this.getJoueur(), 82, 1300));
        this.getInventaireEnvironnement().add(new TotemResurrection(this.getJoueur(), 1400, 1300));

        // Spawn des 12 PotionHeal
        for (int i = 0; i < 3; i++){
            int ecart = i*128;
            this.getInventaireEnvironnement().add(new PotionHeal(this.getJoueur()));
            this.getInventaireEnvironnement().add(new PotionHeal(this.getJoueur(), 1100 + ecart, 50 + ecart));
            this.getInventaireEnvironnement().add(new PotionHeal(this.getJoueur(), 50 + ecart, 1100 + ecart));
            this.getInventaireEnvironnement().add(new PotionHeal(this.getJoueur(), 1300 + ecart, 1100 - ecart));
        }

        // Spawn des équipements (Bottes, ceinture, Couronne)
        this.getInventaireEnvironnement().add(new BotteLevitation(this));
        this.getInventaireEnvironnement().add(new CeintureTP(this, 110, 100));
        this.getInventaireEnvironnement().add(new CouronneTemporel(this, 1450, 150));

        // Spawn des Armes (une arme par zone)
        this.getInventaireEnvironnement().add(new Dague(this));
        this.getInventaireEnvironnement().add(new Sniper(this, 1200, 200));
        this.getInventaireEnvironnement().add(new BatonElectrique(this, 800, 1500));
    }

    // test fin de game
    public void finDeGame(int temps){
        if (this.getActeurs().size() == 1 && temps > 10 || !joueur.estVivant()){
            System.exit(0);
        }
    }

    public boolean peutSeDeplacer(Acteur acteur, boolean aBottesDeLevitation) {
        collision(getJoueur());
    }

    private void collision(Acteur acteur) {

        Hitbox hitbox = getJoueur().getHitbox();


        Position position = acteur.getPosition();
        String direction = acteur.getDirection();
        double vitesse = acteur.getVitesse();

        int x = position.getX() + vitesse * direction.getX();
        int y = position.getY() + vitesse * direction.getY();

        double extremite1;
        double extremite2;

        if (direction == "bas" || direction == "haut") {
            extremite1 = hitbox.getPointLePlusAGauche(x);
            extremite2 = hitbox.getPointLePlusADroite(x);
        } else {
            extremite1 = hitbox.getPointLePlusEnHaut(y);
            extremite2 = hitbox.getPointLePlusEnBas(y);
        }

        boolean collision = false;
        int cpt = (int) extremite1;

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


       /* for (int i = 0; i < environnement.getObstacles().size(); i++) {
            obstacle = environnement.getObstacles().get(i);
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
*/

/* *********************************************************************************************************************
                                             ADD
********************************************************************************************************************* */
    public void ajouterActeur(Acteur acteur) {
        acteurs.add(acteur);
    }

    public void ajouterObstacle(int obstacle) {
        obstacles.add(obstacle);
    }

    public void ajouterNoSpawn(int obstacle) {
        blocNoSpawn.add(obstacle);
    }

/* *********************************************************************************************************************
                                           GETTER & SETTER & BOOLEAN
********************************************************************************************************************* */
    public ObservableList<Acteur> getActeurs() {
        return acteurs;
    }

    public ObservableList<Item> getInventaireEnvironnement() {
        return inventaireEnvironnement;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLargeurTuile() {
        return largeurTuile;
    }

    public int getHauteurTuile() {
        return hauteurTuile;
    }

    public Map getMap() {
        return map;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public int[] getInfoTuile() {
        return this.infoTuile;
    }

    public ArrayList<Integer> getObstacles() {
        return obstacles;
    }

    public ArrayList<Integer> getBlocNoSpawn() {
        return blocNoSpawn;
    }

    public boolean estObstacle(int obstacle) {
        return obstacles.contains(obstacle);
    }

    public boolean estNoSpawn(int obstacle) {
        return blocNoSpawn.contains(obstacle);
    }

/* *********************************************************************************************************************

********************************************************************************************************************* */
    public void allAddObs(){
        ajouterObstacle(316);
        ajouterObstacle(319);
        ajouterObstacle(676);
        ajouterObstacle(677);
        ajouterObstacle(678);
        ajouterObstacle(679);
        ajouterObstacle(751);
        ajouterObstacle(752);
        ajouterObstacle(753);
        ajouterObstacle(754);
        ajouterObstacle(826);
        ajouterObstacle(827);
        ajouterObstacle(828);
        ajouterObstacle(829);
        ajouterObstacle(380);
        ajouterObstacle(455);
        ajouterObstacle(529);
        ajouterObstacle(530);
        ajouterObstacle(454);
        ajouterObstacle(379);
        ajouterObstacle(976);
        ajouterObstacle(977);
        ajouterObstacle(978);
        ajouterObstacle(1051);
        ajouterObstacle(1053);
        ajouterObstacle(1126);
        ajouterObstacle(1127);
        ajouterObstacle(1128);
        ajouterObstacle(465);
        ajouterObstacle(396);
        ajouterObstacle(546);
        ajouterObstacle(471);

    }

    public void allAddNoSpawn(){
        ajouterNoSpawn(316);
        ajouterNoSpawn(376);
        ajouterNoSpawn(527);
        ajouterNoSpawn(1052);
        ajouterNoSpawn(377);
        ajouterNoSpawn(378);
    }
}
