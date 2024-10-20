package universite_paris8.iut.osall.boom.modele.Environnement;

import universite_paris8.iut.osall.boom.modele.entite.Acteur;

public class Map {

    private int[] tableau;
    private Environnement environnement;

    public Map(Environnement environnement) {
        this.environnement = environnement;
        this.tableau = new int[environnement.getInfoTuile()[1] * environnement.getInfoTuile()[2]];
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
