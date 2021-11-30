package app.joueur;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.model.JoueurModel;
import app.joueur.model.strategie.IStrategieIA;
import app.model.Role;
import app.model.action.IAction;
import app.model.action.action2.JouerCarteHunt;
import app.model.action.action2.JouerCarteWitch;

import java.util.Arrays;

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
        CarteRumeur choix = this.strategie.choisirCarte(lesCartesDisponibles);
        if (!this.verifieCarteJouable(choix)) {
            if (lesCartesDisponibles.length == 1)
                return choix; // la carte n'est pas jouable, une exception sera levée plus tard
            return demanderCarte(Arrays.stream(lesCartesDisponibles).filter(carteRumeur -> !carteRumeur.equals(choix)).toArray(CarteRumeur[]::new));
        }
        return choix;

    }

    /**
     * Vérifie que la carte est jouable en fonction
     *
     * @param choix la carte choisie pour être jouée
     * @return un booléen qui est à vrai lorsque la carte est jouable
     */
    private boolean verifieCarteJouable(CarteRumeur choix) {
        IAction[] lesActions = super.getActionsDisponibles();
        boolean estJouable = true;
        for (IAction action : lesActions)
            if (action instanceof JouerCarteHunt)
                estJouable &= choix.effetHuntJouable();
            else if (action instanceof JouerCarteWitch)
                estJouable &= choix.effetWitchJouable();
        return estJouable;
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
