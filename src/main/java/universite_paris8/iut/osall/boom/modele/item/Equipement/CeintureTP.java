package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class CeintureTP extends Equipement{

    public CeintureTP(Environnement environnement, Position position) {
        super(environnement, "Ceinture de Téléportation", position);
    }

    @Override
    public void utilise(Acteur a) {
        seTeleporte(a);
    }

    public void seTeleporte(Acteur a){
        int rangeTP = 80;
        int dx = 0;
        int dy = 0;

        if (a.getDirection()==(Direction.DROITE)){
            if (a.getPosition().getY() - rangeTP > 0){
                dy -= rangeTP;
            }
        }
        if (a.getDirection()==(Direction.BAS)){
            if (a.getPosition().getY() + 16 + rangeTP < this.getEnvironnement().getMap().getHeight()){
                dy += rangeTP;
            }
        }
        if (a.getDirection()==(Direction.GAUCHE)){
            if (a.getPosition().getX() - rangeTP > 0){
                dx -= rangeTP;
            }
        }
        if (a.getDirection()==(Direction.DROITE)){
            if (a.getPosition().getX() + 16 + rangeTP < this.getEnvironnement().getMap().getWidth()){
                dx += rangeTP;
            }
        }
        a.getPosition().setX(a.getPosition().getX() + dx);
        a.getPosition().setY(getEnvironnement().getJoueur().getPosition().getY() + dy);
    }

}
