package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.model.Couleur;

public class HookedNose extends CarteRumeur {

    private static final String descriptionHunt = "Choisissez le prochain joueur\n" +
            "Avant son tour, prend une carte aléatoire de sa main";
    private static final String descriptionWitch = "Vous prendrez une carte du prochain joueur qui vous accusera\n" +
            "Vous jouez le prochain tour";

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
        // TODO: 20/11/2021 première partie de l'effet
        Jeu.getInstance().setProchainJoueur(this.joueur);
    }

    @Override
    protected void pActiverEffetHunt() {
        JoueurControlleur prochainJoueur = this.joueur.getJoueurVue().demanderProchainJoueur();
        Jeu.getInstance().setProchainJoueur(prochainJoueur);
        // TODO: 20/11/2021 implementer dernière partie de l'effet

    }
}
