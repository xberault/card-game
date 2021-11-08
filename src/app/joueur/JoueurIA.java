package app.joueur;

import app.joueur.model.strategie.IStrategieIA;
import app.joueur.model.JoueurModel;

public class JoueurIA extends JoueurModel {

    /**
     * Stratégie que l'IA va adopter durant la partie
     */
    private final IStrategieIA strategie;

    public JoueurIA(IStrategieIA strategieIA) {
        super("ia");
        this.strategie = strategieIA;
    }
}
