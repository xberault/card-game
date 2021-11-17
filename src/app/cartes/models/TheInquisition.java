package app.cartes.models;

import app.cartes.CarteRumeur;
import app.model.Couleur;

public class TheInquisition extends CarteRumeur {

    private static final String descriptionHunt = "Vous choisissez le prochain joueur\nDécouvrez son identité";
    private static final String descriptionWitch = "Défaussez une carte de votre main\nVous jouez le prochain tour";

    public TheInquisition() {
        super("L'inquisition", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.YELLOW;
    }
}
