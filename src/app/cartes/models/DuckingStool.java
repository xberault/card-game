package app.cartes.models;

import app.cartes.CarteRumeur;
import app.model.Couleur;

public class DuckingStool extends CarteRumeur {

    private static final String descriptionHunt = "Choisissez un joueur. Il doit révéler son identité ou défausser une carte de sa main\n" +
            "Sorcière: vous gagnez 1 point; jouez le prochain tour\n" +
            "Villageois: vous perdez 1 point; il joue le prochain tour\n" +
            "Defausse: il joue le prochain tour";

    // TODO: 20/11/2021 voir comment implémenter ce choix d'action

    private static final String descriptionWitch = "Vous choisissez le prochain joueur";

    public DuckingStool() {
        super("Siège à plongeon", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.BLUE_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        // TODO: 20/11/2021
    }

    @Override
    protected void pActiverEffetHunt() {
        this.pActiverEffetWitch();
    }
}
