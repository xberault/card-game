package app.cartes.condition;

import app.joueur.model.JoueurModel;
import app.model.Role;

public class ConditionVillageois extends Condition {

    private static final String description = "vous avez été révélé en tant que villageois";

    public ConditionVillageois() {
        super(description);
    }

    @Override
    public boolean estActivable(JoueurModel joueur) {
        return joueur.estRevele() && joueur.getRole().equals(Role.VILLAGEOIS);
    }
}
