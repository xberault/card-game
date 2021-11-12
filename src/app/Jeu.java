package app;

import app.cartes.models.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.model.JeuConstructeur;
import app.model.JeuConstructreurTXT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Jeu {

    /**
     * Instance du jeu
     * Il peut y avoir qu'un seul jeu à la fois
     */
    private static Jeu instance;

    /**
     * Contient toutes les cartes défaussées au cours de la partie
     */
    private List<CarteRumeur> defausse;

    /**
     * Contient tous les joueurs présents pour la partie données
     * Il faut au moins 3 joueurs pour qu'une partie ait lieu
     * 6 joueurs au maximum
     */
    private List<JoueurControlleur> joueurs;

    /**
     * Permet de gérer l'initialisation d'un jeu en fonction des différentes vues possibles:
     * - GUI
     * - Textuelle
     */
    private final JeuConstructeur constructeur;

    /**
     * Représente le joueur dont c'est actuellement le tour de jouer
     */
    private JoueurControlleur joueurCourant;

    /**
     * Indique si l'on est actuelement en train de débuger l'application
     */
    public static boolean DEBUG = false;

    /**
     * Affiche le string passé en paramètre uniquement si le mode de déboggage est activé
     *
     * @param str la chaine de caractère à afficher
     */
    public static void printd(String str) {
        if (DEBUG)
            System.out.println(str);
    }

    private Jeu(JeuConstructeur jeuConstructeur) {
        this.constructeur = jeuConstructeur;
    }

    /**
     * Permet de s'assurer que nous n'avons qu'une seule instance d'un jeu
     *
     * @param jeuConstructeur la manière dont on veut que le jeu soit affiché ( GUI ou Textuelle)
     * @return l'unique instance de jeu
     */
    public static Jeu getInstance(JeuConstructeur jeuConstructeur) {
        if (Objects.isNull(Jeu.instance))
            return new Jeu(jeuConstructeur);
        return Jeu.instance;
    }

    public void setJoueurCourant(JoueurControlleur joueur) {
        this.joueurCourant = joueur;
    }



    /**
     * Initialise les joueurs de la partie
     * Permet également de définir le premier joueur
     */
    private void initialiserJoueurs() {
        this.joueurs = this.constructeur.initJoueur();
        this.setJoueurCourant(this.joueurs.get(0));

    }

    /**
     * Initialise la nouvelle partie d'un jeu et la démarre
     */
    public void demarrer() {
        this.initialiserDefausse();
        printd("Défausse initialisé");
        this.initialiserJoueurs();
        printd("Les joueurs on été initialisés");
        this.debutPartie();
    }

    public static void main(String[] args) {

        Jeu.DEBUG = Arrays.stream(args).anyMatch("debug"::equals);

        Jeu.printd("Le jeu est lancé en mode de débogage");


        Jeu jeu = Jeu.getInstance(new JeuConstructreurTXT());
        jeu.demarrer();
    }

    /**
     * S'occupe que tous les joueurs choisissent leur identité
     */
    private void debutPartie() {
        // Les joueurs sont sélectionnés d'après leur ordre de création
        for (JoueurControlleur j : joueurs)
            j.commencerTour();
        this.mainLoop();
    }

    /**
     * Effectue le tour du joueur courant tant que la partie n'est pas finie
     */
    private void mainLoop() {
        while (!this.partieFinie())
            this.joueurCourant.commencerTour();
    }

    private boolean partieFinie() {
        for (JoueurControlleur j : this.joueurs)
            if (j.getPoints() > 5)
                return true;
        return false;
    }

    /**
     * Initialise la défausse de la partie
     */
    private void initialiserDefausse() {
        this.defausse = new ArrayList<>();
    }

    /**
     * Permet de passer au prochain joueur
     */
    public void joueurSuivant() {
        int index = (this.joueurs.indexOf(this.joueurCourant) + 1) % this.joueurs.size();
        this.joueurCourant = this.joueurs.get(index);
        Jeu.printd("Nouveau joueur courant: (" + index + " ; " + joueurCourant + " )");
    }
}
