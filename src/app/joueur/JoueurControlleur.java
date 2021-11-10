package app.joueur;

import app.Jeu;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.joueur.model.etat.*;
import app.model.Action;
import app.model.Role;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// TODO: 10/11/2021 gérer si le joueur est une IA ou non pour controller ce qu'on envoie à la vue

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
    /**
     * Lorsque l'état du model change, cet évènement est déclenché
     * On étudie alors la transition entre son ancien et son nouvel état
     */
    public void propertyChange(PropertyChangeEvent evt) {
        Jeu.printd("Changement d'etat: ");
        IEtat etat = (IEtat) evt.getNewValue();
        Jeu.printd("Nouvel etat: " + etat.toString());
        // cette méthode permet d'imposer les actions disponibles au joueur en fonction de son état
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
        Role role = this.vue.demanderIdentite();
        this.model.changerRole(role);
    }

    private void gererAttente() {
        Jeu.printd("Le joueur est en train d'attendre");
        this.vue.faireAttendre();

    }

    private void gererAccusation() {
        Jeu.printd("Le joueur vient d'être accusé");
        // TODO: 10/11/2021 Peut-être créer une classe association(record java14+) pour représenter une accusation entre deux joueurs
        // cette accusation on la ferait Jeu.register(accusation) ? into un jeu.pop(accusation) lors du get
        Action action = this.vue.repondreAccusasion(this.model.getActionsDisponibles());
    }

    /**
     * Transmet l'action soumise par le joueur pour son tour de jeu
     */
    private void gererTourDeJeu() {
        Jeu.printd("Le joueur va jouer son tour");
        Action action = this.vue.demanderTourDeJeu(this.model.getActionsDisponibles());
    }

    /**
     * Permet de savoir si le joueur est controlé par ou IA ou non
     *
     * @return un booléen qui est à vrai lorsque le joueur est humain.
     */
    private boolean estHumain() {
        return this.model instanceof JoueurHumain;
    }

}
