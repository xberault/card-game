package app.cartes.effet;

import app.joueur.model.JoueurModel;
import app.joueur.model.etat.IEtat;

// TODO: 21/11/2021 check les noms des vars


/**
 * Effet dans lequel 2 joueurs sont concernés
 * Les 2 joueurs concernés sont connus à l'avance
 *
 * <pre>{@code
 *     Effet2 effet = new Effet2(joueurEnvoyeur, joueurCible);
 * }</pre>
 */
public abstract class Effet2 extends Effet1 {
    /**
     * Joueur ayant activé l'effet
     */
    protected JoueurModel jEmmeteur;

    public Effet2(JoueurModel jCible, JoueurModel jEmetteur) {
        super(jCible);
        this.jEmmeteur = jEmetteur;
    }

    @Override
    public void activer(Object... args) throws NePeutPasEtreAccuseException {
        // les destinataires étant déjà connus, nous n'avons pas besoin de spécifier des conditions dessus
        this.pActiver(this.jCible, this.jEmmeteur);
    }

    @Override
    abstract public boolean estActivable(IEtat etatAvant, IEtat etatNouveau);
}
