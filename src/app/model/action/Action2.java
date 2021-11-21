package app.model.action;

import app.cartes.EffetNonJouableException;
import app.joueur.model.JoueurModel;

public abstract class Action2 implements Action {

    /**
     * Cible de l'action à effectuer
     */
    protected Object cible;

    /**
     * Le joueur qui est la source de l'action
     */
    protected JoueurModel joueur;

    /**
     * Le nom décrivant l'action
     */
    private final String nom;

    protected Action2(JoueurModel joueur, String nom) {
        this.joueur = joueur;
        this.nom = nom;
    }

    @Override
    public void executerAction() throws EffetNonJouableException {
        this.executerAction(this.cible);
    }

    public abstract void executerAction(Object cible) throws EffetNonJouableException;

    @Override
    public String toString() {
        return this.nom;
    }
}
