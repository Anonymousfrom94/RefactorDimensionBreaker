package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Attaque.DecorateurAttaqueSuicidaire;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Attaque.StrategieAttaque;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Attaque.StrategieAttaqueSimple;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Sniper extends Arme{

    private Joueur joueur;
    private StrategieAttaque strategieAttaque;

    public Sniper(Environnement environnement, Position position) {
        super(environnement, "Sniper", position, 999999, 128);
        this.joueur = environnement.getJoueur();
        this.setStrategie(new DecorateurAttaqueSuicidaire(this.joueur, new StrategieAttaqueSimple(this)));
    }

}
