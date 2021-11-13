package app.cartes.models;

import app.cartes.CarteRumeur;

public class BlackCat extends CarteRumeur {
    private static final String descriptionHunt = "Vous reprenez une carte de votre défausse. Défaussez BlackCat";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public BlackCat() {
        super("Chat noir", descriptionHunt, descriptionWitch);
    }
}
