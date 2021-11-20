package app.model.action.action2;

import app.joueur.model.JoueurModel;
import app.model.action.Action2;

/**
 * Joue l'effet witch d'une carte
 * Objet utilisé: une carte rumeur
 */
public class JouerCarteWitch extends Action2 {
    public JouerCarteWitch(JoueurModel joueur) {
        super(joueur, "jouer l'effet witch d'une carte");
    }

    @Override
    public void executerAction(Object cible) {
        // TODO: 16/11/2021
    }
}
