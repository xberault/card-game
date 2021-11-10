package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.Action;
import app.model.ActionNonJouableException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EtatAccusation)) return false;
        EtatAccusation that = (EtatAccusation) o;
        return Objects.equals(joueur, that.joueur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joueur);
    }

    @Override
    public String toString() {
        return "EtatAccusation{}";
    }
}
