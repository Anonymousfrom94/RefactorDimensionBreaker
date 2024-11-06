package universite_paris8.iut.osall.boom.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import universite_paris8.iut.osall.boom.modele.Utilitaire.Direction;
import universite_paris8.iut.osall.boom.modele.entite.Joueur;

import java.util.HashSet;
import java.util.Set;

import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyCode.D;

public class Clavier implements EventHandler<KeyEvent> {

    private Joueur joueur;
    private final Set<KeyCode> touchePress;

    public Clavier(Joueur joueur) {
        this.joueur = joueur;
        this.touchePress = new HashSet<>();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED)
            this.touchePress.add(keyEvent.getCode());
        else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED)
            this.touchePress.remove(keyEvent.getCode());

        String direction = "";
//modif
        if (touchePress.contains(Z)){
            direction += "haut";
            joueur.setDirection(Direction.HAUT);
        }
        if (touchePress.contains(S)){
            direction += "bas";
            joueur.setDirection(Direction.BAS);
        }
        if (touchePress.contains(Q)){
            direction += "gauche";
            joueur.setDirection(Direction.GAUCHE);
        }
        if (touchePress.contains(D)){
            direction += "droite";
            joueur.setDirection(Direction.DROITE);
        }

        this.joueur.getDirection().setDirectionProperty(direction);
        
        if (touchePress.contains(J)){
            this.joueur.attaque();
            touchePress.clear();
        }

        if (touchePress.contains(K) && joueur.getEquipement() != null){
            joueur.getEquipement().utilise(joueur);}}
}
