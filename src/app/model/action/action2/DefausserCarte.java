package app.model.action.action2;

import app.cartes.CarteRumeur;
import app.cartes.EffetNonJouableException;
import app.joueur.model.JoueurModel;
import app.model.action.Action2;

/**
 * Action qui demande au joueur de défausser une de ses cartes
 */
public class DefausserCarte extends Action2 {
    public DefausserCarte(JoueurModel joueur) {
        super(joueur, "défausser une de vos cartes");
    }

    @Override
    public void executerAction(Object cible) throws EffetNonJouableException {
        super.joueur.defausserCarte((CarteRumeur) cible);
    }
}
