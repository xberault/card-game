package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.model.JoueurModel;
import app.model.Couleur;

import java.util.ArrayList;
import java.util.List;

public class PetNewt extends CarteRumeur {

    private static final String descriptionHunt = "Prenez une carte dans la défausse de  n'importe quel joueur\n" +
            "Vous choisissez le prochain joueur";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public PetNewt() {
        super("Triton apprivoisé", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.CYAN_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        Jeu.getInstance().setProchainJoueur(this.joueur);
    }

    @Override
    protected void pActiverEffetHunt() {
        List<CarteRumeur> lesCartesDefausses = new ArrayList<>();
        JoueurModel[] lesJoueurs = Jeu.getInstance().getLesJoueurs();
        for (JoueurModel joueur : lesJoueurs)
            lesCartesDefausses.addAll(List.of(joueur.getCartesRumeursRevelees()));
        CarteRumeur choix = this.joueur.getJoueurVue().demanderRepriseCarte((CarteRumeur[]) lesCartesDefausses.toArray());
        choix.changerProprietaire(this.joueur);
    }
}
