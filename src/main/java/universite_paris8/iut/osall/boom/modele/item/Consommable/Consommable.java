package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Item;

public abstract class Consommable extends Item {
    protected Joueur joueur;

    public Consommable(Joueur joueur, String nom, int x, int y) {
        super(joueur.getEnvironnement(), nom, x, y);
        this.joueur = joueur;
    }

    public Consommable(Joueur joueur, String nom) {
        super(joueur.getEnvironnement(), nom);
        this.joueur = joueur;
    }

    public abstract void utilise();

    protected void soin(int valeurSoin, int limitePvMax) {
        if (joueur.getPv() + valeurSoin <= limitePvMax) {
            joueur.rajouterPv(valeurSoin);
        } else {
            joueur.setPv(limitePvMax);
        }
        retirerDeLInventaire();
    }

    public void fullSoin() {
        joueur.setPv(joueur.getPvMax());
        retirerDeLInventaire();
    }

    private void retirerDeLInventaire() {
        joueur.getInventaire().remove(this);
    }
}
