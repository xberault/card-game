package app.cartes.models;

import app.cartes.CarteRumeur;
import app.cartes.condition.Condition;
import app.cartes.condition.ConditionCarteDefausse;
import app.model.Couleur;

public class PointedHat extends CarteRumeur {

    private static final String descriptionHunt = "Remettez une carte de votre défausse dans la main\n" +
            "Vous choisissez le prochain joueur";
    private static final String descriptionWitch = descriptionHunt;

    public PointedHat() {
        super("Chapeau pointu", descriptionHunt, descriptionWitch);
        Condition cdt = new ConditionCarteDefausse();
        super.ajouterConditionHunt(cdt);
        super.ajouterConditionWitch(cdt);
    }

    @Override
    public Couleur getColor() {
        return Couleur.YELLOW_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {

    }

    @Override
    protected void pActiverEffetHunt() {

    }
}
