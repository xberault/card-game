package app.cartes.models;

import app.cartes.CarteRumeur;

public class PointedHate extends CarteRumeur {

    private static final String descriptionHunt = "Remettez une carte de votre défausse dans la main\n" +
            "Vous choisissez le prochain joueur";
    private static final String descriptionWitch = descriptionHunt;

    public PointedHate() {
        super("Haine ciblée", descriptionHunt, descriptionWitch);
    }
}
