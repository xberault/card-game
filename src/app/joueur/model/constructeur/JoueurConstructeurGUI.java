package app.joueur.model.constructeur;

import app.joueur.JoueurControlleur;
import app.joueur.JoueurHumain;
import app.joueur.JoueurIA;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.joueur.model.strategie.IStrategieIA;
import app.joueur.model.strategie.StrategieAleatoire;
import app.joueur.model.vue.JoueurVueGUI;
import app.joueur.model.vue.JoueurVueIATXT;

public class JoueurConstructeurGUI implements IJoueurConstructeur {
    @Override
    public JoueurControlleur creerJoueurIA() {
        JoueurControlleur joueurControlleur = new JoueurControlleur();

        IStrategieIA strategieIA = new StrategieAleatoire(); // TODO: 08/11/2021 Gérer la stratégie que l'IA doit adopter

        JoueurIA joueurModel = new JoueurIA(strategieIA);
        JoueurVueIATXT joueurVue = new JoueurVueIATXT(joueurModel);

        joueurModel.ajouterObserverEtat(joueurControlleur);

        joueurControlleur.setModel(joueurModel);
        joueurControlleur.setVue(joueurVue);

        return joueurControlleur;
    }

    @Override
    public JoueurControlleur creerJoueurHumain() {
        return null;
    }

    public JoueurControlleur creerJoueurHumain(String nom) {
        // TODO: 08/11/2021
        JoueurControlleur joueurControlleur = new JoueurControlleur();
        JoueurModel joueurModel = new JoueurHumain(nom);
        IJoueurVue joueurVue = new JoueurVueGUI(joueurModel);

        joueurControlleur.setModel(joueurModel);
        joueurControlleur.setVue(joueurVue);

        joueurModel.ajouterObserverEtat(joueurControlleur);

        return joueurControlleur;
    }

}
