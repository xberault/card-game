package app.cartes.models;

import app.cartes.CarteRumeur;
import app.cartes.condition.ConditionVillageois;
import app.model.Couleur;

public class AngryMob extends CarteRumeur {

    private static final String descriptionHunt = "Révélez l'identité d'un joueur\n" +
            "Sorcière : Vous gagnez 2 points. Vous jouez le prochain tour\n" +
            "Villageois : Vous perdez 2 points. Il joue le prochain tour";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public AngryMob() {
        super("Foule en colère", descriptionHunt, descriptionWitch);
        super.ajouterConditionHunt(new ConditionVillageois());
    }

    @Override
    public Couleur getColor() {
        return Couleur.RED;
    }

    @Override
    protected void pActiverEffetWitch() {

    }

    @Override
    protected void pActiverEffetHunt() {

    }
}
