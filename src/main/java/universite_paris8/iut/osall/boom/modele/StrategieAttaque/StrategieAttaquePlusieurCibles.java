package universite_paris8.iut.osall.boom.modele.StrategieAttaque;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheCible;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheZone;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;

import java.util.ArrayList;

public class StrategieAttaquePlusieurCibles implements StrategieAttaque {

    private Arme arme;
    private StrategieRechercheCible strategieRechercheCible;

    public StrategieAttaquePlusieurCibles(Arme arme) {
        this.arme = arme;
        this.strategieRechercheCible = new StrategieRechercheZone(80);
    }

    @Override
    public void attaque() {
        ArrayList<Acteur> cible = Environnement.getInstance().getCible(strategieRechercheCible);
        for (Acteur acteur : cible) {
            acteur.getPV().enleverPv(arme.getDegat());
        }
    }
}
