package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.Action;
import app.model.ActionNonJouableException;

public class EtatChoixIdentite implements IEtat {

    /**
     * Joueur dont on représente l'état
     */
    private final JoueurModel joueur;

    public EtatChoixIdentite(JoueurModel joueur) {
        this.joueur = joueur;
    }

    @Override
    public void executerAction(Action action) throws ActionNonJouableException {
        this.verifieJouable(action);
        this.joueur.changerEtat(this);
        // TODO: 09/11/2021 implémentation de l'action 
    }

    @Override
    public Action[] getActionsDisponibles() {

        return new Action[]{Action.CHOISIRIDENTITE};
    }

    @Override
    public IEtat getProchainEtat() {
        return new EtatAttente(this.joueur);
    }
}
