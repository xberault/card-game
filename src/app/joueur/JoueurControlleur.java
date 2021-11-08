package app.joueur;

import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;

public class JoueurControlleur {

    /**
     * Modèle du joueur
     */
    private JoueurModel model;

    /**
     * Vue du joueur
     */
    private IJoueurVue vue;


    public JoueurControlleur() {
    }

    public void setModel(JoueurModel joueurModel) {
        this.model = joueurModel;
    }

    public void setVue(IJoueurVue vue) {
        this.vue = vue;
    }
}
