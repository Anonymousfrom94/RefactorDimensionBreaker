package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;
import universite_paris8.iut.osall.boom.modele.item.Item;

public abstract class Equipement extends Item {

    public Equipement(Environnement environnement, String nom, Position position) {
        super(environnement, nom, position);
    }

    public void equip(Joueur joueur) {
        joueur.setEquipement(this);
    }

}
