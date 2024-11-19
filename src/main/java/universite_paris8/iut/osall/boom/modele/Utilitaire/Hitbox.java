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


    public boolean estAProximité(Position Acteurdepart, Position cible) {
        // 4 coins de l'Acteur de depart
        int limiteGaucheDepart = getPointLePlusAGauche(Acteurdepart);
        int limiteDroiteDepart = getPointLePlusADroite(Acteurdepart);
        int limiteHautDepart = getPointLePlusEnHaut(Acteurdepart);
        int limiteBasDepart = getPointLePlusEnBas(Acteurdepart);

        // 4 coins de la cibles
        int limiteGaucheCible = getPointLePlusAGauche(cible);
        int limiteDroiteCible = getPointLePlusADroite(cible);
        int limiteHautCible = getPointLePlusEnHaut(cible);
        int limiteBasCible = getPointLePlusEnBas(cible);

        // Vérifier si l'un des 4 coins de la cible est dans la hitbox de l'item
        boolean coinGaucheHautDansHitbox = estDansHitbox(limiteGaucheDepart, limiteDroiteDepart, limiteHautDepart, limiteBasDepart, limiteGaucheCible, limiteHautCible);
        boolean coinDroitHautDansHitbox = estDansHitbox(limiteGaucheDepart, limiteDroiteDepart, limiteHautDepart, limiteBasDepart, limiteDroiteCible, limiteHautCible);
        boolean coinGaucheBasDansHitbox = estDansHitbox(limiteGaucheDepart, limiteDroiteDepart, limiteHautDepart, limiteBasDepart, limiteGaucheCible, limiteBasCible);
        boolean coinDroitBasDansHitbox = estDansHitbox(limiteGaucheDepart, limiteDroiteDepart, limiteHautDepart, limiteBasDepart, limiteDroiteCible, limiteBasCible);

        // Si un des coins de la cible est dans la hitbox de l'item, ramasser l'item
        return (coinGaucheHautDansHitbox || coinDroitHautDansHitbox || coinGaucheBasDansHitbox || coinDroitBasDansHitbox);
    }

    private boolean estDansHitbox(int limiteGauche, int limiteDroite, int limiteHaut, int limiteBas, int xCoinCible, int yCoinCible) {
        // Vérifie si les coordonnées du coin de la cible sont dans les limites de la hitbox de l'item
        return (xCoinCible >= limiteGauche && xCoinCible <= limiteDroite) &&
                (yCoinCible >= limiteHaut && yCoinCible <= limiteBas);
    }

    public int getPointLePlusAGauche(Position centre) {
        return centre.getX()+3;
    }

    public int getPointLePlusADroite(Position centre) {
        return centre.getX()-3+largeur;

    }

    public int getPointLePlusEnHaut(Position centre) {
        return centre.getY();

    }

    public int getPointLePlusEnBas(Position centre) {
        return centre.getY()+hauteur;
    }

    public int getXCentre(Position centre) {
        return centre.getX()+largeur/2;
    }
    public int getYCentre(Position centre) {
        return centre.getY()+hauteur/2;
    }
}
