package universite_paris8.iut.osall.boom.modele.StrategieAttaque.Cible;

import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;

import java.util.ArrayList;

public class StrategiePremiereCible extends StrategieRechercheCible {

    @Override
    public ArrayList<Acteur> getCible(ObservableList<Acteur> acteurs, Acteur utilisateur) {
        ArrayList<Acteur> result = new ArrayList<>();
        for (Acteur acteur : acteurs) {
            if (acteur instanceof Ennemi) {
                if (utilisateur.estDansLaPortee(utilisateur.getPosition(), acteur.getPosition(), 80)) {
                    result.add(acteur);
                    return result; // Retourne immédiatement pour éviter break.
                }
            }
        }
        return result;
    }
}
