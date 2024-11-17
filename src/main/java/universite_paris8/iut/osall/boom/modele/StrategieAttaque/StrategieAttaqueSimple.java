package universite_paris8.iut.osall.boom.modele.StrategieAttaque;

import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;

public class StrategieAttaqueSimple implements StrategieAttaque {

    private Arme arme;

    public StrategieAttaqueSimple(Arme arme) {
        this.arme = arme;
    }

    @Override
    public void attaque(Acteur cible) {
        cible.getPV().enleverPv(arme.getDegat());
    }
}
