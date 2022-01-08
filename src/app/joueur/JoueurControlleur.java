package app.joueur;

import app.Jeu;
import app.cartes.EffetNonJouableException;
import app.joueur.model.ChangementEtatException;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.joueur.model.etat.*;
import app.joueur.model.vue.JoueurVueGUI;
import app.model.Role;
import app.model.action.Action1;
import app.model.action.Action2;
import app.model.action.IAction;
import app.model.action.action2.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class JoueurControlleur implements PropertyChangeListener {

    /**
     * Modèle du joueur
     */
    private JoueurModel model;

    /**
     * Vue du joueur
     */
    private IJoueurVue vue;

    public JoueurControlleur() {
    }

    public void setModel(JoueurModel joueurModel) {
        this.model = joueurModel;
    }

    public void setVue(IJoueurVue vue) {
        this.vue = vue;
    }

    /**
     * Permet de commencer le tour du joueur
     * Au cas où son rôle est indéfini, il leur fera choisir leur identité
     */
    public void commencerTour() {
        try {
            if (this.model.getRole() == Role.INDEFINI)
                this.model.changerEtat(new EtatChoixIdentite(this.model));
            else
                this.model.changerEtat(new EtatTourDeJeu(this.model));
        } catch (ChangementEtatException e) {
            Jeu.printd("Erreur changement d'etat");
            e.printStackTrace();
        }
    }


    @Override
    /**
     * Lorsque l'état du model change, cet évènement est déclenché
     * On étudie alors la transition entre son ancien et son nouvel état
     */
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO: 01/12/2021 peut-être implémenter les pts / un autre attribut pour gérer ceci automatiquement

        Jeu.printd("Changement d'etat: ");
        IEtat nouvelEtat = (IEtat) evt.getNewValue();
        Jeu.printd("Nouvel etat: " + nouvelEtat.toString());
        if (evt.getOldValue() instanceof EtatChoixIdentite) {
            Jeu.printd(model + " a fini de choisir son role");
            return;
        }
        if (nouvelEtat instanceof EtatChoixIdentite)
            this.gererChoixIdentite();
        else if (Jeu.getInstance().mancheFinie()) {
            Jeu.getInstance().finirManche();
            this.vue.finirTour();
            // TODO: 02/12/2021 meilleure gestion fin de partie pour l'informer au joueur 
            return;
        }

        // cette méthode permet d'imposer les actions disponibles au joueur en fonction de son état

        else if (nouvelEtat instanceof EtatAttente) {
            this.gererAttente();
            return;
        } else if (nouvelEtat instanceof EtatAccusation) {
            if (Jeu.GUI && this.vue instanceof JoueurVueGUI){
                ((JoueurVueGUI) this.vue).setAccuser(((EtatAccusation) nouvelEtat).getJoueurSource());
            }
            this.gererAccusation();

        } else if (nouvelEtat instanceof EtatTourDeJeu) {
            this.gererTourDeJeu();
        } else {
            System.out.println("JoueurControlleur.propertyChange etat: " + nouvelEtat.getClass().getName() + " n'est pas implémenté");
        }
        Jeu.printd("fin de tour");
        this.vue.finirTour();
        this.model.prochainEtat();
    }

    private void gererChoixIdentite() {
        Jeu.printd("Le joueur " + this.model + " va choisir son identité");
        Role role = this.vue.demanderIdentite();
        model.changerRole(role);
    }

    private void gererAttente() {
        Jeu.printd("Le joueur " + this.model + "est en train d'attendre");
        this.vue.faireAttendre();
    }

    @Override
    public String toString() {
        return "JoueurControlleur{" +
                "model=" + model +
                ", vue=" + vue +
                '}';
    }

    private void gererAccusation() {
        if (!Jeu.GUI || !(this.vue instanceof JoueurVueGUI)){
            Jeu.printd("Le joueur " + this.model + " vient d'être accusé");

            IAction action = this.vue.repondreAccusation(this.model.getActionsDisponibles());
            try {
                this.gererAction(action);
            } catch (EffetNonJouableException e) {
                vue.informerErreur("Les conditions requises pour jouer cette carte ne sont pas remplies, merci de recommencer votre tour");
                this.gererAccusation();
            }
        }
        else{
            if (this.vue instanceof JoueurVueGUI){
                ((JoueurVueGUI)this.vue).afficherInterface();
                ((JoueurVueGUI)this.vue).afficherActions(this.model.getActionsDisponibles());

            }
        }
    }

    /**
     * Permet d'obtenir le controlleur du modèle passé en paramètre
     *
     * @param model le modèle dont on veut retrouver le controlleur
     * @return le controlleur associé au modèle.
     */
    public static JoueurControlleur getControllerFromModel(JoueurModel model) {
        for (JoueurControlleur jc : Jeu.getInstance().getLesJoueursControlleurs())
            if (jc.getModel().equals(model))
                return jc;
        return null;
    }

    /**
     * Transmet l'action soumise par le joueur pour son tour de jeu
     */
    private void gererTourDeJeu() {
        if (!Jeu.GUI|| !(this.vue instanceof JoueurVueGUI)) {
            Jeu.printd("Le joueur " + this.model + " va jouer son tour");
            IAction IAction = this.vue.demanderTourDeJeu(this.model.getActionsDisponibles());

            try {
                gererAction(IAction);
            } catch (EffetNonJouableException e) {
                // TODO: 21/11/2021 ne pas faire recommencer le tour mais simplement le choix
                vue.informerErreur("Les conditions requises pour jouer cette carte ne sont pas remplies, merci de recommencer votre tour");
                this.gererTourDeJeu();
                return;
            }

            Jeu.getInstance().joueurSuivant();
        }
        else{
            if (this.vue instanceof JoueurVueGUI){
                ((JoueurVueGUI)this.vue).afficherInterface();
                ((JoueurVueGUI)this.vue).afficherActions(this.model.getActionsDisponibles());
            }
        }
    }

//    /**
//     * Permet de savoir si le joueur est controlé par ou IA ou non
//     *
//     * @return un booléen qui est à vrai lorsque le joueur est humain.
//     */
//    @Deprecated
//    private boolean estHumain() {
//        return this.model instanceof JoueurHumain;
//    }

    public JoueurModel getModel() {
        return this.model;
    }

    public IJoueurVue getJoueurVue() {
        return this.vue;
    }

    /**
     * Permet de gérer l'action que le joueur a demandé d'effectuer
     *
     * @param action l'action qu'il a désiré effectué
     * @throws EffetNonJouableException quand sa carte ne remplit pas les conditions nécessaires
     */
    private void gererAction(IAction action) throws EffetNonJouableException {
        Object target = null; // objet cible de l'action

        if (action instanceof Action1)
            action.executerAction();
        else if (action instanceof Action2) {
            this.obtenirSetTargetAction2((Action2) action);
            action.executerAction();
        }

    }

    private void obtenirSetTargetAction2(Action2 action) {
        Object target = null;
        if (action instanceof Accusation) {
            Jeu.printd("Choix d'accuser");
            this.vue.afficherJoueurs();
            target = this.vue.demanderCibleAccusation();
        } else if (action instanceof ChoisirIdentite)
            target = this.vue.demanderIdentite();
        else if (action instanceof DefausserCarte)
            target = this.vue.demanderDefausseCarte();
        else if (action instanceof JouerCarteHunt || action instanceof JouerCarteWitch) {
            this.vue.afficherCartes(this.model.getCartesMain());
            target = this.vue.demanderCarte(this.model.getCartesMain());
        }
        action.setCible(target);
    }

}
