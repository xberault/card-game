package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.cartes.effet.EffetNonAccusation;
import app.joueur.JoueurControlleur;
import app.model.Couleur;

public class EvilEye extends CarteRumeur {

    private static final String descriptionWitch = "Choisissez le prochain joueur\n" +
            "Il devra accuser quelqu'un d'autre que vous - quand possible";

    private static final String descriptionHunt = descriptionWitch;

    public EvilEye() {
        super("Oeil maléfique", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.RED_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        JoueurControlleur cible = super.joueur.getJoueurVue().demanderProchainJoueur();
        super.joueur.getJoueurVue().afficherProchainJoueurTour(cible.getModel());
        Jeu.getInstance().setProchainJoueur(cible);
        cible.getModel().ajouterEffetChangementEtat(
                new EffetNonAccusation(joueur.getModel(), cible.getModel()) // TODO: 21/11/2021 check l'ordre des joueurs
        );
    }

    @Override
    protected void pActiverEffetHunt() {
        this.pActiverEffetWitch();
    }
}
