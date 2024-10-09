package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Item;

public abstract class Consommable extends Item {

    public Consommable(Joueur joueur, String nom, int x, int y) {
        super(joueur.getEnvironnement(), nom, x, y);
    }

    public Consommable(Joueur joueur, String nom) {
        super(joueur.getEnvironnement(), nom);
    }

//    public void retirerDeLInventaire() { //a voir plus tard
//        joueur.getInventaire().remove(this);
//    }
}
