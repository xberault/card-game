package app.joueur;

import app.Jeu;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.joueur.model.etat.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class JoueurControlleur implements PropertyChangeListener {

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

    /**
     * Permet de commencer le tour du joueur
     * Est utile au début de la partie
     */
    public void commencerTour() {
        this.model.changerEtat(new EtatChoixIdentite(this.model));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Jeu.printd("Changement d'etat: ");
        IEtat etat = (IEtat) evt.getNewValue();
        Jeu.printd("Nouvel etat: " + etat.toString());
        if (EtatChoixIdentite.class.equals(etat.getClass())) {
            this.gererChoixIdentite();
        } else if (EtatAttente.class.equals(etat.getClass())) {
            this.gererAttente();
        } else if (EtatAccusation.class.equals(etat.getClass())) {
            this.gererAccusation();
        } else if (EtatTourDeJeu.class.equals(etat.getClass())) {
            this.gererTourDeJeu();
        } else {
            System.out.println("JoueurControlleur.propertyChange etat: " + etat.getClass().getName() + " n'est pas implémenté");
        }
        // TODO: 09/11/2021 Implémenter les actions dans des méthodes individuelles

    }

    private void gererChoixIdentite() {
        Jeu.printd("Le joueur va choisir son identité");
    }

    private void gererAttente() {
        Jeu.printd("Le joueur est en train d'attendre");
    }

    private void gererAccusation() {
        Jeu.printd("Le joueur vient d'être accusé");
    }

    private void gererTourDeJeu() {
        Jeu.printd("Le joueur va jouer son tour");
    }
}
