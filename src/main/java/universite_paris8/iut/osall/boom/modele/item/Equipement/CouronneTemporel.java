package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class CouronneTemporel extends Equipement{

    public CouronneTemporel(Environnement environnement, Position position) {
        super(environnement, "Couronne Temporel", position);
    }

    @Override
    public void utilise(Acteur a) {
        ralentirEnnemies(a);
    }

    public void ralentirEnnemies(Acteur acteur){
        int x = acteur.getPosition().getX();
        int y = acteur.getPosition().getY();
        int rangeConnexion = 128;

        for(Acteur a : this.getEnvironnement().getActeurs()){
            if (a instanceof Ennemi){
                if(((a.getPosition().getX() <= x && a.getPosition().getX() >= x-rangeConnexion)
                        || (a.getPosition().getX() >= x && a.getPosition().getX() <= x+rangeConnexion))
                        && ((a.getPosition().getY() <= y && a.getPosition().getY() >= y-rangeConnexion)
                        || (a.getPosition().getY() >= y && a.getPosition().getY() <= y+rangeConnexion))){
                    if (a.getVitesse()-2 >= 0){
                        a.setVitesse(a.getVitesse()-2);
                    }
                }
            }
        }
    }

}
