package app.cartes.models;

import app.cartes.CarteRumeur;

public class HookedNose extends CarteRumeur {

    private static final String descriptionHunt = "Choisissez le prochain joueur\n" +
            "Avant son tour, prend une carte aléatoire de sa main";
    private static final String descriptionWitch = "Vous prendrez une carte du prochain joueur qui vous accusera\n" +
            "Vous jouez le prochain tour";

    public HookedNose() {
        super("Nez crochu", descriptionHunt, descriptionWitch);
    }
}
