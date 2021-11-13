package app.cartes.models;

import app.cartes.CarteRumeur;

public class Wart extends CarteRumeur {

    private static final String descriptionHunt = "Vous choisissez le prochain joueur";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public Wart() {
        super("Verrue", descriptionHunt, descriptionWitch);
    }
}
