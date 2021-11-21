package app.model.action.action2;

import app.cartes.CarteDejaJoueeException;
import app.cartes.CarteRumeur;
import app.cartes.EffetNonJouableException;
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
    public void executerAction(Object cible) throws EffetNonJouableException {
        CarteRumeur carte = (CarteRumeur) cible;
        try {
            carte.activerEffetHunt();
        } catch (CarteDejaJoueeException e) {
            e.printStackTrace();
        }
    }
}
