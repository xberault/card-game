package app.model;

public interface IColorable {
    Couleur getColor();

    /**
     * Permet d'obtenir le String contenant les couleurs désirées pour l'affichage
     *
     * @param aColorise le texte à colorer
     * @return le texte contenant les codes permettant un affichage en couleur
     */
    default String getStringColore(String aColorise) {
        return this.getColor().toString() + aColorise + Couleur.RESET;
    }
}
