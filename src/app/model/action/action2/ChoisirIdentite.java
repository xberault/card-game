package app.model.action.action2;

import app.joueur.model.JoueurModel;
import app.model.action.Action2;

/**
 * Choix d'identité pour l'utilisateur
 * Objet utilisé : un Role pour déterminer celui de l'utilisateur
 */
public class ChoisirIdentite extends Action2 {
    public ChoisirIdentite(JoueurModel joueur) {
        super(joueur);
    }

    @Override
    protected void executerAction(Object cible) {
        // TODO: 16/11/2021
    }
}
