package app.joueur;

import app.joueur.model.JoueurModel;
import app.joueur.model.strategie.IStrategieIA;

// TODO: 10/11/2021 implémenter l'IA d'un joueur
// celle-ci utilise une strategie qu'il faut également implémenter

public class JoueurIA extends JoueurModel {

    /**
     * identifiant d'une IA
     */
    private static int ID;

    /**
     * Stratégie que l'IA va adopter durant la partie
     */
    private final IStrategieIA strategie;

    public JoueurIA(IStrategieIA strategieIA) {
        super("IA" + ++JoueurIA.ID);
        this.strategie = strategieIA;
    }
}
