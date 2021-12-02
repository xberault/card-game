package app.cartes.condition;

import app.cartes.CarteRumeur;

/**
 * Impose au joueur de ne pas être la cible de la carte passée en paramètre à la création tant que la carte ayant
 * émise la condition est révélée
 * <p>
 * // TODO: 02/12/2021  @see app.cartes.effet.EffetNonAccusation qui aurait été une meilleure implémentation
 */
public class ConditionNonCible {
    /**
     * Classe représentant le type de carte ne pouvant cibler le joueur
     */
    private final Class<? extends CarteRumeur> carteInterdite;

    /**
     * Carte ayant lancé cette condition
     * <p>
     * La condition est valide tant qu'elle est révélée
     */
    private final CarteRumeur carteSource;

    public ConditionNonCible(Class<? extends CarteRumeur> carteRumeurClass, CarteRumeur carteSource) {
        this.carteInterdite = carteRumeurClass;
        this.carteSource = carteSource;
    }


    /**
     * Permet de savoir si la condition est activable en fonction de la carteRumeur passée en paramètres
     *
     * @param carteRumeur la carte à vérifier
     * @return un booléen à true si la carteRumeur peut-être joué
     */
    public boolean estActivable(CarteRumeur carteRumeur) {
        return !(this.carteInterdite.isInstance(carteRumeur) && this.carteSource.estRevelee());
    }

    public String getDescription() {
        return "ne peut-être la cible de la carte " + this.carteInterdite.getSimpleName();
    }

    @Override
    public String toString() {
        return "ConditionNonCible{" +
                "carteInterdite=" + carteInterdite +
                ", carteSource=" + carteSource +
                '}';
    }
}
