package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.ActionNonJouableException;
import app.model.action.IAction;
import app.model.action.action2.Accusation;
import app.model.action.action2.ChoisirIdentite;
import app.model.action.action2.JouerCarteHunt;
import app.model.action.action2.JouerCarteWitch;

import java.util.Objects;

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
    public void executerAction(IAction IAction) throws ActionNonJouableException {
        this.verifieJouable(IAction);

        // TODO: 09/11/2021 Implémenter l'action
    }

    @Override
    public IAction[] getActionsDisponibles() {
        return new IAction[0];
    }

    @Override
    public IEtat getProchainEtat() {
        // TODO: 23/11/2021 peut-être check quand le joueur est accusé ici
        // le prochain etat de l'attente sera toujours l'attente
        // il a besoin d'un input pour en sortir
        return this;
        //return new EtatTourDeJeu(this.joueur);
    }

    @Override
    public JoueurModel getJoueur() {
        return this.joueur;
    }

    /**
     * Son prochain état est déterminé en fonction de l'action qu'il doit effectue
     */
    @Deprecated
    public IEtat getProchainEtat(IAction IAction) {
        // TODO: 09/11/2021 Pas sûr que ce code soit utile :: possiblement enlever certain constructeurs de protected -> public
        // puisque c'est un getter particulier à l'état d'attente et qui demande une action
        IEtat nouvelEtat;
        if (IAction instanceof Accusation) {
            nouvelEtat = new EtatAccusation(this.joueur);
        } else if (IAction instanceof JouerCarteHunt) {
            nouvelEtat = new EtatTourDeJeu(this.joueur);
        } else if (IAction instanceof ChoisirIdentite) {
            nouvelEtat = new EtatChoixIdentite(this.joueur);
        } else if (IAction instanceof JouerCarteWitch) {
            nouvelEtat = new EtatAccusation(this.joueur);
        } else {
            nouvelEtat = this; // ne devrait pas arriver
        }
        System.out.println("Nouvel état pour le joueur: " + nouvelEtat);
        return nouvelEtat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EtatAttente that)) return false;
        return Objects.equals(joueur, that.joueur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joueur);
    }

    @Override
    public String toString() {
        return "EtatAttente{}";
    }
}
