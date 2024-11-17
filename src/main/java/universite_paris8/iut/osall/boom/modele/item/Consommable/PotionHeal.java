package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class PotionHeal extends Consommable {
    private static final int val_soin = 90;

    public PotionHeal(Joueur joueur, Position position) {
        super(joueur, "Potion de Soin", position);
    }

    @Override
    public int soin() {
        return val_soin;
    }
}
