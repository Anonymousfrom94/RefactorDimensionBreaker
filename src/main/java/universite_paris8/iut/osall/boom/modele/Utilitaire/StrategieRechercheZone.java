package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;

import java.util.ArrayList;

public class StrategieRechercheZone extends StrategieRechercheCible {

    private final int rayon; // Rayon de recherche

    public StrategieRechercheZone(int rayon) {
        this.rayon = rayon;
    }

    @Override
    public ArrayList<Acteur> getCible(ObservableList<Acteur> acteurs, Joueur joueur) {
        ArrayList<Acteur> result = new ArrayList<>();

        for (Acteur acteur : acteurs) {
            if (acteur instanceof Ennemi) { // Vérifie si c'est un ennemi
                if (dansRayon(joueur, acteur)) {
                    result.add(acteur);
                }
            }
        }
        return result;
    }

    private boolean dansRayon(Joueur source, Acteur cible) {
        // Calcul de la distance entre deux acteurs
        int dx = Math.abs(cible.getPosition().getX() - source.getPosition().getX());
        int dy = Math.abs(cible.getPosition().getY() - source.getPosition().getY());
        return Math.sqrt(dx * dx + dy * dy) <= rayon;
    }

}
