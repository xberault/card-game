package app.model.action;

import app.joueur.model.JoueurModel;

/**
 * Action réalisée uniquement par le joueur
 */
public abstract class Action1 implements Action {

    /**
     * Texte qui décrit l'action représentée
     */
    private final String nom;
    /**
     * Le joueur effectuant l'action
     */
    protected JoueurModel joueur;

    protected Action1(JoueurModel joueur, String nom) {
        this.joueur = joueur;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
