package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.Action;
import app.model.ActionNonJouableException;

public class EtatAccusation implements IEtat {

    private final JoueurModel joueur;

    protected EtatAccusation(JoueurModel joueur) {
        this.joueur = joueur;
    }

    @Override
    public void executerAction(Action action) throws ActionNonJouableException {
        this.verifieJouable(action);
    }

    @Override
    public Action[] getActionsDisponibles() {
        return new Action[]{Action.JOUERCARTEWITCH, Action.RELEVERIDENTITE};
    }

    @Override
    public IEtat getProchainEtat() {
        return new EtatAttente(this.joueur);
    }
}
