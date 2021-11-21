package app.model.action.action2;

import app.cartes.CarteDejaJoueeException;
import app.cartes.CarteRumeur;
import app.cartes.EffetNonJouableException;
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
        CarteRumeur carte = (CarteRumeur) cible;
        try {
            carte.activerEffetWitch();
        } catch (CarteDejaJoueeException | EffetNonJouableException e) {
            e.printStackTrace();
        }
    }
}
