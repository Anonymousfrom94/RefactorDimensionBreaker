package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import java.util.ArrayList;

public class StrategieAttaqueSquelette extends StrategieRechercheCible {

    @Override
    public ArrayList<Acteur> getCible(ObservableList<Acteur> acteurs, Acteur utilisateur) {
        ArrayList<Acteur> result = new ArrayList<>();
        for (Acteur acteur : acteurs) {
            if (utilisateur instanceof Ennemi) {
                if (Environnement.getInstance().getJoueur().estDansLaPortee(utilisateur.getPosition(), acteur.getPosition(), 80)){
                    result.add(acteur);
                    break;}
            }
        }
        return result;
    }
}
