package universite_paris8.iut.osall.boom.modele.Utilitaire;

public class Hitbox {
    private int hauteur;
    private int largeur;

    public Hitbox(int hauteur, int largeur) {
        setHitbox(hauteur, largeur);
    }

    // Méthode pour définir la hauteur et la largeur de la hitbox
    private void setHitbox(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    // Getters pour la hauteur et la largeur
    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    // Méthode pour vérifier si un point donné (x, y) est contenu dans cette hitbox
    public boolean contient(Position centre, Position cible) {
        int limiteGauche = getPointLePlusAGauche(centre);
        int limiteDroite = getPointLePlusADroite(centre);
        int limiteHaut = getPointLePlusEnHaut(centre);
        int limiteBas = getPointLePlusEnBas(centre);

        // Vérifie si la position cible est dans les limites de la hitbox
        return (cible.getX() >= limiteGauche && cible.getX() <= limiteDroite &&
                cible.getY() >= limiteHaut && cible.getY() <= limiteBas);
    }

    public int getPointLePlusAGauche(Position centre) {
        return (centre.getX()+3) - ( largeur / 2);
    }

    public int getPointLePlusADroite(Position centre) {
        return (centre.getX()-3) + (largeur / 2);

    }

    public int getPointLePlusEnHaut(Position centre) {
        return (centre.getY()+5) - ( hauteur / 2);

    }

    public int getPointLePlusEnBas(Position centre) {
        return (centre.getY()-5) + (hauteur / 2);
    }
}
