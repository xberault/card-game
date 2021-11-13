package app.cartes.condition;

import app.joueur.model.JoueurModel;
import app.model.Role;

public class ConditionVillageois extends Condition {

    private static final String description = "vous avez été révélé en tant que villageois";

    protected ConditionVillageois(JoueurModel joueur) {
        super(description);
        super.setJoueur(joueur);
    }


    @Override
    public boolean estActivable() {
        return super.joueur.estRevele() && super.joueur.getRole().equals(Role.VILLAGEOIS);
    }
}
