package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.StrategieAttaqueSuicidaire;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Boss;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Squelette;

public class Sniper extends Arme{

    private Joueur joueur;

    public Sniper(Environnement environnement, Position position) {
        super(environnement, "Sniper", position, 999999, 128, new StrategieAttaqueSuicidaire(null, null));
        this.joueur = environnement.getJoueur();
        this.setStrategie(new StrategieAttaqueSuicidaire(this, this.joueur));
    }


}
