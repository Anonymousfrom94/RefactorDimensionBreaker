package universite_paris8.iut.osall.boom.modele.entite.ennemi;
import universite_paris8.iut.osall.boom.modele.DeplacementStrategie.DeplacementEnnemi;
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
        setDeplacementStrategie(new DeplacementEnnemi(this));
    }

    @Override
    public void agit() {
        seDeplace();
        attaque();
    }

    @Override
    public void seDeplace() {
        getDeplacementStrategie().deplacement();

//        Environnement environnement = getEnvironnement();
//        Joueur joueur = environnement.getJoueur();
//
//        int distanceEnX = joueur.getHitbox().getXCentre(joueur.getPosition()) - getHitbox().getXCentre(getPosition());
//        int distanceEnY = joueur.getHitbox().getYCentre(joueur.getPosition()) - getHitbox().getYCentre(getPosition());
//        double distance = Math.sqrt(distanceEnX * distanceEnX + distanceEnY * distanceEnY);
//
//        if (distance <= rangeEnnemmi) {
//
//            int dx;
//            if (distanceEnX == 0) {
//                setDirection(Direction.ARRET);
//            } else if (distanceEnX > 0) {
//                setDirection(Direction.DROITE);
//            } else {
//                setDirection(Direction.GAUCHE);
//            }
//            if (peutSeDeplacer()) {
//                getPosition().setX(getPosition().getX() + getDirection().getX() * getVitesse());
//            }
//
//            int dy;
//            if (distanceEnY == 0) {
//                setDirection(Direction.ARRET);
//            } else if (distanceEnY > 0) {
//                setDirection(Direction.BAS);
//            } else {
//                setDirection(Direction.HAUT);
//            }
//            if (peutSeDeplacer()) {
//                getPosition().setY(getPosition().getY() + getDirection().getY() * getVitesse());
//            }
//        }
//            int newX = getPosition().getX() + dx * getVitesse();
//            int newY = getPosition().getY() + dy * getVitesse();
//
//            if (peutSeDeplacerVers(newX, newY)) {
//                getPosition().setX(newX);
//                getPosition().setY(newY);
//            } else {
//                if (distanceEnX != 0) {
//                    newX = getPosition().getX() + dx * getVitesse();
//                    if (peutSeDeplacerVers(newX, getPosition().getY())) {
//                        getPosition().setX(newX);
//                    }
//                }
//                if (distanceEnY != 0) {
//                    newY = getPosition().getY() + dy * getVitesse();
//                    if (peutSeDeplacerVers(getPosition().getX(), newY)) {
//                        getPosition().setY(newY);
//                    }
//                }
//            }

    }

//    public boolean peutSeDeplacer() {
//
//        Hitbox hitbox = getHitbox();
//        boolean libre = true;
//
//        // Calculer la position cible en fonction de la direction et de la vitesse
//        // Position actuelle de l'objet
//        int pX = getPosition().getX()+(getVitesse()*getDirection().getX());
//        int pY = getPosition().getY()+(getVitesse()*getDirection().getY());
//
//        // Récupération de l'environnement et de la carte
//        Environnement e = Environnement.getInstance();
//        Map m = e.getMap();
//
//        // Calcul des coordonnées des coins du hitbox à la nouvelle position
//        int bas = hitbox.getPointLePlusEnBas(new Position(pX, pY));
//        int gauche = hitbox.getPointLePlusAGauche(new Position(pX, pY));
//        int droite = hitbox.getPointLePlusADroite(new Position(pX, pY));
//        int haut = hitbox.getPointLePlusEnHaut(new Position(pX, pY));
//
//        //Limite de la map
//        if (gauche < 0 || droite >= m.getWidth() || haut < 0 || bas >= m.getHeight()) {
//            return false;
//        }
//
//        // Vérification si les positions sont libres
//        boolean positionHautGaucheLibre = m.positionLibre(gauche, haut);
//        boolean positionBasGaucheLibre = m.positionLibre(gauche, bas);
//        boolean positionBasDroiteLibre = m.positionLibre(droite, bas);
//        boolean positionHautDroiteLibre = m.positionLibre(droite, haut);
//
//        if (!positionBasGaucheLibre || !positionBasDroiteLibre || !positionHautDroiteLibre || !positionHautGaucheLibre) {
//            libre = false;
//        }
//
//        return libre;
//
//    }



    private boolean peutAttaquer() {
//        double distance = Math.sqrt(getX() * getX() + getY() * getY());
//        if (super.getArme() != null && distance <= super.getArme().getRange()) {
//            return false;
//        }
        long tempsActuel = System.currentTimeMillis();
        return (tempsActuel - derniereAttaque) >= intervalleAttack;
    }

//    private boolean peutSeDeplacerVers(int newX, int newY) {
//        Environnement environnement = getEnvironnement();
//        Map map = environnement.getMap();
//
//        for (int i = 0; i < environnement.getJoueur().getHitbox().getLargeur(); i++) {
//            for (int j = 0; j < getHitbox().getHauteur(); j++) {
//                int x = newX + i;
//                int y = newY + j;
//                if (x >= 0 && x < environnement.getMap().getWidth() && y >= 0 && y < environnement.getMap().getHeight()) {
//                    int indice = map.indice(x, y);
//                    if (map.estDevantObstacle(indice)) {
//                        return false;
//                    }
//                } else {
//                    return false;
//                }
//            }
//        }
//
//        return true; // Aucun obstacle trouvé, mouvement possible
//    }

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