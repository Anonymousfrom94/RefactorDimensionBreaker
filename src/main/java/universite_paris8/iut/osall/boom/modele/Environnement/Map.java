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

    public int indice(int newX, int newY) {
        int colonne = newX / environnement.getLargeurTuile();
        int ligne = newY / environnement.getHauteurTuile();
        return ligne * environnement.getInfoTuile()[1] + colonne;
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

    public int getLargeurMonde(){
        return environnement.getLargeurTuile();
    }

/* *********************************************************************************************************************

********************************************************************************************************************* */
}
