package app.joueur.model.vue;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.JoueurIA;
import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.IAction;

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
        return this.ia.demanderIdentite();
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
        return this.ia.demanderCarte(cartes);
    }

    @Override
    public JoueurModel demanderCibleAccusation() {
        return this.ia.demanderCibleAccusation(super.getLesJoueursSansCourant());
    }


    @Override
    public JoueurControlleur demanderProchainJoueur() {
        JoueurModel joueurModel = this.ia.demanderProchainJoueur(super.getLesJoueursSansCourant());
        return JoueurControlleur.getControllerFromModel(joueurModel);
    }

    @Override
    public CarteRumeur demanderDefausseCarte() {
        return this.ia.demanderDefausseCarte();
    }

    @Override
    public CarteRumeur demanderRepriseCarte(CarteRumeur[] lesCartesDisponibles) {
        return this.ia.demanderRepriseCarte(lesCartesDisponibles);
    }

    @Override
    public void afficherRoleJoueur(JoueurModel joueur) {

    }
}
