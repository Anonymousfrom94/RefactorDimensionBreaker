package universite_paris8.iut.osall.boom.modele.StrategieAttaque.Attaque;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;

public class StrategieAttaqueSquelette implements StrategieAttaque {

    private final Arme arme;
    private final Acteur utilisateur;

    public StrategieAttaqueSquelette(Arme arme, Acteur utilisateur) {
        this.arme = arme;
        this.utilisateur = utilisateur;
    }

    @Override
    public void attaque() {
        Joueur joueur = Environnement.getInstance().getJoueur();

        if (joueur != null) {
            if (utilisateur.estDansLaPortee(utilisateur.getPosition(), joueur.getPosition(), arme.getRange())) {
                joueur.getPV().enleverPv(arme.getDegat());
            }
        }
    }

}
