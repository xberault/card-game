package app.joueur.vue;

import app.cartes.CarteRumeur;
import app.joueur.model.IJoueurVue;
import app.model.Action;
import app.model.Role;

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
}
