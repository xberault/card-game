package app.joueur.model.vue;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.IAction;

public class JoueurVueGUI implements IJoueurVue {

    /**
     * Joueur représenté par la vue
     */
    private final JoueurModel joueur;

    public JoueurVueGUI(JoueurModel joueurModel){
        this.joueur=joueurModel;
    }

    @Override
    public Role demanderIdentite() {
        return null;
    }

    @Override
    public IAction demanderTourDeJeu(IAction[] actionsDisponibles) {
        return null;
    }

    @Override
    public IAction repondreAccusation(IAction[] actionsDisponibles) {
        return null;
    }

    @Override
    public void faireAttendre() {

    }

    @Override
    public void afficherCartes(CarteRumeur[] cartes) {

    }

    @Override
    public CarteRumeur demanderCarte(CarteRumeur[] cartes) {
        return null;
    }

    @Override
    public JoueurModel demanderCibleAccusation() {
        return null;
    }

    @Override
    public void afficherJoueurs() {

    }

    @Override
    public void informerErreur(String msgErreur) {

    }

    @Override
    public void finirTour() {

    }

    @Override
    public JoueurControlleur demanderProchainJoueur() {
        return null;
    }

    @Override
    public void afficherProchainJoueurTour(JoueurModel joueur) {

    }

    @Override
    public CarteRumeur demanderDefausseCarte() {
        return null;
    }

    @Override
    public CarteRumeur demanderRepriseCartePersonnelle(CarteRumeur[] lesCartesDisponibles) {
        return null;
    }

    @Override
    public CarteRumeur demanderRepriseCarteJoueur(CarteRumeur[] lesCartesDisponibles) {
        return null;
    }

    @Override
    public void afficherRoleJoueur(JoueurModel joueur) {

    }
}
