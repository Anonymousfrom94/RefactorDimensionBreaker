package universite_paris8.iut.osall.boom.modele.StrategieAttaque;

import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.item.Arme.Arme;

public class StrategieAttaqueZone implements StrategieAttaque {

    private Arme arme;
    private int rangeConnexion;

    public StrategieAttaqueZone(Arme arme, int rangeConnexion) {
        this.arme = arme;
        this.rangeConnexion = rangeConnexion;
    }

    @Override
    public void attaque(Acteur cible) {
        cible.getPV().enleverPv(arme.getDegat());

        for(Acteur a : arme.getEnvironnement().getActeurs()){
            if (a instanceof Ennemi) {
                if(((a.getPosition().getX() <= cible.getPosition().getX() && a.getPosition().getX() >= cible.getPosition().getX()-rangeConnexion)
                        || (a.getPosition().getX() >= cible.getPosition().getX() && a.getPosition().getX() <= cible.getPosition().getX()+rangeConnexion))
                        && ((a.getPosition().getY() <= cible.getPosition().getY() && a.getPosition().getY() >= cible.getPosition().getY()-rangeConnexion)
                        || (a.getPosition().getY() >= cible.getPosition().getY() && a.getPosition().getY() <= cible.getPosition().getY()+rangeConnexion))){
                    a.getPV().enleverPv(arme.getDegat());
                }
            }
        }
    }

}
