package app.cartes.condition;

import app.Jeu;
import app.joueur.model.JoueurModel;

/**
 * Le joueur doit posséder au moins une carte dans sa défausse
 */
public class ConditionCarteDefausse extends Condition {
    public ConditionCarteDefausse() {
        super("La défausse contient au moins une carte");
    }

    @Override
    public boolean estActivable(JoueurModel joueur) {
        return Jeu.getInstance().getDefausse().size() > 0;
    }
}
