package app.model.action.action2;

import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.Action2;

/**
 * Choix d'identité pour l'utilisateur
 * Objet utilisé : un Role pour déterminer celui de l'utilisateur
 */
public class ChoisirIdentite extends Action2 {
    public ChoisirIdentite(JoueurModel joueur) {
        super(joueur, "choisir son identité");
    }

    @Override
    public void executerAction(Object cible) {
        Role identite = (Role) cible;
        super.joueur.changerRole(identite);
    }
}
