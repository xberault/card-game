package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.cartes.condition.ConditionCarteDefausse;
import app.model.Couleur;

public class BlackCat extends CarteRumeur {
    private static final String descriptionHunt = "Vous reprenez une carte de la défausse. Défaussez Chat noir";
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
        CarteRumeur aReprendre = super.joueur.getJoueurVue().demanderRepriseCartePersonnelle(Jeu.getInstance().getDefausse().toArray(new CarteRumeur[Jeu.getInstance().getDefausse().size()]));
        joueur.getModel().ajouterCarteRumeur(aReprendre);
        joueur.getModel().defausserCarte(this);
    }
}
