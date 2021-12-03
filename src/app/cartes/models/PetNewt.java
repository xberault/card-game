package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.cartes.condition.ConditionCartesReveleeJoueurs;
import app.joueur.model.JoueurModel;
import app.model.Couleur;

import java.util.ArrayList;
import java.util.List;

public class PetNewt extends CarteRumeur {

    private static final String descriptionHunt = """
            Prenez une carte révélée de n'importe quel joueur
            "Vous choisissez le prochain joueur""";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public PetNewt() {
        // TODO: 21/11/2021 ajouter condition que tous les joueurs doivent avoir joué au moins 1 carte
        // cad l'effet hunt ne peut etre jouée en première carte de la partie
        super("Triton apprivoisé", descriptionHunt, descriptionWitch);
        super.ajouterConditionHunt(new ConditionCartesReveleeJoueurs());
    }

    @Override
    public Couleur getColor() {
        return Couleur.CYAN_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        Jeu.getInstance().setProchainJoueur(super.joueur);
        super.joueur.getJoueurVue().afficherProchainJoueurTour(super.joueur.getModel());
    }

    @Override
    protected void pActiverEffetHunt() {
        List<CarteRumeur> lesCartesRevelees = new ArrayList<>();
        JoueurModel[] lesJoueurs = Jeu.getInstance().getLesJoueurs();
        for (JoueurModel joueur : lesJoueurs)
            lesCartesRevelees.addAll(List.of(joueur.getCartesRumeursRevelees()));
        CarteRumeur choix = super.joueur.getJoueurVue().demanderRepriseCartePersonnelle(lesCartesRevelees.toArray(CarteRumeur[]::new));
        choix.changerProprietaire(super.joueur);
        choix.setEstJouee(false);
        super.choixProchainJoueur();
    }
}
