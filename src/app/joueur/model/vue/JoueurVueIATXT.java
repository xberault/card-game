package app.joueur.model.vue;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.JoueurIA;
import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.IAction;
import app.model.constructeur.JeuConstructreurTXT;

/**
 * Cela permet un affichage de l'action effectué par l'ordinateur
 */
public class JoueurVueIATXT extends JoueurVUETXT {

    /**
     * IA représenté dans la vue
     */
    private final JoueurIA ia;

    public JoueurVueIATXT(JoueurIA joueurIA) {
        this.ia = joueurIA;
    }

    @Override
    public Role demanderIdentite() {
        Role identite = this.ia.demanderIdentite();
        System.out.println(JeuConstructreurTXT.gras(this.ia.getNom()) + " vient de choisir son rôle");
        return identite;
    }

    @Override
    public IAction demanderTourDeJeu(IAction[] actionsDisponibles) {
        return this.ia.demanderAction();
    }

    @Override
    public IAction repondreAccusation(IAction[] actionsDisponibles) {
        return this.ia.demanderReponseAccusation(actionsDisponibles);
    }

    @Override
    public CarteRumeur demanderCarte(CarteRumeur[] cartes) {
        CarteRumeur carte = this.ia.demanderCarte(cartes);
        System.out.println("Choix de carte à jouer : " + JeuConstructreurTXT.couleur(carte, carte.getNom()));
        return carte;
    }

    @Override
    public JoueurModel demanderCibleAccusation() {
        JoueurModel cible = this.ia.demanderCibleAccusation(super.getLesJoueursSansCourant());
        super.demanderContinuation();
        return cible;
    }


    @Override
    public JoueurControlleur demanderProchainJoueur() {
        JoueurModel joueurModel = this.ia.demanderProchainJoueur(super.getLesJoueursSansCourant());

        super.afficherProchainJoueurTour(joueurModel);

        return JoueurControlleur.getControllerFromModel(joueurModel);
    }

    @Override
    public CarteRumeur demanderDefausseCarte() {
        CarteRumeur carteRumeur = this.ia.demanderDefausseCarte();
        super.demanderContinuation();
        return carteRumeur;
    }

    @Override
    public CarteRumeur demanderRepriseCarte(CarteRumeur[] lesCartesDisponibles) {
        CarteRumeur carteRumeur = this.ia.demanderRepriseCarte(lesCartesDisponibles);
        super.demanderContinuation();
        return carteRumeur;
    }

}
