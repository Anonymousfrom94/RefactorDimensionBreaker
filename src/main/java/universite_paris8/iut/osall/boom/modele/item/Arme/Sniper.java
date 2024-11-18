package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.DecorateurAttaqueSuicidaire;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.StrategieAttaque;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.StrategieAttaqueSimple;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Sniper extends Arme{

    private Joueur joueur;
    private StrategieAttaque strategieAttaque;

    public Sniper(Environnement environnement, Position position) {
        super(environnement, "Sniper", position, 999999, 128);
        this.joueur = environnement.getJoueur();
        this.strategieAttaque = new DecorateurAttaqueSuicidaire(this.joueur, new StrategieAttaqueSimple(this));
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }



}
