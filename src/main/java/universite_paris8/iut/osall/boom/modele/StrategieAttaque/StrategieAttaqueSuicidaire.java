package universite_paris8.iut.osall.boom.modele.StrategieAttaque;

import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Squelette;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;

public class StrategieAttaqueSuicidaire implements StrategieAttaque {

    private Arme arme;
    private Joueur joueur;

    public StrategieAttaqueSuicidaire(Arme arme, Joueur joueur) {
        this.arme = arme;
        this.joueur = joueur;
    }

    @Override
    public void attaque(Acteur cible) {
        if (cible instanceof Squelette){
            cible.getPV().enleverPv(arme.getDegat());
            joueur.getPV().setPv(joueur.getPV().getPv()-10);
        }
    }
}
