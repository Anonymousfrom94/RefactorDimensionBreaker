package universite_paris8.iut.osall.boom.modele.entite.ennemi;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.item.Arme.BatonElectrique;

public class Boss extends Ennemi{
    public Boss(Environnement environnement) {
        super(environnement, new Position(700, 1150), 5, 150, new Hitbox(20,20));
        this.setArme(new BatonElectrique(getEnvironnement(), this.getPosition()));
        invoque(environnement);
    }

    public void invoque(Environnement environnement) {
        for (int i = 0; i < 49 ; i++) {
            new Squelette(getEnvironnement(), new Position(environnement));
            // Rajouter les ennemis en fonction
        }
    }
}
