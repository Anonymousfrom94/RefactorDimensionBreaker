package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class PotionHeal extends Consommable {
    private static final int val_soin = 90;
    private static final int val_pvMax = 300;

    public PotionHeal(Joueur joueur) {
        super(joueur, "Potion de Soin");
    }

    public PotionHeal(Joueur joueur, int x, int y) {
        super(joueur, "Potion de Soin", x, y);
    }

    @Override
    public void utilise() {
        soin(val_soin, val_pvMax);
    }
}
