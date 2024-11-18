package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheCible;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategiePremiereCible;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

import java.util.ArrayList;

public class Dague extends Arme {

    private StrategieRechercheCible strategieRechercheCible;

    public Dague(Environnement environnement, Position position) {
        super(environnement, "Dague", position, 20, 12);
        // Définit la stratégie comme "recherche de la première cible"
        this.strategieRechercheCible = new StrategiePremiereCible();
    }

    @Override
    public void utilise(Acteur e) {
        // Utilise la stratégie pour récupérer la cible
        ArrayList<Acteur> cibles = strategieRechercheCible.getCible(getEnvironnement().getActeurs(), getEnvironnement().getJoueur());
        if (!cibles.isEmpty()) {
            Acteur cible = cibles.get(0); // La première cible trouvée
            cible.getPV().enleverPv(this.getDegat()); // Enlève les PV de la cible
        }
    }

    @Override
    public void equip(Joueur joueur) {

    }
}
