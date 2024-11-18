package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.StrategieAttaqueSimple;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Dague extends Arme{

    public Dague(Environnement environnement, Position position) {
        super(environnement, "Dague", position, 20, 12, new StrategieAttaqueSimple(null));
        this.setStrategie(new StrategieAttaqueSimple(this));
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setArme(this);
    }

}
