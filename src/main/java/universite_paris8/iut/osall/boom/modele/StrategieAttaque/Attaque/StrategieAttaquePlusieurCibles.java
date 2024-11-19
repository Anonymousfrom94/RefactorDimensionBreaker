package universite_paris8.iut.osall.boom.modele.StrategieAttaque.Attaque;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Cible.StrategieRechercheCible;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Cible.StrategieRechercheZone;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;

import java.util.ArrayList;

public class StrategieAttaquePlusieurCibles implements StrategieAttaque {

    private Arme arme;
    private StrategieRechercheCible strategieRechercheCible;

    public StrategieAttaquePlusieurCibles(Arme arme) {
        this.arme = arme;
        this.strategieRechercheCible = new StrategieRechercheZone(arme.getRange());
    }

    @Override
    public void attaque() {
        ArrayList<Acteur> cible = Environnement.getInstance().getCible(strategieRechercheCible);
        for (Acteur acteur : cible) {
            acteur.getPV().enleverPv(arme.getDegat());
        }
    }
}
