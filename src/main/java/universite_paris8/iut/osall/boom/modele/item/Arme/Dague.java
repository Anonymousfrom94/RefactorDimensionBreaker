package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.StrategieAttaqueSimple;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheCible;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategiePremiereCible;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

import java.util.ArrayList;

public class Dague extends Arme {

    private StrategieRechercheCible strategieRechercheCible;

    public Dague(Environnement environnement, Position position) {
        super(environnement, "Dague", position, 20, 12);
        // Définit la stratégie comme "recherche de la première cible"
        this.strategieRechercheCible = new StrategiePremiereCible();
    }



}
