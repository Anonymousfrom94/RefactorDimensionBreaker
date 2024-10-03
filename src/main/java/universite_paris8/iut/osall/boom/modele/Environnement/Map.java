package universite_paris8.iut.osall.boom.modele.Environnement;

import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

public class Map {

    private int[] tableau;
    private Environnement environnement;

    public Map(Environnement environnement) {
        this.environnement = environnement;
        this.tableau = new int[environnement.getInfoTuile()[1] * environnement.getInfoTuile()[2]];
    }

    public int indice(int newX, int newY) {
        int colonne = newX / environnement.getLargeurTuile();
        int ligne = newY / environnement.getHauteurTuile();
        return ligne * environnement.getInfoTuile()[1] + colonne;
    }

    public boolean peutSeDeplacer(Acteur acteur, boolean aBottesDeLevitation) {
        collision();
    }

    private void collision(Acteur acteur) {

        Hitbox hitbox = getEnvironnement().getJoueur().getHitbox();


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


        for (int i = 0; i < environnement.getObstacles().size(); i++) {
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

    }

    private boolean obstacle(int indice1, int indice2, int obstacle, boolean aBottesDeLevitation) {
        int[] tableau = getTableau();

        if (indice1 >= 0 && indice1 < tableau.length && indice2 >= 0 && indice2 < tableau.length) {
            if ((tableau[indice1] == obstacle || tableau[indice2] == obstacle) && (obstacle != 316 || !aBottesDeLevitation)) {
                return false;
            }
            return true;
        }
        return false;
    }

/* *********************************************************************************************************************
                                             GETTER & SETTER & BOOLEAN
********************************************************************************************************************* */
    public boolean estObstacle(int val) {
        for (int obstacle : environnement.getObstacles()) {
            if (tableau[val] == obstacle) {
                return true;
            }
        }
        return false;
    }

    public boolean estNoSpawn(int val) {
        for (int obstacle : environnement.getBlocNoSpawn()) {
            if (tableau[val] == obstacle) {
                return true;
            }
        }
        return false;
    }

    public int[] getTableau() {
        return tableau;
    }

    public void setTableau(int[] tableau) {
        this.tableau = tableau;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

/* *********************************************************************************************************************

********************************************************************************************************************* */
}
