package universite_paris8.iut.osall.boom.modele.entite.ennemi;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.item.Arme.EpeeEnBois;

public class Squelette extends Ennemi {

    public Squelette(Environnement environnement, Position position) {
        super(environnement, position, 3, 100, new Hitbox(16,16));
        setArme(new EpeeEnBois(environnement, getPosition(), this));
    }

}
