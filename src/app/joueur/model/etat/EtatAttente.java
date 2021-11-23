package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.ActionNonJouableException;
import app.model.action.Action;
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
        // TODO: 23/11/2021 peut-être check quand le joueur est accusé ici
        return new EtatTourDeJeu(this.joueur);
    }

    @Override
    public JoueurModel getJoueur() {
        return this.joueur;
    }

    /**
     * Son prochain état dé
     */
    public IEtat getProchainEtat(Action action) {
        // TODO: 09/11/2021 Pas sûr que ce code soit utile :: possiblement enlever certain constructeurs de protected -> public
        // puisque c'est un getter particulier à l'état d'attente et qui demande une action
        IEtat nouvelEtat;
        if (action instanceof Accusation) {
            nouvelEtat = new EtatAccusation(this.joueur);
        } else if (action instanceof JouerCarteHunt) {
            nouvelEtat = new EtatTourDeJeu(this.joueur);
        } else if (action instanceof ChoisirIdentite) {
            nouvelEtat = new EtatChoixIdentite(this.joueur);
        } else if (action instanceof JouerCarteWitch) {
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
