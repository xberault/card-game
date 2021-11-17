package app.model.action;

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
    public void executerAction() {
        this.executerAction(this.cible);
    }

    protected abstract void executerAction(Object cible);

    @Override
    public String toString() {
        return this.nom;
    }
}
