package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class BotteLevitation extends Equipement{
    public BotteLevitation(Environnement environnement,int x,int y) {
        super(environnement, "Botte de Lévitation",x,y);
    }

    @Override
    public void equip(Joueur joueur) {
        joueur.setEquipement(this);
    }

    @Override
    public void utilise(Acteur a) {

    }

}
