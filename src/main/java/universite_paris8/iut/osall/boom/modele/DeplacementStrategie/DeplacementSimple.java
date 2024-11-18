package universite_paris8.iut.osall.boom.modele.DeplacementStrategie;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import java.util.ArrayList;

public class DeplacementSimple implements DeplacementStrategie{
    private Acteur acteur;
    public DeplacementSimple(Acteur a){
        this.acteur = a;
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
        ArrayList<Integer> obstacles = acteur.getEnvironnement().getObstacles();
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
