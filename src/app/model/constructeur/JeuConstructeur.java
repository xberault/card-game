package app.model.constructeur;

import app.joueur.JoueurControlleur;

import java.util.List;

public interface JeuConstructeur {
    /**
     * Initialise l'entrée les joueurs dans le système de jeu
     *
     * @return la liste des joueurs pour effectuer une partie
     */
    List<JoueurControlleur> initJoueur();

    void demarrerPartie();
}
