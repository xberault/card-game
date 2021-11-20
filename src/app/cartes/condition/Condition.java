package app.cartes.condition;

import app.joueur.model.JoueurModel;

import java.util.Objects;

public abstract class Condition {
    /**
     * Texte décrivant les conditions à respecter pour la condition
     */
    private final String description;

    /**
     * Joueur à observer pour savoir si la condition est remplie
     */
    protected JoueurModel joueur;

    protected Condition(String description) {
        this.description = description;
    }

    protected void setJoueur(JoueurModel joueur) {
        this.joueur = joueur;
    }

    /**
     * Indique si la condition est remplie pour le joueur
     *
     * @return un booléen qui indique l'état de la condition
     */
    public abstract boolean estActivable();


    @Override
    public String toString() {
        return "Condition préalable: " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Condition)) return false;
        Condition condition = (Condition) o;
        return description.equals(condition.description) && Objects.equals(joueur, condition.joueur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, joueur);
    }


}
