package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheCible;
import universite_paris8.iut.osall.boom.modele.Utilitaire.StrategieRechercheZone;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

import java.util.ArrayList;

public class BatonElectrique extends Arme{

    private StrategieRechercheCible strategieRechercheCible;

    public BatonElectrique(Environnement environnement, Position position) {
        super(environnement, "Baton Electrique",position, 8, 32);
        strategieRechercheCible = new StrategieRechercheZone(80);
    }

    @Override
    public void utilise(Acteur a) {
        attackDeZone(a);
    }

    @Override
    public void equip(Joueur joueur) {

    }

    //a modifier



    public void attackDeZone(Acteur e){

        ArrayList<Acteur> cibles = getEnvironnement().getCible(strategieRechercheCible);
        e.getPV().enleverPv(this.getDegat());
        int rangeConnexion = 80;

        for(Acteur a : this.getEnvironnement().getActeurs()){
            if (a instanceof Ennemi) {
                if(((a.getPosition().getX() <= e.getPosition().getX() && a.getPosition().getX() >= e.getPosition().getX()-rangeConnexion)
                        || (a.getPosition().getX() >= e.getPosition().getX() && a.getPosition().getX() <= e.getPosition().getX()+rangeConnexion))
                        && ((a.getPosition().getY() <= e.getPosition().getY() && a.getPosition().getY() >= e.getPosition().getY()-rangeConnexion)
                        || (a.getPosition().getY() >= e.getPosition().getY() && a.getPosition().getY() <= e.getPosition().getY()+rangeConnexion))){
                    a.getPV().enleverPv(this.getDegat());
                }
            }
        }
    }
}
