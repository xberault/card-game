package app.joueur.model.strategie;

import app.cartes.CarteRumeur;
import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.IAction;

/**
 * Nous avons décidé d'implémenter un énorme choix de fonctions ceci permettant le développement d'ia plus développées
 */
public interface IStrategieIA {
    IAction choisirAction(IAction[] actionsDisponibles);

    CarteRumeur choisirCarte(CarteRumeur[] lesCartesDisponibles);

    JoueurModel demanderProchainJoueur(JoueurModel[] lesJoueursSansCourant);

    JoueurModel demanderCibleAccusation(JoueurModel[] lesJoueursSansCourant);

    CarteRumeur demanderDefausseCarte(CarteRumeur[] cartesMain);

    CarteRumeur demanderRepriseCarte(CarteRumeur[] lesCartesDisponibles);

    Role demanderIdentite();

    IAction demanderCibleAccusation(IAction[] actionsDisponibles);

    IAction demanderReponseAccusation(IAction[] actionsDisponibles);
}
