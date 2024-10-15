package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Dague extends Arme{

    public Dague(Environnement environnement, int x, int y) {
        super(environnement, "Dague", x, y, 20, 12);
    }

    @Override
    public void utilise(Acteur e) {
        e.enleverPv(this.getDegat());
    }
}
