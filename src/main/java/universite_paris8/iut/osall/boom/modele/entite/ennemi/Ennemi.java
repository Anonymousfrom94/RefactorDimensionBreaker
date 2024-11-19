package universite_paris8.iut.osall.boom.modele.entite.ennemi;
import universite_paris8.iut.osall.boom.modele.DeplacementStrategie.DeplacementEnnemi;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class Ennemi extends Acteur {

    private static final int rangeEnnemmi = 200;
    private long derniereAttaque;
    private static final long intervalleAttack = 1000;

    public Ennemi(Environnement environnement, Position position, int vitesse, int pvMax, Hitbox hitbox) {
        super(environnement, position, Direction.BAS, vitesse, pvMax, hitbox);
        setDeplacementStrategie(new DeplacementEnnemi(this));
    }

    @Override
    public void agit() {
        seDeplace();
        attaque();
    }

    private boolean peutAttaquer() {

        long tempsActuel = System.currentTimeMillis();
        return (tempsActuel - derniereAttaque) >= intervalleAttack;
    }

    @Override
    public void attaque() {
        Environnement environnement = getEnvironnement();
        Joueur joueur = environnement.getJoueur();

        int distanceEnX = joueur.getHitbox().getXCentre(joueur.getPosition()) - getHitbox().getXCentre(getPosition());
        int distanceEnY = joueur.getHitbox().getYCentre(joueur.getPosition()) - getHitbox().getYCentre(getPosition());
        double distance = Math.sqrt(distanceEnX * distanceEnX + distanceEnY * distanceEnY);

        if (peutAttaquer() && super.getArme() != null && distance <= super.getArme().getRange()) {
            super.getArme().utilise(joueur);
            derniereAttaque = System.currentTimeMillis();

        }
    }

    public int getRangeEnnemmi(){
        return rangeEnnemmi;
    }

}