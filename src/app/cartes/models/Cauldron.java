package app.cartes.models;

import app.cartes.CarteRumeur;
import app.model.Couleur;

public class Cauldron extends CarteRumeur {
    private static final String descriptionHunt = "Révèle votre identité\n" +
            "Sorcière: le joueur avant vous prend le tour" + // c'est normalement celui à sa gauche mais peu implémentable
            "Villageois: vous choisissez le prochain joueur à jouer";
    private static final String descriptionWitch = "";

    public Cauldron() {
        super("Chaudron", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.MAGENTA;
    }
}
