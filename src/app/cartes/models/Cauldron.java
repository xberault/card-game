package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.model.Couleur;

public class Cauldron extends CarteRumeur {
    private static final String descriptionHunt = "Révèle votre identité\n" +
            "Sorcière: le joueur avant vous prend le tour" + // c'est normalement celui à sa gauche mais peu implémentable
            "Villageois: vous choisissez le prochain joueur à jouer";
    private static final String descriptionWitch = "Le joueur qui vous a accusé doit défausser une carte aléatoire de sa main\n" +
            "Vous jouez le prochain tour";

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
        this.joueur.getModel().seRevele();
        JoueurControlleur prochainJoueur = null;
        switch (joueur.getModel().getRole()) {
            case SORCIERE -> prochainJoueur = this.joueur; // TODO: 20/11/2021
            case VILLAGEOIS -> prochainJoueur = this.joueur.getJoueurVue().demanderProchainJoueur();
            default -> Jeu.printd("Erreur pActiverEffetHunt() de Cauldron dans switch role joueur");
        }
        Jeu.getInstance().setProchainJoueur(prochainJoueur);
    }
}
