package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheCible;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheZone;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

import java.util.ArrayList;

public class BatonElectrique extends Arme {

    private StrategieRechercheCible strategieRechercheCible;

    public BatonElectrique(Environnement environnement, Position position) {
        super(environnement, "Baton Electrique", position, 8, 32);
        this.strategieRechercheCible = new StrategieRechercheZone(80); // Stratégie de zone
    }

    @Override
    public void utilise(Acteur a) {
        ArrayList<Acteur> cibles = strategieRechercheCible.getCible(getEnvironnement().getActeurs(), getEnvironnement().getJoueur());
        for (Acteur cible : cibles) {
            cible.getPV().enleverPv(this.getDegat());
        }
    }
}
