package app.cartes.effet;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.model.JoueurModel;
import app.joueur.model.etat.EtatTourDeJeu;
import app.joueur.model.etat.IEtat;

/**
 * Représente la prise d'une carte à la cible avant son tour de jeu
 * Le joueur hote peut prendre une carte au joueur cible
 */
public class EffetPriseCarte extends Effet1 {
    public EffetPriseCarte(JoueurModel jCible) {
        super(jCible);
    }


    @Override
    public boolean estActivable(IEtat etatAvant, IEtat etatNouveau) {
        return etatNouveau instanceof EtatTourDeJeu;
    }

    @Override
    protected void pActiver(JoueurModel jCible, JoueurModel jHote) {
        JoueurControlleur jHoteControlleur = JoueurControlleur.getControllerFromModel(jHote);
        CarteRumeur aPrendre = jHoteControlleur.getJoueurVue().demanderRepriseCarte(jCible.getCartesMain());
        aPrendre.changerProprietaire(jHoteControlleur);
    }
}
