package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Item;

public abstract class Consommable extends Item {

    public Consommable(Joueur joueur, String nom, Position position) {
        super(joueur.getEnvironnement(), nom, position);
    }

//    public void retirerDeLInventaire() { //a voir plus tard
//        joueur.getInventaire().remove(this);
//    }
}
