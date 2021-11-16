package app.model.action.action2;

import app.joueur.model.JoueurModel;
import app.model.action.Action2;

/**
 * Accusation d'un autre joueur
 * Objet utilisé: Le joueur nouvellement accusé
 */
public class Accusation extends Action2 {
    public Accusation(JoueurModel joueur) {
        super(joueur);
    }

    @Override
    protected void executerAction(Object cible) {
        // TODO: 16/11/2021
    }
}
