package app.cartes.models;

import app.cartes.CarteRumeur;

public class Toad extends CarteRumeur {

    private static final String descriptionHunt = "Révélez votre identité\n" +
            "Sorcière: le joueur avant vous est le prochain joueur\n" +
            "Villageois: vous choisissez le prochain joueur";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public Toad() {
        super("crapaud", descriptionHunt, descriptionWitch);
    }
}
