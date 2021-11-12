package app.cartes;

import app.cartes.models.AngryMob;
import app.cartes.models.BlackCat;
import app.cartes.models.Broomstick;
import app.cartes.models.Cauldron;
import app.cartes.models.DuckingStool;
import app.cartes.models.EvilEye;
import app.cartes.models.HookedNose;
import app.cartes.models.PetNewt;
import app.cartes.models.PointedHate;
import app.cartes.models.TheInquisition;
import app.cartes.models.Toad;
import app.cartes.models.Wart;
import app.joueur.JoueurControlleur;

import java.util.Set;

public abstract class CarteRumeur {

    /**
     * Joueur qui possède la carte
     */
    protected JoueurControlleur joueur;

    protected CarteRumeur() {
    }

    /**
     * Permet d'obtenir toutes les cartes obtenables dans le jeu
     *
     * @return un ensemble contenant toutes ces cartes
     */
    public static Set<CarteRumeur> obtenirToutesLesCartes() {
        return Set.of(
                new AngryMob(),
                new BlackCat(),
                new Broomstick(),
                new Cauldron(),
                new DuckingStool(),
                new EvilEye(),
                new HookedNose(),
                new PetNewt(),
                new PointedHate(),
                new TheInquisition(),
                new Toad(),
                new Wart()
        );
    }
    // TODO: 12/11/2021 check s'il faut utiliser le controlleur ou le modèle pour les cartes

    public void setJoueur(JoueurControlleur joueur) {
        this.joueur = joueur;
    }
}
