package universite_paris8.iut.osall.boom.modele.item.Arme;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.StrategieAttaque.StrategieAttaque;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Item;

public abstract class Arme extends Item {

    private int degat;
    private int range;
    private StrategieAttaque strategieAttaque;

    public Arme(Environnement environnement, String nom, Position position, int degat, int range) {
        super(environnement, nom, position);
        this.degat = degat;
        this.range = range;
    }


    public void equip(Joueur joueur){
        joueur.setArme(this);
    }

    @Override
    public void utilise(Acteur cible){
        strategieAttaque.attaque();
    }

/* *********************************************************************************************************************
                                          GETTER & SETTER & BOOLEAN
********************************************************************************************************************* */
    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public int getRange() {
        return range;
    }

    public void setStrategie(StrategieAttaque strategieAttaque) {
        this.strategieAttaque = strategieAttaque;
    }

    /* *********************************************************************************************************************

********************************************************************************************************************* */
}
