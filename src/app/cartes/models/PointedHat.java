package app.cartes.models;

import app.cartes.CarteRumeur;
import app.cartes.condition.Condition;
import app.cartes.condition.ConditionCartesRevelee;
import app.model.Couleur;

public class PointedHat extends CarteRumeur {

    private static final String descriptionHunt = """
            Remettez une carte que vous avez révélée dans la main
            "Vous choisissez le prochain joueur""";
    private static final String descriptionWitch = descriptionHunt;

    public PointedHat() {
        super("Chapeau pointu", descriptionHunt, descriptionWitch);
        Condition cdt = new ConditionCartesRevelee();
        super.ajouterConditionHunt(cdt);
        super.ajouterConditionWitch(cdt);
    }

    @Override
    public Couleur getColor() {
        return Couleur.YELLOW_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        super.joueur.getJoueurVue().demanderRepriseCartePersonnelle(joueur.getModel().getCartesRumeursRevelees());
        super.choixProchainJoueur();
    }

    @Override
    protected void pActiverEffetHunt() {
        this.pActiverEffetWitch();
    }
}
