package app.joueur.model.vue;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.Action;

public class JoueurVueGUI implements IJoueurVue {
    @Override
    public Role demanderIdentite() {
        return null;
    }

    @Override
    public Action demanderTourDeJeu(Action[] actionsDisponibles) {
        return null;
    }

    @Override
    public Action repondreAccusasion(Action[] actionsDisponibles) {
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
    public JoueurControlleur demanderProchainJoueur() {
        return null;
    }

    @Override
    public void afficherProchainJoueur() {

    }

    @Override
    public CarteRumeur demanderDefausseCarte() {
        return null;
    }

    @Override
    public CarteRumeur demanderRepriseCarte(CarteRumeur[] lesCartesDisponibles) {
        return null;
    }
}
