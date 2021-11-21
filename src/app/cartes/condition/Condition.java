package app.cartes.condition;

import app.joueur.model.JoueurModel;

import java.util.Objects;

// TODO: 21/11/2021 possibilité d'implémenter les conditions en singleton mais pas important
public abstract class Condition {
    /**
     * Texte décrivant les conditions à respecter pour la condition
     */
    private final String description;


    protected Condition(String description) {
        this.description = description;
    }


    /**
     * Indique si la condition est remplie pour le joueur
     *
     * @param joueur le joueur à qui s'applique la condition
     * @return un booléen qui indique l'état de la condition
     */
    public abstract boolean estActivable(JoueurModel joueur);


    @Override
    public String toString() {
        return "Condition préalable: " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Condition)) return false;
        Condition condition = (Condition) o;
        return description.equals(condition.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }


}
