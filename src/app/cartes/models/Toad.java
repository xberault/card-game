package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.model.Couleur;

public class Toad extends CarteRumeur {

    private static final String descriptionHunt = """
            Révélez votre identité
            Sorcière: le joueur avant vous est le prochain joueur
            Villageois: vous choisissez le prochain joueur""";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public Toad() {
        super("crapaud", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.GREEN_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        super.joueur.getJoueurVue().afficherProchainJoueurTour(joueur.getModel());
        Jeu.getInstance().setProchainJoueur(joueur);
    }

    @Override
    protected void pActiverEffetHunt() {
        super.joueur.getModel().seRevele();
        switch (super.joueur.getModel().getRole()) {
            case VILLAGEOIS -> super.choixProchainJoueur();
            case SORCIERE -> {
                JoueurControlleur joueur = Jeu.getInstance().getJoueurAvant(super.joueur);
                Jeu.getInstance().setProchainJoueur(joueur);
                super.joueur.getJoueurVue().afficherProchainJoueurTour(joueur.getModel());
            }
            default -> Jeu.printd("Toad.pActtiverEffetWitch() case default activé avec joueur: " + joueur);
        }
    }
}
