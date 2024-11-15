package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class EpeEnBois extends Arme{

    public EpeEnBois(Environnement environnement, Position position) {
        super(environnement,"Epée en Bois", position, 10, 15);
    }

    @Override
    public void utilise(Acteur a) {
        a.getPV().enleverPv(getDegat());
    }

    @Override
    public void equip(Joueur joueur) {

    }
}
