package app.model.action.action2;

import app.joueur.model.JoueurModel;
import app.model.action.Action2;

/**
 * Demande des informations complémentaires sur une carte de jeu
 * Objet utilisé : Une carte de jeu
 */
public class LireDescriptionCarte extends Action2 {
    protected LireDescriptionCarte(JoueurModel joueur) {
        super(joueur);
    }

    @Override
    protected void executerAction(Object cible) {
// TODO: 16/11/2021
    }
}
