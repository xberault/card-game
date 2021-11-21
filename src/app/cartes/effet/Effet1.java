package app.cartes.effet;

import app.joueur.model.JoueurModel;

import java.util.Objects;

/**
 * Effet dans lequel 2 joueurs sont concernés
 * Mais un seul joueur est connu à l'avance
 * Le lanceur
 *
 * <pre>{@code
 *     Effet1 effet = new Effet(joueurEnvoyeur);
 * }</pre>
 */
public abstract class Effet1 implements IEffet {

    /**
     * ID générique pour les effets
     */
    private static int EFFET_ID = 0;

    /**
     * identité de l'effet
     * Permet de le hasher efficacement sans se soucier des valeurs nulles
     */
    private final int id;
    /**
     * Représente le joueur cible de l'effet
     */
    protected JoueurModel jCible;

    protected Effet1(JoueurModel jCible) {
        this.jCible = jCible;
        this.id = ++Effet1.EFFET_ID;
    }

    @Override
    public void activer(Object... args) {
        // l'effet n'aurait jamais du etre activé
        if (args.length < 2)
            return;
        this.pActiver(this.jCible, (JoueurModel) args[0]);
    }

    /**
     * Activation de l'effet
     *
     * @param jCible     le joueur recevant l'effet
     * @param jEmmetteur le joueur ayant déclanché l'activation de l'effet
     */
    abstract protected void pActiver(JoueurModel jCible, JoueurModel jEmmetteur);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Effet1)) return false;
        Effet1 effet1 = (Effet1) o;
        return id == effet1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
