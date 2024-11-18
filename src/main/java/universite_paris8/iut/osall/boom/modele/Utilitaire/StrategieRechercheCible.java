package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

import java.util.ArrayList;

public abstract class StrategieRechercheCible {

    public abstract ArrayList<Acteur> getCible(ObservableList<Acteur> acteurs, Acteur utilisateur);

}
