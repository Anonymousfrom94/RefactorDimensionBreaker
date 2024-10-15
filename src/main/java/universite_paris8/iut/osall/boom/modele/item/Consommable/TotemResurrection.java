package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class TotemResurrection extends Consommable {



    public TotemResurrection(Joueur joueur, int x, int y) {
        super(joueur, "Totem de résurection", x, y);
    }

    @Override
    public void utilise(Acteur a) {
        a.getPV().setPv(a.getPV().getPvMax());
    }
}
