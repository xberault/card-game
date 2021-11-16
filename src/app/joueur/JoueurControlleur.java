package app.joueur;

import app.Jeu;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.joueur.model.etat.EtatAccusation;
import app.joueur.model.etat.EtatAttente;
import app.joueur.model.etat.EtatChoixIdentite;
import app.joueur.model.etat.EtatTourDeJeu;
import app.joueur.model.etat.IEtat;
import app.model.Role;
import app.model.action.Action;

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
     * Au cas où son rôle est indéfini, il leur fera choisir leur identité
     */
    public void commencerTour() {
        if (this.model.getRole() == Role.INDEFINI)         // TODO: 10/11/2021 CHECK que tout le monde ne joue pas le même role
            this.model.changerEtat(new EtatChoixIdentite(this.model));
        else
            this.model.changerEtat(new EtatTourDeJeu(this.model));
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
        Jeu.printd("Le joueur " + this.model + " va choisir son identité");
        Role role = this.vue.demanderIdentite();
        this.model.changerRole(role);
    }

    private void gererAttente() {
        Jeu.printd("Le joueur " + this.model + "est en train d'attendre");
        this.vue.faireAttendre();

    }

    private void gererAccusation() {
        Jeu.printd("Le joueur " + this.model + " vient d'être accusé");
        // TODO: 10/11/2021 Peut-être créer une classe association(record java14+) pour représenter une accusation entre deux joueurs
        // cette accusation on la ferait Jeu.register(accusation) ? into un jeu.pop(accusation) lors du get
        Action action = this.vue.repondreAccusasion(this.model.getActionsDisponibles());
    }

    /**
     * Transmet l'action soumise par le joueur pour son tour de jeu
     */
    private void gererTourDeJeu() {
        Jeu.printd("Le joueur " + this.model + " va jouer son tour");
        Action action = this.vue.demanderTourDeJeu(this.model.getActionsDisponibles());
        // TODO: 16/11/2021 mettre executer action ici 
        this.vue.afficherCartes(this.model.getCartes());
    }

    /**
     * Permet de savoir si le joueur est controlé par ou IA ou non
     *
     * @return un booléen qui est à vrai lorsque le joueur est humain.
     */
    private boolean estHumain() {
        return this.model instanceof JoueurHumain;
    }

    public int getPoints() {
        return this.model.getPoints();
    }

    public JoueurModel getModel() {
        return model;
    }
}
