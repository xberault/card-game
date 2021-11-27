package app.joueur;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.model.JoueurModel;
import app.joueur.model.strategie.IStrategieIA;
import app.model.Role;
import app.model.action.IAction;

// TODO: 10/11/2021 implémenter l'IA d'un joueur
// celle-ci utilise une strategie qu'il faut également implémenter


public class JoueurIA extends JoueurModel {

    /**
     * identifiant d'une IA
     */
    private static int ID;

    /**
     * Stratégie que l'IA va adopter durant la partie
     */
    private final IStrategieIA strategie;

    public JoueurIA(IStrategieIA strategieIA) {
        super("IA" + ++JoueurIA.ID);
        this.strategie = strategieIA;
    }

    /**
     * Demande l'action à exécuter
     *
     * @return l'action que voudra exécuter l'ordinateur
     */
    public IAction demanderAction() {
        IAction[] actionsDisponibles = this.getActionsDisponibles();
        IAction choix = this.strategie.choisirAction(actionsDisponibles);
        Jeu.printd(super.toString() + "choixAction() resultat: " + choix);
        /*
        try {
            choix.executerAction();
        } catch (EffetNonJouableException e) {
            e.printStackTrace();
        }
        */
        return choix;
    }

    public CarteRumeur demanderCarte(CarteRumeur[] lesCartesDisponibles) {
        return this.strategie.choisirCarte(lesCartesDisponibles);
    }

    public JoueurModel demanderCibleAccusation(JoueurModel[] lesJoueursSansCourant) {
        return this.strategie.demanderCibleAccusation(lesJoueursSansCourant);
    }

    public IAction demanderReponseAccusation(IAction[] actionsDisponibles) {
        return this.strategie.demanderReponseAccusation(actionsDisponibles);
    }

    public JoueurModel demanderProchainJoueur(JoueurModel[] lesJoueursSansCourant) {
        return this.strategie.demanderProchainJoueur(lesJoueursSansCourant);
    }

    public CarteRumeur demanderDefausseCarte() {
        CarteRumeur[] cartesMain = super.getCartesMain();
        return this.strategie.demanderDefausseCarte(cartesMain);
    }

    public CarteRumeur demanderRepriseCarte(CarteRumeur[] lesCartesDisponibles) {
        return this.strategie.demanderRepriseCarte(lesCartesDisponibles);
    }

    public Role demanderIdentite() {
        return this.strategie.demanderIdentite();
    }


}
