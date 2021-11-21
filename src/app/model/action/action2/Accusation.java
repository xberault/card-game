package app.model.action.action2;

import app.joueur.model.JoueurModel;
import app.joueur.model.etat.EtatAccusation;
import app.model.action.Action2;

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
        jCible.changerEtat(new EtatAccusation(jCible), jCible, super.joueur);
    }
}
