package app.cartes.condition;

import app.joueur.model.JoueurModel;

/**
 * Le joueur doit posséder au moins une carte dans sa défausse
 */
public class ConditionCarteDefausse extends Condition {
    public ConditionCarteDefausse() {
        super("Avoir au moins une carte dans la défausse");
    }

    @Override
    public boolean estActivable(JoueurModel joueur) {
        return joueur.getCartesRumeursRevelees().length > 0;
    }
}
