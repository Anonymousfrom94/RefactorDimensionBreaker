package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Attaque.StrategieAttaqueSimple;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Attaque.StrategieAttaqueSquelette;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;

public class EpeeEnBois extends Arme {

    public EpeeEnBois(Environnement environnement, Position position, Acteur utilisateur) {
        super(environnement, "Epée en Bois", position, 10, 15);

        if (utilisateur instanceof Joueur) {
            this.setStrategie(new StrategieAttaqueSimple(this));
        } else if (utilisateur instanceof Ennemi) {
            this.setStrategie(new StrategieAttaqueSquelette(this, utilisateur));
        } else {
            this.setStrategie(new StrategieAttaqueSimple(this));
        }
    }
}
