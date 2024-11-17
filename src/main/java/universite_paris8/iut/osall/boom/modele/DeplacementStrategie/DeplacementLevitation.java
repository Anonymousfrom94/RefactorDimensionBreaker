package universite_paris8.iut.osall.boom.modele.DeplacementStrategie;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

import java.util.ArrayList;

public class DeplacementLevitation implements DeplacementStrategie{
    private Acteur acteur;

    public DeplacementLevitation(Acteur acteur) {
        this.acteur = acteur;
    }

    @Override
    public void deplacement() {
        Direction direction = acteur.getDirection();
        if (peutSeDeplacer()) {
            acteur.getPosition().setX(acteur.getPosition().getX()+(acteur.getVitesse()*acteur.getDirection().getX()));
            acteur.getPosition().setY(acteur.getPosition().getY()+(acteur.getVitesse()*acteur.getDirection().getY()));
        }
    }

    @Override
    public boolean peutSeDeplacer() {

        Hitbox hitbox = acteur.getHitbox();
        boolean libre = true;

        // Calculer la position cible en fonction de la direction et de la vitesse
        // Position actuelle de l'objet
        int pX = acteur.getPosition().getX()+(acteur.getVitesse()*acteur.getDirection().getX());
        int pY = acteur.getPosition().getY()+(acteur.getVitesse()*acteur.getDirection().getY());

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

        ArrayList<Integer> obstaclesSansEau = new ArrayList<>(acteur.getEnvironnement().getObstacles());
        //obstaclesSansEau.remove(0);

        for (int i = 0; i < obstaclesSansEau.size(); i++){
            if (obstaclesSansEau.get(i) == 316){
                obstaclesSansEau.remove(i);
            }
        }

        // Vérification si les positions sont libres
        boolean positionHautGaucheLibre = m.positionLibre(gauche, haut,obstaclesSansEau);
        boolean positionBasGaucheLibre = m.positionLibre(gauche, bas,obstaclesSansEau);
        boolean positionBasDroiteLibre = m.positionLibre(droite, bas,obstaclesSansEau);
        boolean positionHautDroiteLibre = m.positionLibre(droite, haut,obstaclesSansEau);


        if (!positionBasGaucheLibre || !positionBasDroiteLibre || !positionHautDroiteLibre || !positionHautGaucheLibre) {
            libre = false;
        }

        return libre;

    }

}
