package app.joueur.model.etat;

import app.model.Action;
import app.model.ActionNonJouableException;

import java.util.Arrays;

/***
 * Représente l'état d'un joueur au cours de la partie
 */
public interface IEtat {
    /**
     * Permet l'exécution d'une action en fonction de l'état du joueur
     *
     * @param action l'action que l'on souhaite réaliser
     * @throws ActionNonJouableException au cas où l'action demandée n'est pas réalisable dans son état
     */
    void executerAction(Action action) throws ActionNonJouableException;

    /**
     * Permet d'obtenir toutes les actions actuellement réalisables pour le joueur
     *
     * @return un tableau contenant toutes ces actions
     */
    Action[] getActionsDisponibles();

    /**
     * Permet d'obtenir le prochain état du joueur
     *
     * @return l'état suivant pour le joueur
     */
    IEtat getProchainEtat();

    /**
     * Permet de vérifier qu'une action est bien réalisable pour l'état donné
     *
     * @param action l'action à vérifier
     * @throws ActionNonJouableException au cas où celle-ci n'est pas réalisable
     */
    default void verifieJouable(Action action) throws ActionNonJouableException {
        boolean jouable = Arrays.stream(this.getActionsDisponibles()).toList().contains(action);
        if (!jouable)
            throw new ActionNonJouableException();
    }
}
