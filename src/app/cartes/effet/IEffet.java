package app.cartes.effet;

import app.joueur.model.etat.IEtat;

/**
 * Effet lancé qui aura des effets particuliers sur un/des joueurs
 */
public interface IEffet {

    /**
     * Active les spécificités de l'effet
     *
     * @param args tous les paramètres possibles pour les effets
     */
    void activer(Object... args) throws NePeutPasEtreAccuseException;

    /**
     * Vérifie si les conditions d'activations de l'effet son réunies
     *
     * @param etatAvant   l'ancien état du joueur
     * @param etatNouveau le nouvel etat du joueur
     * @return un booléen étant à vrai lorsque l'effet est activable. Non sinon
     */
    boolean estActivable(IEtat etatAvant, IEtat etatNouveau);
}
