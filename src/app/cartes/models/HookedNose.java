package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.cartes.EffetNonJouableException;
import app.cartes.effet.EffetPriseCarte;
import app.joueur.JoueurControlleur;
import app.joueur.model.etat.EtatAccusation;
import app.joueur.model.etat.IEtat;
import app.model.Couleur;

public class HookedNose extends CarteRumeur {

    private static final String descriptionHunt = """
            Choisissez le prochain joueur
            "Avant son tour, prend une carte aléatoire de sa main""";
    private static final String descriptionWitch = """
            Vous prendrez une carte du joueur qui vous a accusé
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
    protected void pActiverEffetWitch() throws EffetNonJouableException {
        IEtat etatJoueur = super.joueur.getModel().getEtat();
        if (etatJoueur instanceof EtatAccusation) {
            if( ((EtatAccusation) etatJoueur).getJoueurSource().getCartesMain().length == 0 ){
                throw new EffetNonJouableException();
            }
            CarteRumeur cartePrise = super.joueur.getJoueurVue().demanderRepriseCarteJoueur(((EtatAccusation) etatJoueur).getJoueurSource().getCartesMain());
            cartePrise.changerProprietaire(super.joueur);
        }
        Jeu.getInstance().setProchainJoueur(this.joueur);
    }

    @Override
    protected void pActiverEffetHunt() throws EffetNonJouableException {
        JoueurControlleur prochainJoueur = this.joueur.getJoueurVue().demanderProchainJoueur();
        if(prochainJoueur.getModel().getCartesMain().length == 0){
            throw new EffetNonJouableException();
        }
        Jeu.getInstance().setProchainJoueur(prochainJoueur);

        CarteRumeur cartePrise = joueur.getJoueurVue().demanderRepriseCarteJoueur(prochainJoueur.getModel().getCartesMain());
        cartePrise.changerProprietaire(super.joueur);

        joueur.getJoueurVue().afficherProchainJoueurTour(prochainJoueur.getModel());
    }
}
