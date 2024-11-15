package universite_paris8.iut.osall.boom.modele.entite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.osall.boom.modele.Environnement.Environnement;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Hitbox;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Position;
import universite_paris8.iut.osall.boom.modele.entite.ennemi.Ennemi;
import universite_paris8.iut.osall.boom.modele.item.Equipement.BotteLevitation;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    private Joueur joueur;
    private Environnement environnement;

    @BeforeEach //ce qu'on init avant les test
    void setUp() {
        environnement = new Environnement();
        joueur = new Joueur(environnement);
    }

    @Test
    void testSeDeplace() {
        // déplacement normaux
        joueur.setDirection(Direction.HAUT);
        int yInitial = joueur.getPosition().getY();
        joueur.seDeplace();
        assertEquals(yInitial - joueur.getVitesse(), joueur.getPosition().getY());

        joueur.setDirection(Direction.BAS);
        int yAfterUp = joueur.getPosition().getY();
        joueur.seDeplace();
        assertEquals(yAfterUp + joueur.getVitesse(), joueur.getPosition().getY());

        joueur.setDirection(Direction.GAUCHE);
        int xInitial = joueur.getPosition().getX();
        joueur.seDeplace();
        assertEquals(xInitial - joueur.getVitesse(), joueur.getPosition().getX());

        joueur.setDirection(Direction.DROITE);
        int xAfterLeft = joueur.getPosition().getX();
        joueur.seDeplace();
        assertEquals(xAfterLeft + joueur.getVitesse(), joueur.getPosition().getX());

        //déplacement limite map
        joueur.getPosition().setX(0);
        joueur.getPosition().setY(0);
        joueur.setDirection(Direction.HAUT);
        joueur.seDeplace();
        assertEquals(0, joueur.getPosition().getY());

        joueur.setDirection(Direction.GAUCHE);
        joueur.seDeplace();
        assertEquals(0, joueur.getPosition().getX());

        joueur.getPosition().setX(environnement.getMap().getWidth() - joueur.getHitbox().getLargeur());
        joueur.getPosition().setY(environnement.getMap().getHeight() - joueur.getHitbox().getHauteur());
        joueur.setDirection(Direction.BAS);
        joueur.seDeplace();
        assertEquals(environnement.getMap().getHeight() - joueur.getHitbox().getHauteur(), joueur.getPosition().getY());

        joueur.setDirection(Direction.DROITE);
        joueur.seDeplace();
        assertEquals(environnement.getMap().getWidth() - joueur.getHitbox().getLargeur(), joueur.getPosition().getX());
    }

    @Test
    void testEstAttaquable() {
        // ennemie a coté
        Ennemi ennemie = new Ennemi(environnement, new Position(100, 100), 3,1, new Hitbox(16, 16));
        ennemie.getPosition().setX(joueur.getPosition().getX() + 10);
        ennemie.getPosition().setY(joueur.getPosition().getY() + 10);
        environnement.getActeurs().add(ennemie);

        assertNotNull(joueur.chercherActeurAttaquable());

        //pas ennemies
        environnement.getActeurs().remove(ennemie);
        ennemie.getPosition().setX(joueur.getPosition().getX() + 300);
        ennemie.getPosition().setY(joueur.getPosition().getY() + 300);
        environnement.getActeurs().add(ennemie);

        assertNull(joueur.chercherActeurAttaquable());
    }

    @Test
    void testAttaque() {
        //attaque
        Ennemi ennemie = new Ennemi(environnement, new Position(100, 100), 3,1, new Hitbox(16, 16));
        ennemie.getPosition().setX(joueur.getPosition().getX() + 10);
        ennemie.getPosition().setY(joueur.getPosition().getY() + 10);
        environnement.getActeurs().add(ennemie);

        int pvInitial = ennemie.getPV().getPv();
        joueur.attaque();
        assertTrue(ennemie.getPV().getPv() < pvInitial);

        //pas attaquer
        environnement.getActeurs().remove(ennemie);
        ennemie.getPosition().setX(joueur.getPosition().getX() + 300);
        ennemie.getPosition().setY(joueur.getPosition().getY() + 300);
        environnement.getActeurs().add(ennemie);

        pvInitial = ennemie.getPV().getPv();
        joueur.attaque();
        assertEquals(pvInitial, ennemie.getPV().getPv());
    }

    @Test
    void testPeutRamasse() {
        //item proche
//        Item item = new Item(environnement, "TestItem", joueur.getPosition().getX() + 10, joueur.getPosition().getY() + 10);
//        environnement.getInventaireEnvironnement().add(item);
//
//        assertNotNull(joueur.peutRamasse());
//
//        //item loin
//        environnement.getInventaireEnvironnement().remove(item);
//        item.getPosition().setX(joueur.getPosition().getX() + 300);
//        item.getPosition().setY(joueur.getPosition().getY() + 300);
//        environnement.getInventaireEnvironnement().add(item);
//
//        assertNull(joueur.peutRamasse());
    }

    @Test
    void testRamasse() {
        //nouvel item dans l'inventaire
//        Item item = new Item(environnement, "TestItem", joueur.getPosition().getX() + 10, joueur.getPosition().getY() + 10);
//        environnement.getInventaireEnvironnement().add(item);
//
//        joueur.ramasse();
//        assertTrue(joueur.getInventaire().contains(item));
//        assertFalse(environnement.getInventaireEnvironnement().contains(item));
//
//        //aucun changement
//        joueur.getInventaire().remove(item);
//        environnement.getInventaireEnvironnement().add(item);
//        item.getPosition().setX(joueur.getPosition().getX() + 300);
//        item.getPosition().setY(joueur.getPosition().getY() + 300);
//
//        joueur.ramasse();
//        assertFalse(joueur.getInventaire().contains(item));
//        assertTrue(environnement.getInventaireEnvironnement().contains(item));
    }

    @Test
    void testABottesDeLevitation() {
        //a des bottes
        assertFalse(joueur.aBottesDeLevitation());

        BotteLevitation bottes = new BotteLevitation(environnement,new Position(595,670));
        joueur.setEquipement(bottes);

        assertTrue(joueur.aBottesDeLevitation());

        //a pas de botte
        joueur.setEquipement(null);
        assertFalse(joueur.aBottesDeLevitation());
    }
}
