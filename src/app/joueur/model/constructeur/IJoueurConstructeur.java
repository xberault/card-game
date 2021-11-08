package app.joueur.model.constructeur;

import app.joueur.JoueurControlleur;
import app.joueur.model.IStrategieIA;

public interface IJoueurConstructeur {
      JoueurControlleur creerJoueurIA(IStrategieIA strategieIA);

      JoueurControlleur creerJoueurHumain();
}
