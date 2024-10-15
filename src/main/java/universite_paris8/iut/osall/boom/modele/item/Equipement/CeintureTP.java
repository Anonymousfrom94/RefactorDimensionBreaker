package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class CeintureTP extends Equipement{

    public CeintureTP(Environnement environnement) {
        super(environnement, "Ceinture de Téléportation", 650, 670);
    }

    public CeintureTP(Environnement environnement, int x, int y) {
        super(environnement, "Ceinture de Téléportation", x, y);
    }

    public void seTeleporte(Acteur a){
        int rangeTP = 80;
        int dx = 0;
        int dy = 0;

        if (a.direction.get().contains("haut")){
            if (a.getY() - rangeTP > 0){
                dy -= rangeTP;
            }
        }
        if (a.direction.get().contains("bas")){
            if (a.getY() + 16 + rangeTP < this.getEnvironnement().getHeight()){
                dy += rangeTP;
            }
        }
        if (a.direction.get().contains("gauche")){
            if (a.getX() - rangeTP > 0){
                dx -= rangeTP;
            }
        }
        if (a.direction.get().contains("droite")){
            if (a.getX() + 16 + rangeTP < this.getEnvironnement().getWidth()){
                dx += rangeTP;
            }
        }
        a.setX(a.getX() + dx);
        a.setY(getEnvironnement().getJoueur().getY() + dy);
    }


    @Override
    public void utilise(Acteur a) {
        seTeleporte(a);
    }
}
