package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.cartes.effet.EffetPriseCarte;
import app.joueur.JoueurControlleur;
import app.model.Couleur;

public class HookedNose extends CarteRumeur {

    private static final String descriptionHunt = """
            Choisissez le prochain joueur
            "Avant son tour, prend une carte aléatoire de sa main""";
    private static final String descriptionWitch = """
            Vous prendrez une carte du prochain joueur qui vous accusera
            "Vous jouez le prochain tour""";

    // TODO: 20/11/2021 EFFET GLOBAL à voir comment implémenter

    public HookedNose() {
        super("Nez crochu", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.MAGENTA_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        joueur.getModel().ajouterEffetChangementEtat(new EffetPriseCarte(joueur.getModel()));
        Jeu.getInstance().setProchainJoueur(this.joueur);
    }

    @Override
    protected void pActiverEffetHunt() {
        JoueurControlleur prochainJoueur = this.joueur.getJoueurVue().demanderProchainJoueur();

        Jeu.getInstance().setProchainJoueur(prochainJoueur);

        CarteRumeur cartePrise = joueur.getJoueurVue().demanderRepriseCarteJoueur(prochainJoueur.getModel().getCartesMain());
        cartePrise.changerProprietaire(super.joueur);

        joueur.getJoueurVue().afficherProchainJoueurTour(prochainJoueur.getModel());
    }
}
