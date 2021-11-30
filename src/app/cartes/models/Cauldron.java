package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.model.Couleur;

import java.util.Objects;

public class Cauldron extends CarteRumeur {
    // c'est normalement celui à sa gauche mais peu implémentable
    private static final String descriptionHunt = """
            Révèle votre identité
            Sorcière: le joueur avant vous prend le tour
            Villageois: vous choisissez le prochain joueur à jouer""";
    private static final String descriptionWitch = """
            Le joueur qui vous a accusé doit défausser une carte aléatoire de sa main
            Vous jouez le prochain tour""";

    public Cauldron() {
        super("Chaudron", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.MAGENTA;
    }

    @Override
    protected void pActiverEffetWitch() {
        // TODO: 20/11/2021 implémenter 1re partie de l'effet
        Jeu.getInstance().setProchainJoueur(this.joueur);
    }

    @Override
    protected void pActiverEffetHunt() {
        super.joueur.getModel().seRevele();
        JoueurControlleur prochainJoueur = null;
        switch (joueur.getModel().getRole()) {
            case SORCIERE -> prochainJoueur = Jeu.getInstance().getJoueurAvant(super.joueur); // TODO: 20/11/2021
            case VILLAGEOIS -> prochainJoueur = this.joueur.getJoueurVue().demanderProchainJoueur();
            default -> Jeu.printd("Erreur pActiverEffetHunt() de Cauldron dans switch role joueur");
        }
        super.joueur.getJoueurVue().afficherProchainJoueurTour(Objects.requireNonNull(prochainJoueur).getModel());
        Jeu.getInstance().setProchainJoueur(prochainJoueur);
    }
}
