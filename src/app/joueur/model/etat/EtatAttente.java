package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.Action;
import app.model.ActionNonJouableException;

/**
 * Etat dans lequel le joueur ne peut effectuer aucune action ne serait-ce qu'attendre de changer d'état
 */
public class EtatAttente implements IEtat {

    /**
     * Joueur donc on gère l'état
     */
    private final JoueurModel joueur;

    public EtatAttente(JoueurModel joueur) {
        this.joueur = joueur;
    }

    @Override
    public void executerAction(Action action) throws ActionNonJouableException {
        this.verifieJouable(action);

        // TODO: 09/11/2021 Implémenter l'action
    }

    @Override
    public Action[] getActionsDisponibles() {
        return new Action[0];
    }

    @Override
    public IEtat getProchainEtat() {
        return null;
    }

    /**
     * Son prochain état dé
     */
    public IEtat getProchainEtat(Action action) {
        // TODO: 09/11/2021 Pas sûr que ce code soit utile :: possiblement enlever certain constructeurs de protected -> public
        // puisque c'est un getter particulier à l'état d'attente et qui demande une action
        IEtat nouvelEtat;
        switch (action) {
            case ACCUSATION -> nouvelEtat = new EtatAccusation(this.joueur);
            case JOUERCARTEHUNT -> nouvelEtat = new EtatTourDeJeu(this.joueur);
            case CHOISIRIDENTITE -> nouvelEtat = new EtatChoixIdentite(this.joueur);
            case JOUERCARTEWITCH -> nouvelEtat = new EtatAccusation(this.joueur);
            default -> nouvelEtat = this; // ne devrait pas arriver
        }
        System.out.println("Nouvel état pour le joueur: " + nouvelEtat);
        return nouvelEtat;

    }
}
