package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.Action;
import app.model.ActionNonJouableException;

public class EtatTourDeJeu implements IEtat {

    private final JoueurModel joueur;

    protected EtatTourDeJeu(JoueurModel joueur) {
        this.joueur = joueur;
    }

    @Override
    public void executerAction(Action action) throws ActionNonJouableException {
        this.verifieJouable(action);

        // TODO: 09/11/2021 implémentation de l'action

    }

    @Override
    public Action[] getActionsDisponibles() {
        return new Action[]{
                Action.JOUERCARTEHUNT, Action.ACCUSATION
        };
    }

    @Override
    public IEtat getProchainEtat() {
        return new EtatAttente(this.joueur);
    }
}
