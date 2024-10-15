package universite_paris8.iut.osall.boom.modele.Utilitaire;

public class Hitbox {

    private int hauteur;
    private int largeur;

    public Hitbox(int hauteur, int largeur) {
        setHitbox(hauteur, largeur);
    }

    //Methode créer en dehors du constructeur pour gerer les exception (sera coder plus tard)
    private void setHitbox(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public double getHauteur(){
        return hauteur;
    }
    public double getLargeur(){
        return largeur;
    }

    public double getPointLePlusAGauche(Position centre) {
        return centre.getX()-((double) largeur /2);
    }

    public double getPointLePlusADroite(Position centre) {
        return centre.getX()+((double) largeur /2);
    }

    public double getPointLePlusEnHaut(Position centre) {
        return centre.getY()-((double) hauteur /2);
    }

    public double getPointLePlusEnBas(Position centre) {
        return centre.getY()+((double) hauteur /2);
    }
}
