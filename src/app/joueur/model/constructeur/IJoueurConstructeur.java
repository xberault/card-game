package app.joueur.model.constructeur;

import app.joueur.JoueurControlleur;
import app.joueur.model.strategie.IStrategieIA;

public interface IJoueurConstructeur {

      /**
       * Permet la création d'un joueur IA
       * Détermine également la stratégie à adopter par l'IA
       *
       * @return le joueur nouvellement crée
       */
      JoueurControlleur creerJoueurIA();

      /**
       * Permet la création d'un joueur humain
       * Gère également la demande de son nom
       *
       * @return le joueur nouvellement crée
       */
      JoueurControlleur creerJoueurHumain();
}
