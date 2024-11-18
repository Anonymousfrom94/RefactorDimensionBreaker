package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.StrategieAttaqueSimple;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieAttaqueSquelette;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Squelette;

public class EpeeEnBois extends Arme {

    public EpeeEnBois(Environnement environnement, Position position, Acteur acteur) {
        super(environnement, "Epée en Bois", position, 10, 15);

        if (acteur instanceof Joueur) {
            this.setStrategie(new StrategieAttaqueSimple(this));
        } else if (acteur instanceof Squelette) {
            this.setStrategie(new StrategieAttaqueSquelette(this));
        } else {
            System.out.println("Acteur inconnu, stratégie d'attaque par défaut appliquée.");
            this.setStrategie(new StrategieAttaqueSimple(this));
        }

    }
}
