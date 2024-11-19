package universite_paris8.iut.osall.boom.modele.StrategieAttaque.Attaque;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Cible.StrategiePremiereCible;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Cible.StrategieRechercheCible;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;

import java.util.ArrayList;

public class StrategieAttaqueSimple implements StrategieAttaque {

    private Arme arme;
    private StrategieRechercheCible strategieRechercheCible;

    public StrategieAttaqueSimple(Arme arme) {
        this.arme = arme;
        this.strategieRechercheCible = new StrategiePremiereCible();
    }

    @Override
    public void attaque() {
        ArrayList<Acteur> cible = Environnement.getInstance().getCible(strategieRechercheCible);
        if (cible != null && !cible.isEmpty()) {
            cible.get(0).getPV().enleverPv(arme.getDegat());
        } else {
            // Gestion d'une absence de cible
            System.out.println("Aucune cible trouvée.");
        }
    }
}
