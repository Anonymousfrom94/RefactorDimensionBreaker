package universite_paris8.iut.osall.boom.modele.StrategieAttaque.Cible;

import javafx.collections.ObservableList;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

import java.util.ArrayList;

/**
 * Classe abstraite pour définir une stratégie de recherche de cible.
 */
public abstract class StrategieRechercheCible {
    /**
     * Recherche et retourne une liste de cibles potentielles pour un acteur donné.
     *
     * @param acteurs Liste des acteurs disponibles.
     * @param utilisateur Acteur qui utilise la stratégie.
     * @return Liste des acteurs sélectionnés comme cibles.
     */
    public abstract ArrayList<Acteur> getCible(ObservableList<Acteur> acteurs, Acteur utilisateur);
}
