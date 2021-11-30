package app.joueur.model.vue;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.model.JoueurModel;

/**
 * Contient toutes les méthodes susceptibles d'être utilisées par les effets des cartes
 * Et nécessitant un appel à la vue
 */
public interface IEffetVue {

    /**
     * Demande au joueur le prochain joueur qu'il souhaite voir jouer son tour
     *
     * @return le joueur choisi par l'utilisateur
     */
    JoueurControlleur demanderProchainJoueur();

    /**
     * Permet la confirmation que le joueur passé en paramètre sera bien le prochain joueur à jouer
     *
     * @param joueur le joueur dont il sera le tour
     */
    void afficherProchainJoueurTour(JoueurModel joueur);

    /**
     * Demande à l'utilisateur quelle carte il souhaite défausser de sa main
     *
     * @return la carte qu'il souhaite défausser
     */
    CarteRumeur demanderDefausseCarte();

    /**
     * Demande à l'utilisateur quelle carte il souhaite parmi les cartes défaussées
     * Cette fonction peut être utilisée lorsqu'un joueur reprend des cartes de sa défausse personnelle
     *
     * @param lesCartesDisponibles un tableau contenant toutes les cartes disponibles à la reprise
     * @return la carte qu'il souhaite reprendre
     * @see #demanderRepriseCarteJoueur(CarteRumeur[])
     */
    CarteRumeur demanderRepriseCartePersonnelle(CarteRumeur[] lesCartesDisponibles);

    /**
     * Demande à l'utilisateur quelle carte il souhaite parmi les cartes défaussées
     * Cette fonction peut être utilisée lorsqu'un joueur reprend de la défausse d'un autre joueur
     * <p>
     * Le joueur ne voit pas les cartes qui lui sont proposées
     *
     * @param lesCartesDisponibles un tableau contenant toutes les cartes disponibles à la reprise
     * @return la carte qu'il souhaite reprendre
     * @see #demanderRepriseCartePersonnelle(CarteRumeur[])
     */
    CarteRumeur demanderRepriseCarteJoueur(CarteRumeur[] lesCartesDisponibles);

    /**
     * Permet d'afficher le role du joueur passé en paramètre
     * Celui-ci reste confidentiel avec le joueur ayant effectué cette demande
     *
     * @param joueur le joueur dont on souhaite afficher le role
     */
    void afficherRoleJoueur(JoueurModel joueur);


}
