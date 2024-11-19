package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.Attaque.StrategieAttaquePlusieurCibles;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;

public class BatonElectrique extends Arme {

    public BatonElectrique(Environnement environnement, Position position) {
        super(environnement, "Baton Electrique", position, 8, 80);
        this.setStrategie(new StrategieAttaquePlusieurCibles(this));

    }
}
