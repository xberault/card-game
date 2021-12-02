package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.cartes.condition.ConditionCarteDefausse;
import app.model.Couleur;

public class BlackCat extends CarteRumeur {
    private static final String descriptionHunt = "Vous reprenez une carte de votre défausse. Défaussez Chat noir";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public BlackCat() {
        super("Chat noir", descriptionHunt, descriptionWitch);
        super.ajouterConditionHunt(new ConditionCarteDefausse());
    }

    @Override
    public Couleur getColor() {
        return Couleur.BLACK_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        Jeu.getInstance().setProchainJoueur(this.joueur);
        this.joueur.getJoueurVue().afficherJoueurs();
    }

    @Override
    protected void pActiverEffetHunt() {
        CarteRumeur[] saDefausse = this.joueur.getModel().getCartesRumeursRevelees();
        CarteRumeur aReprendre = this.joueur.getJoueurVue().demanderRepriseCartePersonnelle(saDefausse);
        aReprendre.setEstJouee(false);
        // pas desoin de la défausser, c'est automatique
    }
}
