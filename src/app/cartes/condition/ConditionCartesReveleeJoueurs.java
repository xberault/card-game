package app.cartes.condition;

import java.util.ArrayList;
import java.util.Arrays;

import app.Jeu;
import app.joueur.model.JoueurModel;

/**
 * Le joueur doit posséder au moins une carte dans sa défausse
 */
public class ConditionCartesReveleeJoueurs extends Condition {
    public ConditionCartesReveleeJoueurs() {
        super("Il y a au moins une carte révélée par les joueurs");
    }

    @Override
    public boolean estActivable(JoueurModel joueur) {
        boolean auMoinsUneCarteRevelee = false;
        ArrayList<JoueurModel> joueurs = new ArrayList<>(Arrays.asList(Jeu.getInstance().getLesJoueurs()));
        for (JoueurModel j : joueurs) {
            if (j.getCartesRumeursRevelees().length > 0){
                auMoinsUneCarteRevelee = true;
            }            
        }
        return auMoinsUneCarteRevelee;
    }
}
