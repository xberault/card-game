package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.model.Couleur;

public class Wart extends CarteRumeur {

    private static final String descriptionHunt = "Vous choisissez le prochain joueur";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public Wart() {
        super("Verrue", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.MAGENTA;
    }

    @Override
    protected void pActiverEffetWitch() {
        super.joueur.getJoueurVue().afficherProchainJoueurTour(joueur.getModel());
        Jeu.getInstance().setProchainJoueur(super.joueur);
    }

    @Override
    protected void pActiverEffetHunt() {
        JoueurControlleur cible = super.joueur.getJoueurVue().demanderProchainJoueur();
        super.joueur.getJoueurVue().afficherProchainJoueurTour(cible.getModel());
        Jeu.getInstance().setProchainJoueur(cible);
    }
}
