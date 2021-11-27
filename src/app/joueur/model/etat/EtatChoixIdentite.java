package app.joueur.model.etat;

import app.Jeu;
import app.joueur.model.ChangementEtatException;
import app.joueur.model.JoueurModel;
import app.model.ActionNonJouableException;
import app.model.action.IAction;
import app.model.action.action2.ChoisirIdentite;

import java.util.Objects;

public class EtatChoixIdentite implements IEtat {

    /**
     * Joueur dont on représente l'état
     */
    private final JoueurModel joueur;

    public EtatChoixIdentite(JoueurModel joueur) {
        this.joueur = joueur;
    }

    @Override
    public void executerAction(IAction IAction) throws ActionNonJouableException {
        this.verifieJouable(IAction);
        try {
            this.joueur.changerEtat(this);
        } catch (ChangementEtatException e) {
            Jeu.printd("Changement d'etat impossible EtatChoixIdentite.executerAction");
            throw new ActionNonJouableException();
        }
        // TODO: 09/11/2021 implémentation de l'action 
    }

    @Override
    public IAction[] getActionsDisponibles() {

        return new IAction[]{new ChoisirIdentite(joueur)};
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
        if (!(o instanceof EtatChoixIdentite that)) return false;
        return Objects.equals(joueur, that.joueur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joueur);
    }

    @Override
    public String toString() {
        return "EtatChoixIdentite{}";
    }
}
