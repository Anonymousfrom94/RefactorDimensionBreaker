package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

import java.util.ArrayList;

public class StrategieRechercheZone extends StrategieRechercheCible{

    private int rayon;

    public StrategieRechercheZone(int rayon) {
        super();
    }

    @Override
    public ArrayList<Acteur> getCible(ObservableList<Acteur> acteurs) {
        return null;
    }
}
