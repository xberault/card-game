package app.joueur.model;

import app.joueur.model.etat.EtatAttente;
import app.joueur.model.etat.EtatChoixIdentite;
import app.joueur.model.etat.IEtat;
import app.model.Role;

public abstract class JoueurModel {

    /**
     * Désigne le nom du joueur
     */
    private final String nom;

    /**
     * Représente le nombre de points possédés par le joueur
     */
    private int points;

    /**
     * Représente le rôle du joueur pour la partie actuelle
     * la valeur est à role.INDEFINI lorsqu'il n'en a pas encore
     */
    private Role role;

    /**
     * Représente l'état actuel du joueur au cours de la partie
     * Par défaut il est en train d'attendre son tour
     */
    private IEtat etatActuel;

    protected JoueurModel(String nom) {
        this.nom = nom;
        this.role = Role.INDEFINI;
        this.etatActuel = new EtatAttente(this);
    }

    /**
     * Permet de changer l'état actuel du joueur
     * Réveille ensuite tous les threads en attentes
     *
     * @param etat le nouvel etat à mettre
     */
    public void changerEtat(IEtat etat) {
        this.etatActuel = etat;
        notifyAll();
    }

    /**
     * change le role actuel du joueur
     *
     * @param role le nouveau role au joueur
     */
    public void changerRole(Role role) {
        // vérification que le joueur ait la possibilité de changer de role
        if (this.etatActuel instanceof EtatChoixIdentite)
            this.role = role;
    }
}
