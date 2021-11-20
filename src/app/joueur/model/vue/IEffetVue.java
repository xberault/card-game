package app.joueur.model.vue;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;

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
     * Permet l'affichage au joueur qu'il jouera effectivement le prochain tour
     */
    void afficherProchainJoueur();

    /**
     * Demande à l'utilisateur quelle carte il souhaite défausser de sa main
     *
     * @return la carte qu'il souhaite défausser
     */
    CarteRumeur demanderDefausseCarte();

    /**
     * Demande à l'utilisateur quelle carte il souhaite parti les cartes défaussées
     * Cette fonction peut être utilisée lorsqu'un joueur reprend des cartes de sa défausse personnelle ou celle d'un autre
     *
     * @param lesCartesDisponibles un tableau contenant toutes les cartes disponibles à la reprise
     * @return la carte qu'il souhaite reprendre
     */
    CarteRumeur demanderRepriseCarte(CarteRumeur[] lesCartesDisponibles);


}
