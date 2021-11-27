package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.ActionNonJouableException;
import app.model.action.IAction;
import app.model.action.action2.Accusation;
import app.model.action.action2.JouerCarteHunt;

import java.util.Objects;

public class EtatTourDeJeu implements IEtat {

    private final JoueurModel joueur;

    public EtatTourDeJeu(JoueurModel joueur) {
        this.joueur = joueur;
    }

    @Override
    public void executerAction(IAction IAction) throws ActionNonJouableException {
        this.verifieJouable(IAction);

        // TODO: 09/11/2021 implémentation de l'action

    }

    @Override
    public IAction[] getActionsDisponibles() {
        return new IAction[]{
                new JouerCarteHunt(joueur), new Accusation(joueur)
        };
    }

    @Override
    public IEtat getProchainEtat() {
        return new EtatAttente(this.joueur);
    }

    @Override
    public JoueurModel getJoueur() {
        return this.joueur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EtatTourDeJeu that)) return false;
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
