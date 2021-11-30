package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.model.Couleur;

public class Broomstick extends CarteRumeur {
    private static final String descriptionHunt = """
            Tant que révélé, vous ne pouvez être la cible de Foule en colère
            Vous choisissez le prochain joueur à jouer""";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    // TODO: 20/11/2021  voir comment implémenter cette condition ( sans rajouter un bolléen dans joueurModel..)
    // peut-être creuser en ajouter des conditions d'activation de carte

    public Broomstick() {
        super("Balai", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.GREEN_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        Jeu.getInstance().setProchainJoueur(this.joueur);
        this.joueur.getJoueurVue().afficherProchainJoueurTour(joueur.getModel());
    }

    @Override
    protected void pActiverEffetHunt() {
        // TODO: 20/11/2021 implémentation condition
        JoueurControlleur prochainJoueur = this.joueur.getJoueurVue().demanderProchainJoueur();
        Jeu.getInstance().setProchainJoueur(prochainJoueur);

    }
}
