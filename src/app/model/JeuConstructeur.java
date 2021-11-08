package app.model;

import app.joueur.JoueurControlleur;

import java.util.List;

public interface JeuConstructeur {
    List<JoueurControlleur> initJoueur();

    void demarrerPartie();
}
