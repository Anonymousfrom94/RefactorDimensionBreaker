package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class CeintureTP extends Equipement{

    public CeintureTP(Environnement environnement, int x, int y) {
        super(environnement, "Ceinture de Téléportation", x, y);
    }

    @Override
    public void utilise(Acteur a) {
        seTeleporte(a);
    }

    public void seTeleporte(Acteur a) {
        int rangeTP = 80;
        int dx = 0, dy = 0;

        String direction = a.direction.get();
        int envHauteur = this.getEnvironnement().getHeight();
        int envLargeur = this.getEnvironnement().getWidth();

        if (direction.contains("haut") && a.getY() - rangeTP > 0) {
            dy -= rangeTP;
        } else if (direction.contains("bas") && a.getY() + 16 + rangeTP < envHauteur) {
            dy += rangeTP;
        } else if (direction.contains("gauche") && a.getX() - rangeTP > 0) {
            dx -= rangeTP;
        } else if (direction.contains("droite") && a.getX() + 16 + rangeTP < envLargeur) {
            dx += rangeTP;
        }

        a.setX(a.getX() + dx);
        a.setY(a.getY() + dy);
    }


}
