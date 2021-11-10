package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.Action;
import app.model.ActionNonJouableException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EtatTourDeJeu)) return false;
        EtatTourDeJeu that = (EtatTourDeJeu) o;
        return Objects.equals(joueur, that.joueur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joueur);
    }

    @Override
    public String toString() {
        return "EtatTourDeJeu{}";
    }
}
