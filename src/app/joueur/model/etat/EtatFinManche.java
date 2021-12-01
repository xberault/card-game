package app.joueur.model.etat;

import app.Jeu;
import app.joueur.model.JoueurModel;

/**
 * L'état de fin d'une manche est un cas spéciale de l'état d'attente
 *
 * @see Jeu#getJoueursEncorePresents()
 */
public class EtatFinManche extends EtatAttente {
    public EtatFinManche(JoueurModel joueur) {
        super(joueur);
    }
}
