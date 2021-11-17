package app.cartes.models;

import app.cartes.CarteRumeur;
import app.model.Couleur;

public class EvilEye extends CarteRumeur {

    private static final String descriptionWitch = "Choisissez le prochain joueur\n" +
            "Il devra accuser quelqu'un d'autre que vous - quand possible";

    private static final String descriptionHunt = descriptionWitch;

    public EvilEye() {
        super("Oeil maléfique", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.RED_BOLD;
    }
}
