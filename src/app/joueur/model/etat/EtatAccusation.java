package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.ActionNonJouableException;
import app.model.Role;
import app.model.action.IAction;
import app.model.action.action1.ReleverIdentite;
import app.model.action.action2.JouerCarteWitch;

import java.util.Objects;

/**
 * Lorsque le joueur vient de se faire accuser
 */
public class EtatAccusation implements IEtat {

    /**
     * Joueur ciblé par l'accusation, celui qui est dans cet etat
     */
    private final JoueurModel joueur;

    /**
     * Joueur ayant provoqué l'entré dans cet état de joueur
     */
    private final JoueurModel jSource;

    public EtatAccusation(JoueurModel jCible, JoueurModel jSource) {
        this.joueur = jCible;
        this.jSource = jSource;
    }

    @Override
    public void executerAction(IAction IAction) throws ActionNonJouableException {
        this.verifieJouable(IAction);
        // TODO: 16/11/2021
    }


    @Override
    public IAction[] getActionsDisponibles() {
        if (this.joueur.getCartesMain().length > 0)
            return new IAction[]{
                    new JouerCarteWitch(joueur),
                    new ReleverIdentite(joueur)
            };
        return new IAction[]{
                new ReleverIdentite(joueur)
        };
    }

    @Override
    /**
     * Si c'était une sorcière et qu'il s'est révélé, le joueur se verra finir la manche
     */
    public IEtat getProchainEtat() {
        if (this.joueur.estRevele() && this.joueur.getRole().equals(Role.SORCIERE))
            return new EtatFinManche(this.joueur);
        return new EtatAttente(this.joueur);
    }

    @Override
    public JoueurModel getJoueur() {
        return this.joueur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EtatAccusation that)) return false;
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

    /**
     * Le joueur ayant provoqué l'entré dans l'état d'accusation du joueur
     *
     * @return le model de ce joueur
     */
    public JoueurModel getJoueurSource() {
        return this.jSource;
    }
}
