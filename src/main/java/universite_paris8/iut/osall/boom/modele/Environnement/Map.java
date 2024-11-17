package universite_paris8.iut.osall.boom.modele.Environnement;

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

//        System.out.println("Calcul indice: x=" + newX + " y=" + newY + " => ligne=" + ligne + " colonne=" + colonne + " => indice=" + ind);

        return ind;
    }



//A supprimer mais Barou vas surement s'en inspiré'
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


    public boolean positionLibre(int x, int y ){

        int indice = indice(x, y);

        if (indice<0 || indice >= tableau.length) {
            return false;
        }
        int idTuile = tableau[indice];

        return !estDevantObstacle(idTuile);

    }


/* *********************************************************************************************************************
                                             GETTER & SETTER & BOOLEAN
********************************************************************************************************************* */

    public boolean estDevantObstacle(int val) {

        for (int obstacle : environnement.getObstacles()) {

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
