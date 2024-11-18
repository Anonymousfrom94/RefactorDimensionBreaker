package universite_paris8.iut.osall.boom.modele.DeplacementStrategie;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;

import java.util.ArrayList;

public class DeplacementEnnemi implements DeplacementStrategie{

    private Ennemi ennemi;
    
    public DeplacementEnnemi(Ennemi ennemi){
        this.ennemi=ennemi;
    }
    @Override
    public void deplacement() {
        Environnement environnement = ennemi.getEnvironnement();
        Joueur joueur = ennemi.getEnvironnement().getJoueur();

        int distanceEnX = joueur.getHitbox().getXCentre(joueur.getPosition()) - ennemi.getHitbox().getXCentre(ennemi.getPosition());
        int distanceEnY = joueur.getHitbox().getYCentre(joueur.getPosition()) - ennemi.getHitbox().getYCentre(ennemi.getPosition());
        double distance = Math.sqrt(distanceEnX * distanceEnX + distanceEnY * distanceEnY);

        if (distance <= ennemi.getRangeEnnemmi()) {

            if (distanceEnX == 0) {
                ennemi.setDirection(Direction.ARRET);
            } else if (distanceEnX > 0) {
                ennemi.setDirection(Direction.DROITE);
            } else {
                ennemi.setDirection(Direction.GAUCHE);
            }
            if (peutSeDeplacer()) {
                ennemi.getPosition().setX(ennemi.getPosition().getX() + ennemi.getDirection().getX() * ennemi.getVitesse());
            }

            if (distanceEnY == 0) {
                ennemi.setDirection(Direction.ARRET);
            } else if (distanceEnY > 0) {
                ennemi.setDirection(Direction.BAS);
            } else {
                ennemi.setDirection(Direction.HAUT);
            }
            if (peutSeDeplacer()) {
                ennemi.getPosition().setY(ennemi.getPosition().getY() + ennemi.getDirection().getY() * ennemi.getVitesse());
            }

        }
    }

    @Override
    public boolean peutSeDeplacer() {

        Hitbox hitbox = ennemi.getHitbox();
        boolean libre = true;

        // Calculer la position cible en fonction de la direction et de la vitesse
        // Position actuelle de l'objet
        int pX = ennemi.getPosition().getX()+(ennemi.getVitesse()*ennemi.getDirection().getX());
        int pY = ennemi.getPosition().getY()+(ennemi.getVitesse()*ennemi.getDirection().getY());

        // Récupération de l'environnement et de la carte
        Environnement e = Environnement.getInstance();
        Map m = e.getMap();

        // Calcul des coordonnées des coins du hitbox à la nouvelle position
        int bas = hitbox.getPointLePlusEnBas(new Position(pX, pY));
        int gauche = hitbox.getPointLePlusAGauche(new Position(pX, pY));
        int droite = hitbox.getPointLePlusADroite(new Position(pX, pY));
        int haut = hitbox.getPointLePlusEnHaut(new Position(pX, pY));

        //Limite de la map
        if (gauche < 0 || droite >= m.getWidth() || haut < 0 || bas >= m.getHeight()) {
            return false;
        }

        ArrayList<Integer> obstacles = ennemi.getEnvironnement().getObstacles();
        // Vérification si les positions sont libres
        boolean positionHautGaucheLibre = m.positionLibre(gauche, haut,obstacles);
        boolean positionBasGaucheLibre = m.positionLibre(gauche, bas,obstacles);
        boolean positionBasDroiteLibre = m.positionLibre(droite, bas,obstacles);
        boolean positionHautDroiteLibre = m.positionLibre(droite, haut,obstacles);

        if (!positionBasGaucheLibre || !positionBasDroiteLibre || !positionHautDroiteLibre || !positionHautGaucheLibre) {
            libre = false;
        }

        return libre;

    }
}
