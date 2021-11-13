package app.cartes.models;

import app.cartes.CarteRumeur;

public class PetNewt extends CarteRumeur {

    private static final String descriptionHunt = "Prenez une carte dans la défausse de  n'importe quel joueur\n" +
            "Vous choisissez le prochain joueur";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public PetNewt() {
        super("Triton apprivoisé", descriptionHunt, descriptionWitch);
    }
}
