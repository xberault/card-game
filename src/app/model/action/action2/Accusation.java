package app.model.action.action2;

import app.Jeu;
import app.joueur.JoueurControlleur;
import app.joueur.model.ChangementEtatException;
import app.joueur.model.JoueurModel;
import app.joueur.model.etat.EtatAccusation;
import app.model.Role;
import app.model.action.Action2;

/**
 * Accusation d'un autre joueur
 * Objet utilisé: Le joueur nouvellement accusé
 * <p>
 * Quand le joueur accusé choisi de révéler son rôle:
 * - Villageois : il joue le prochain tour
 * - Sorcière : Le joueur source gagné 1point
 */
public class Accusation extends Action2 {
    public Accusation(JoueurModel joueur) {
        super(joueur, "accuser un joueur");
    }

    @Override
    public void executerAction(Object cible) {
        JoueurModel jCible = (JoueurModel) cible;
        try {
            jCible.changerEtat(new EtatAccusation(jCible, super.joueur), jCible, super.joueur);
            if (jCible.estRevele()) {
                if (jCible.getRole().equals(Role.SORCIERE)) {
                    this.joueur.ajouterPoints(1);
                    Jeu.getInstance().setProchainJoueur(JoueurControlleur.getControllerFromModel(jCible));
                }
                else
                    Jeu.getInstance().setProchainJoueur(JoueurControlleur.getControllerFromModel(jCible));
            }
        } catch (ChangementEtatException e) {
            // TODO: 23/11/2021
            e.printStackTrace();
//            Objects.requireNonNull(JoueurControlleur.getControllerFromModel(joueur)).commencerTour();
        }
    }
}
