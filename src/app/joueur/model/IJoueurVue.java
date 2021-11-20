package app.joueur.model;

import app.cartes.CarteRumeur;
import app.joueur.model.vue.IEffetVue;
import app.model.Role;
import app.model.action.Action;


public interface IJoueurVue extends IEffetVue {

    /**
     * Demande à l'utilisateur quelle role il souhaite jouer pour cette partie
     *
     * @return le role choisi
     */
    Role demanderIdentite();

    /**
     * Demande l'action de l'utilisateur pour son tour de jeu
     *
     * @param actionsDisponibles un tableau contenant toutes les actions actuellement réalisables par le joueur
     * @return l'action que le joueur aura choisi
     */
    Action demanderTourDeJeu(Action[] actionsDisponibles);

    /**
     * Demande à l'utilisateur quelle action il souhaite effectuer lors d'une accusation
     *
     * @param actionsDisponibles un tableau contenant toutes les actions actuellement réalisables par le joueur
     * @return l'action que le joueur voudra jouer en réponse à l'accusation
     */
    Action repondreAccusasion(Action[] actionsDisponibles);

    /**
     * TODO
     * A voir pendant l'implémentation, cette méthode ne sera peut-être pas utile
     */
    void faireAttendre();

    /**
     * Affiche les cartes passées en paramètres
     *
     * @param cartes un tableau contenant les cartes à afficher
     */
    void afficherCartes(CarteRumeur[] cartes);

    /**
     * Demande à l'utilisateur la carte qu'il choisi
     *
     * @param cartes un tableau contenant toutes les cartes disponibles au joueur
     * @return la carte sélectionnée par le joueur
     */
    CarteRumeur demanderCarte(CarteRumeur[] cartes);

    /**
     * Demande à l'utilisateur quel joueur il souhaite cibler
     *
     * @return le joueur sélectionné par l'utilisateur
     */
    JoueurModel demanderCibleAccusation();

    /**
     * Permet d'afficher les joueurs présents dans la partie
     */
    void afficherJoueurs();
}
