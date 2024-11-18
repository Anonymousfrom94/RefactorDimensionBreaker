package universite_paris8.iut.osall.boom.modele.item.Equipement;

import universite_paris8.iut.osall.boom.modele.DeplacementStrategie.DeplacementTéleporté;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.Acteur;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

public class CeintureTP extends Equipement{

    public CeintureTP(Environnement environnement, Position position) {
        super(environnement, "Ceinture de Téléportation", position);
    }

    @Override
    public void utilise(Acteur a) {
        a.setDeplacementStrategie(new DeplacementTéleporté(a,50));
    }


}
