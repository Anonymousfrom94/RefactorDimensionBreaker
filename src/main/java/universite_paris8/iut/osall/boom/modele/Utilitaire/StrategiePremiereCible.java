package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;

import java.util.ArrayList;

public class StrategiePremiereCible extends StrategieRechercheCible {

    @Override
    public ArrayList<Acteur> getCible(ObservableList<Acteur> acteurs, Joueur joueur) {
        ArrayList<Acteur> result = new ArrayList<>();
        for (Acteur acteur : acteurs) {
            if (acteur instanceof Ennemi) {
                result.add(acteur);
                break;
            }
        }
        return result;
    }
}
