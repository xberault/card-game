package app.joueur.model;

import app.cartes.CarteRumeur;
import app.model.Role;
import app.model.action.Action;


public interface IJoueurVue {

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
}
