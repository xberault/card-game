package app.cartes.condition;

import app.joueur.model.JoueurModel;

/**
 * Condition qui demande au joueur d'avoir joué au moins une carte identité
 */
public class ConditionJoueRumeur extends Condition {

    private static final String description = "vous avez révélé une carte rumeur";

    protected ConditionJoueRumeur(JoueurModel joueur) {
        super(description);
        setJoueur(joueur);
    }

    @Override
    public boolean estActivable() {
        return joueur.getCartesRumeursRevelees().length > 0;
    }
}
