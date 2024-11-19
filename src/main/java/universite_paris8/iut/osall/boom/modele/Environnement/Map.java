package universite_paris8.iut.osall.boom.modele.Environnement;

import java.util.ArrayList;

public class Map {

    private int[] tableau;
    private Environnement environnement;
    private int width;
    private int height;
    private int largeurTuile;
    private int hauteurTuile;

    public Map(Environnement environnement) {
        this.environnement = environnement;
        this.tableau = new int[environnement.getInfoTuile()[1] * environnement.getInfoTuile()[2]];
        largeurTuile = 16;
        hauteurTuile = 16;
        this.width = 100 * largeurTuile;
        this.height = 100 * hauteurTuile;
    }

    //Permet de connaitre l'indice du tableau a 1D a partir d'un x et y
    public int indice(int newX, int newY) {
        int colonne = newX / environnement.getMap().getLargeurTuile();
        int ligne = newY / environnement.getMap().getHauteurTuile();
        int ind = ligne * environnement.getInfoTuile()[1] + colonne;


        return ind;
    }






    public boolean positionLibre(int x, int y, ArrayList<Integer> obstacles){

        int indice = indice(x, y);

        if (indice<0 || indice >= tableau.length) {
            return false;
        }
        int idTuile = tableau[indice];

        return !estDevantObstacle(idTuile, obstacles);

    }


/* *********************************************************************************************************************
                                             GETTER & SETTER & BOOLEAN
********************************************************************************************************************* */

    public boolean estDevantObstacle(int val, ArrayList<Integer> obstacles) {
        for (int obstacle : obstacles) {
            if (val== obstacle) {
                return true;
            }
        }
        return false;
    }

    public boolean peutPasSpawmIci(int val) {
        boolean peutPasSpawn = false;

        for (int obstacle : environnement.getBlocNoSpawn()) {
            if (tableau[val] == obstacle) {
                peutPasSpawn= true;
            }
        }
        return peutPasSpawn;
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


    /* *********************************************************************************************************************

********************************************************************************************************************* */
}
