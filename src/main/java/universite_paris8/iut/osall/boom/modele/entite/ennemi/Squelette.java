package universite_paris8.iut.osall.boom.modele.entite.ennemi;
import java.util.Random;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;

public class Squelette extends Ennemi {

    public Squelette(Environnement environnement, Position position) {
        super(environnement, position, 3, 100, new Hitbox(16,16));
    }



}
