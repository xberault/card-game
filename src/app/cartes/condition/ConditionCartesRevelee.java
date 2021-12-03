package app.cartes.condition;

import app.joueur.model.JoueurModel;

/**
 * Le joueur doit posséder au moins une carte dans sa défausse
 */
public class ConditionCartesRevelee extends Condition {
    public ConditionCartesRevelee() {
        super("Avoir au moins une carte révélée");
    }

    @Override
    public boolean estActivable(JoueurModel joueur) {
        return joueur.getCartesRumeursRevelees().length > 0;
    }
}
