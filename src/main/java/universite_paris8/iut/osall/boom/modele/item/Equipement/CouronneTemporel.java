package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class CouronneTemporel extends Equipement{

    public CouronneTemporel(Environnement environnement, int x, int y) {
        super(environnement, "Couronne Temporel", x, y);
    }

    @Override
    public void utilise(Acteur a) {
        ralentirEnnemies(a);
    }

    public void ralentirEnnemies(Acteur acteur){
        int x = acteur.getX();
        int y = acteur.getY();
        int rangeConnexion = 128;

        for(Acteur a : this.getEnvironnement().getActeurs()){
            if (a instanceof Ennemi){
                if(((a.getX() <= x && a.getX() >= x-rangeConnexion)
                        || (a.getX() >= x && a.getX() <= x+rangeConnexion))
                        && ((a.getY() <= y && a.getY() >= y-rangeConnexion)
                        || (a.getY() >= y && a.getY() <= y+rangeConnexion))){
                    if (a.getVitesse()-2 >= 0){
                        a.setVitesse(a.getVitesse()-2);
                    }
                }
            }
        }
    }

}
