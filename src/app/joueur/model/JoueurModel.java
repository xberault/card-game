package app.joueur.model;

public abstract class JoueurModel {

    /**
     * Désigne le nom du joueur
     */
    private final String nom;

    /**
     * Représente le nombre de points possédés par le joueur
     */
    private int points;

    protected JoueurModel(String nom) {
        this.nom = nom;
    }
}
