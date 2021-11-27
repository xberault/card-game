package app.joueur.model.etat;

import app.joueur.model.JoueurModel;
import app.model.ActionNonJouableException;
import app.model.action.IAction;

import java.util.Arrays;

/***
 * Représente l'état d'un joueur au cours de la partie
 */
public interface IEtat {
    /**
     * Permet l'exécution d'une action en fonction de l'état du joueur
     *
     * @param IAction l'action que l'on souhaite réaliser
     * @throws ActionNonJouableException au cas où l'action demandée n'est pas réalisable dans son état
     */
    void executerAction(IAction IAction) throws ActionNonJouableException;

    /**
     * Permet d'obtenir toutes les actions actuellement réalisables pour le joueur
     *
     * @return un tableau contenant toutes ces actions
     */
    IAction[] getActionsDisponibles();

    /**
     * Permet d'obtenir le prochain état du joueur
     *
     * @return l'état suivant pour le joueur
     */
    IEtat getProchainEtat();

    /**
     * Permet de vérifier qu'une action est bien réalisable pour l'état donné
     *
     * @param IAction l'action à vérifier
     * @throws ActionNonJouableException au cas où celle-ci n'est pas réalisable
     */
    default void verifieJouable(IAction IAction) throws ActionNonJouableException {
        boolean jouable = Arrays.stream(this.getActionsDisponibles()).toList().contains(IAction);
        if (!jouable)
            throw new ActionNonJouableException();
    }

    /**
     * Permet d'obtenir le joueur correspondant à l'etat
     *
     * @return le joueurmodel représenté par l'état
     */
    JoueurModel getJoueur();

}
