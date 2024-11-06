package universite_paris8.iut.osall.boom.modele.entite.ennemi;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;
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


    }


    @Override
    public void agit() {
        Environnement environnement = getEnvironnement();
        Joueur joueur = environnement.getJoueur();

        int distanceEnX = joueur.getPosition().getX() - getPosition().getX();
        int distanceEnY = joueur.getPosition().getY() - getPosition().getY();
        double distance = Math.sqrt(distanceEnX * distanceEnX + distanceEnY * distanceEnY);

        if (distance <= rangeEnnemmi) {
            int dx;
            if (distanceEnX == 0) {
                dx = 0;
            } else if (distanceEnX > 0) {
                dx = 1;
            } else {
                dx = -1;
            }

            int dy;
            if (distanceEnY == 0) {
                dy = 0;
            } else if (distanceEnY > 0) {
                dy = 1;
            } else {
                dy = -1;
            }

            int newX = getPosition().getX() + dx * getVitesse();
            int newY = getPosition().getY() + dy * getVitesse();

            if (peutSeDeplacerVers(newX, newY)) {
                getPosition().setX(newX);
                getPosition().setY(newY);
            } else {
                if (distanceEnX != 0) {
                    newX = getPosition().getX() + dx * getVitesse();
                    if (peutSeDeplacerVers(newX, getPosition().getY())) {
                        getPosition().setX(newX);
                    }
                }
                if (distanceEnY != 0) {
                    newY = getPosition().getY() + dy * getVitesse();
                    if (peutSeDeplacerVers(getPosition().getX(), newY)) {
                        getPosition().setY(newY);
                    }
                }
            }

            if (super.getArme() != null && distance <= super.getArme().getRange()) {
                attaque();
            }
        }
    }

    @Override
    public void seDeplace() {

    }

    @Override
    public boolean estDansHitbox() {
        return false;
    }

    private boolean peutAttaquer() {
//        double distance = Math.sqrt(getX() * getX() + getY() * getY());
//        if (super.getArme() != null && distance <= super.getArme().getRange()) {
//            return false;
//        }
        long tempsActuel = System.currentTimeMillis();
        return (tempsActuel - derniereAttaque) >= intervalleAttack;
    }

    private boolean peutSeDeplacerVers(int newX, int newY) {
        Environnement environnement = getEnvironnement();
        Map map = environnement.getMap();

        for (int i = 0; i < environnement.getJoueur().getHitbox().getLargeur(); i++) {
            for (int j = 0; j < getHitbox().getHauteur(); j++) {
                int x = newX + i;
                int y = newY + j;
                if (x >= 0 && x < environnement.getMap().getWidth() && y >= 0 && y < environnement.getMap().getHeight()) {
                    int indice = map.indice(x, y);
                    if (map.estObstacle(indice)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }


        return true; // Aucun obstacle trouvé, mouvement possible
    }

    @Override
    public void attaque() {
//        joueur.enleverPv(super.getArme().getDegat());
        Joueur joueur = getEnvironnement().getJoueur();
        if (peutAttaquer()) {
            super.getArme().utilise(joueur);
            derniereAttaque = System.currentTimeMillis();

        }
    }




}