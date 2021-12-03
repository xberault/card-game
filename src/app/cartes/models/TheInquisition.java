package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.cartes.condition.ConditionVillageois;
import app.joueur.JoueurControlleur;
import app.model.Couleur;

public class TheInquisition extends CarteRumeur {

    private static final String descriptionHunt = """
            Vous choisissez le prochain joueur
            Découvrez son identité""";
    private static final String descriptionWitch = """
            Défaussez une carte de votre main
            Vous jouez le prochain tour""";

    public TheInquisition() {
        super("L'inquisition", descriptionHunt, descriptionWitch);
        super.ajouterConditionHunt(new ConditionVillageois());
    }

    @Override
    public Couleur getColor() {
        return Couleur.YELLOW;
    }

    @Override
    protected void pActiverEffetWitch() {
        CarteRumeur aDefausse = super.joueur.getJoueurVue().demanderDefausseCarte();
        super.joueur.getModel().defausserCarte(aDefausse);
        Jeu.getInstance().setProchainJoueur(super.joueur);
    }

    @Override
    protected void pActiverEffetHunt() {
        JoueurControlleur cible = joueur.getJoueurVue().demanderProchainJoueur();
        joueur.getJoueurVue().afficherRoleJoueur(cible.getModel());
        Jeu.getInstance().setProchainJoueur(cible);

    }
}
