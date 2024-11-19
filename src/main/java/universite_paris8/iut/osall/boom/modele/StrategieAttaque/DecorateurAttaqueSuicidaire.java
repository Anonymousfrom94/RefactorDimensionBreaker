package universite_paris8.iut.osall.boom.modele.StrategieAttaque;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class DecorateurAttaqueSuicidaire implements StrategieAttaque {

    private Joueur joueur;
    private StrategieAttaque strategieAttaque;

    public DecorateurAttaqueSuicidaire(Joueur joueur, StrategieAttaque strategieAttaque) {
        this.joueur = joueur;
        this.strategieAttaque = strategieAttaque;
    }

    @Override
    public void attaque() {
        strategieAttaque.attaque();
        joueur.getPV().setPv(joueur.getPV().getPv()-10);
    }
}

//rajouter la caracterisque que le joeur subit degat
//ça evite la repetition