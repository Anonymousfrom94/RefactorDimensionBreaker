package universite_paris8.iut.osall.boom.modele.Utilitaire;

public class Hitbox {

    public int getHitbox;
    private double hauteur;
    private double largeur;

    public Hitbox(double hauteur, double largeur) {
        setHitbox(hauteur, largeur);
    }

    //Methode créer en dehors du constructeur pour gerer les exception (sera coder plus tard)
    private void setHitbox(double hauteur, double largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    private double getHauteur(){
        return hauteur;
    }
    private double getLargeur(){
        return largeur;
    }

    public double getPointLePlusAGauche(int x) {
        
    }

    public double getPointLePlusADroite(int x) {
    }

    public double getPointLePlusEnHaut(int y) {
    }

    public double getPointLePlusEnBas(int y) {
    }
}
