package app.model.action;

import app.joueur.model.JoueurModel;

/**
 * Action réalisée uniquement par le joueur
 */
public abstract class Action1 implements Action {

    protected JoueurModel joueur;

    protected Action1(JoueurModel joueur) {
        this.joueur = joueur;

    }
}
