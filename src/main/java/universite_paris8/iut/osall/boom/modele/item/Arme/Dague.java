package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Dague extends Arme{

    public Dague(Environnement environnement, Position position) {
        super(environnement, "Dague", position, 20, 12);
    }

    @Override
    public void utilise(Acteur e) {
        e.getPV().enleverPv(this.getDegat());
    }

    @Override
    public void equip(Joueur joueur) {

    }
}
