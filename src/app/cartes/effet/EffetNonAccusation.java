package app.cartes.effet;

import app.joueur.model.JoueurModel;
import app.joueur.model.etat.EtatAccusation;
import app.joueur.model.etat.IEtat;

/**
 * Le joueur cible ne devra être accusé par le joueur émetteur s'il a d'autres choix
 */
public class EffetNonAccusation extends Effet2 {

    public EffetNonAccusation(JoueurModel jCible, JoueurModel jEmetteur) {
        super(jCible, jEmetteur);
    }

    @Override
    protected void pActiver(JoueurModel jCible, JoueurModel jEmmetteur) throws NePeutPasEtreAccuseException {
        // TODO: 21/11/2021
    }

    @Override
    public boolean estActivable(IEtat etatAvant, IEtat etatNouveau) {
        // l'etat doit être une accusation et le joueur de l'accusation le joueur ciblé par l'effet
        return etatNouveau instanceof EtatAccusation && jEmmeteur.equals(etatNouveau.getJoueur());
    }
}
