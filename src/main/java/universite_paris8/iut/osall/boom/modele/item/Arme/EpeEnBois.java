package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class EpeEnBois extends Arme{

    public EpeEnBois(Environnement environnement, int x, int y) {
        super(environnement,"Epée en Bois", x, y, 10, 15);
    }

    public void utilise(Ennemi e) {
        e.enleverPv(getDegat());
    }
}
