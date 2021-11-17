package app.cartes.models;

import app.cartes.CarteRumeur;
import app.model.Couleur;

public class Broomstick extends CarteRumeur {
    private static final String descriptionHunt = "Tant que révélé, vous ne pouvez être la cible de Foule en colère\n" +
            "Vous choisissez le prochain joueur à jouer";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public Broomstick() {
        super("Balai", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.GREEN_BOLD;
    }
}
