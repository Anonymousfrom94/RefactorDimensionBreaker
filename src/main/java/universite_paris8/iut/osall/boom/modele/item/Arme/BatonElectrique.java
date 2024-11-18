package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.StrategieAttaquePlusieurCibles;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheCible;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheZone;

public class BatonElectrique extends Arme {

    public BatonElectrique(Environnement environnement, Position position) {
        super(environnement, "Baton Electrique", position, 8, 80);
    }

}
