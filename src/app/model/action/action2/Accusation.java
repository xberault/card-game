package app.model.action.action2;

import app.joueur.JoueurControlleur;
import app.joueur.model.ChangementEtatException;
import app.joueur.model.JoueurModel;
import app.joueur.model.etat.EtatAccusation;
import app.model.action.Action2;

import java.util.Objects;

/**
 * Accusation d'un autre joueur
 * Objet utilisé: Le joueur nouvellement accusé
 */
public class Accusation extends Action2 {
    public Accusation(JoueurModel joueur) {
        super(joueur, "accuser un joueur");
    }

    @Override
    public void executerAction(Object cible) {
        JoueurModel jCible = (JoueurModel) cible;
        try {
            jCible.changerEtat(new EtatAccusation(jCible), jCible, super.joueur);
        } catch (ChangementEtatException e) {
            // TODO: 23/11/2021
            e.printStackTrace();
            Objects.requireNonNull(JoueurControlleur.getControllerFromModel(joueur)).commencerTour();
        }
    }
}
