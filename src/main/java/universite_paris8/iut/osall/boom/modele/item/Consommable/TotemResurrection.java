package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class TotemResurrection extends Consommable {

    public TotemResurrection(Joueur joueur, Position position) {
        super(joueur, "Totem de résurection", position);
    }

    @Override
    public int soin() {
        return Integer.MAX_VALUE;
    }

}
