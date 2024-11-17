package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

import java.util.ArrayList;

public class StrategiePremiereCicle extends StrategieRechercheCible {


    public ArrayList<Acteur> getCible(int rayon) {

    }

    @Override
    public ArrayList<Acteur> getCible(ObservableList<Acteur> acteurs) {
        return null;
    }
}
