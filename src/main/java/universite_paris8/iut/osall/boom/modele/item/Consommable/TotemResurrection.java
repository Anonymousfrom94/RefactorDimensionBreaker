package universite_paris8.iut.osall.boom.modele.item.Consommable;

import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class TotemResurrection extends Consommable{

    private Joueur joueur;

    public TotemResurrection(Joueur joueur, int x, int y) {
        super(joueur.getEnvironnement(), "Totem de résurection", x, y);
        this.joueur = joueur;
    }

    public void utilise(){
        seSoigne();
    }

    public void seSoigne(){
        if (joueur.getPv() < joueur.getPvMax()){
            this.joueur.setPv(joueur.getPvMax());
            this.joueur.getInventaire().remove(this);
        }
    }
}
