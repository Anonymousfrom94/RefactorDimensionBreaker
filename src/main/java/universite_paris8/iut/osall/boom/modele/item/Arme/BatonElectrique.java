package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.StrategieAttaqueZone;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class BatonElectrique extends Arme{

    public BatonElectrique(Environnement environnement, Position position) {
        super(environnement, "Baton Electrique",position, 8, 64, new StrategieAttaqueZone(null, 80));
        this.setStrategie(new StrategieAttaqueZone(this, 80));
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }

}
