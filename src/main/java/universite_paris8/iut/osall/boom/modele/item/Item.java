package universite_paris8.iut.osall.boom.modele.item;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;

public abstract class Item {
    private String nom;
    private Environnement environnement;
    private Position position;
    private String id;
    private static int compteur;

    public Item(Environnement environnement, String nom, Position position) {
        this.environnement = environnement;
        this.nom = nom;
        this.position = position;
        this.id = "I" + compteur ;
        compteur++;
    }

    public abstract void utilise(Acteur a);

/* *********************************************************************************************************************
                                          GETTER & SETTER & BOOLEAN
********************************************************************************************************************* */



    public Position getPosition() {
        return position;
    }


    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /* *********************************************************************************************************************

********************************************************************************************************************* */

}
