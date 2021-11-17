package app.model.action.action2;

import app.joueur.model.JoueurModel;
import app.model.action.Action2;

/**
 * Joue l'effet hunt d'une carte
 * Objet utilisé: une Carte rumeur
 */
public class JouerCarteHunt extends Action2 {

    public JouerCarteHunt(JoueurModel joueur) {
        super(joueur, "jouer l'effet Hunt d'une carte");
    }

    @Override
    protected void executerAction(Object cible) {
        // TODO: 16/11/2021  
    }
}
