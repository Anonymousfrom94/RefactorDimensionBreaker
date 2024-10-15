package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class PotionHeal extends Consommable {
    private static final int val_soin = 90;

    public PotionHeal(Joueur joueur, int x, int y) {
        super(joueur, "Potion de Soin", x, y);
    }

    @Override
    public void utilise(Acteur a) {
        a.rajouterPv(val_soin);
    }
}
