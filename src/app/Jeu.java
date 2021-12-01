package app;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.model.ChangementEtatException;
import app.joueur.model.JoueurModel;
import app.joueur.model.etat.EtatAttente;
import app.model.Role;
import app.model.constructeur.JeuConstructeur;
import app.model.constructeur.JeuConstructreurTXT;

import java.io.IOException;
import java.util.*;


public class Jeu {

    /**
     * Instance du jeu
     * Il peut y avoir qu'un seul jeu à la fois
     */
    private static Jeu instance;

    /**
     * Nombre de cartes rumeur au total dans la partie
     */
    private static final int nbCartesRumeur = 12;

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
            Jeu.instance = new Jeu(jeuConstructeur);
        return Jeu.instance;
    }

    /**
     * Permet d'obtenir la partie existante
     * Si elle n'existe pas, elle ne sera pas créée
     *
     * @return Retourne la seule et unique instance de jeu
     */
    public static Jeu getInstance() {
        return Jeu.instance;
    }

    private void setJoueurCourant(JoueurControlleur joueur) {
        this.joueurCourant = joueur;
    }

    public static void main(String[] args) {

        Jeu.DEBUG = Arrays.stream(args).anyMatch("debug"::equalsIgnoreCase);

        Jeu.printd("Le jeu est lancé en mode de débogage");


        Jeu jeu = Jeu.getInstance(new JeuConstructreurTXT());
        jeu.demarrer();
    }

    /**
     * Initialise les joueurs de la partie
     * Permet également de définir le premier joueur
     */
    private void initialiserJoueurs() {
        this.joueurs = this.constructeur.initJoueur();
        this.initialiserCartesJoueurs();

        // on choisit aléatoirement le premier joueur
        int idPremierJoueur = (int) Math.floor((Math.random() * this.joueurs.size()));
        this.setJoueurCourant(this.joueurs.get(idPremierJoueur));

    }

    /**
     * Initialise la nouvelle partie d'un jeu et la démarre
     */
    public void demarrer() {
        this.initialiserDefausse();
        printd("Défausse initialisé");
        this.initialiserJoueurs();
        printd("Les joueurs ont été initialisés");
        this.debutPartie();
    }

    /**
     * Initialise les cartes dans la main des joueurs
     */
    private void initialiserCartesJoueurs() {
        int nbCartes = Jeu.nbCartesRumeur / this.joueurs.size();

        // obligé de transformer en liste puisque les set sont pseudo aléatoire donc on aurait rapidement des redondances
        /**
         * On aurait pu assigner dynamiquement le joueur à la carte, ce qui évite tout ce code
         */

        List<CarteRumeur> lesCartes = new ArrayList<>(CarteRumeur.obtenirToutesLesCartes().stream().toList());
        Collections.shuffle(lesCartes);
        for (int i = 0; i < nbCartes; ++i)
            for (int j = 0; j < this.joueurs.size(); ++j) {
                JoueurControlleur joueur = this.joueurs.get(j);
                CarteRumeur carteRumeur = lesCartes.get(i * this.joueurs.size() + j);
                joueur.getModel().ajouterCarteRumeur(carteRumeur);
                carteRumeur.setJoueur(joueur);
            }

    }

    /**
     * S'occupe que tous les joueurs choisissent leur identité
     */
    private void debutPartie() {
        // Les joueurs sont sélectionnés d'après leur ordre de création
        for (JoueurControlleur j : joueurs) {
            joueurCourant = j;
            j.commencerTour();
        }
        this.mainLoop();
    }

    /**
     * Effectue le tour du joueur courant tant que la partie n'est pas finie
     */
    private void mainLoop() {
        JoueurControlleur joueurCourant;
        boolean changementJoueur = false;
        while (!this.partieFinie()) {
            joueurCourant = this.joueurCourant;
            // on change de joueur uniquement quand le tour du joueur n'a pas provoqué de changement de joueur
            if (changementJoueur)
                this.joueurSuivant(); // TODO: 23/11/2021 que le joueur suivant soit celui accusé
            this.joueurCourant.commencerTour();
            changementJoueur = joueurCourant.equals(this.joueurCourant);
        }
    }

    public boolean partieFinie() {
        int nbReveles = 0;
        for (JoueurControlleur j : this.joueurs) {
            if (j.getModel().getPoints() > 5)
                return true;
            if (j.getModel().estRevele())
                ++nbReveles;
        }
        return nbReveles == (this.joueurs.size() - 1);
    }

    public void finirPartie() {
        Jeu.printd("Partie fini gagnant " + joueurCourant);

        try {
            for (JoueurControlleur j : this.joueurs)
                j.getModel().changerEtat(new EtatAttente(j.getModel()));
            System.in.read();
        } catch (IOException | ChangementEtatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise la défausse de la partie
     */
    private void initialiserDefausse() {
        this.defausse = new ArrayList<>();
    }

    /**
     * Permet d'obtenir tous les joueurs encore présents dans la partie
     * Ceci implique qu'aucune sorcière s'étant révélée se soit présent
     *
     * @return une liste contenant tous ces joueurs non sorcieres révélées
     */
    public List<JoueurControlleur> getJoueursNonSorcieres() {
        List<JoueurControlleur> lesJoueursNonSorciers = new ArrayList<>();
        for (JoueurControlleur j : this.joueurs)
            if (!((j.getModel().estRevele()) && j.getModel().getRole().equals(Role.SORCIERE)))
                lesJoueursNonSorciers.add(j);
        return lesJoueursNonSorciers;
    }

    /**
     * Permet de passer au prochain joueur
     */
    public void joueurSuivant() {

        List<JoueurControlleur> lesJoueursNonSorciers = this.getJoueursNonSorcieres();

        int index = Math.floorMod(lesJoueursNonSorciers.indexOf(this.joueurCourant) + 1, lesJoueursNonSorciers.size());
        this.joueurCourant = lesJoueursNonSorciers.get(index);
        Jeu.printd("Nouveau joueur courant: (" + index + " , " + joueurCourant + " )");
    }

    public JoueurModel getJoueurCourant() {
        return this.joueurCourant.getModel();
    }


    /**
     * Permet d'obtenir les joueurs présents dans la partie
     *
     * @return un tableau contenant ces joueurs
     */
    public JoueurModel[] getLesJoueurs() {
        List<JoueurModel> res = new ArrayList<>();
        for (JoueurControlleur j : this.joueurs)
            res.add(j.getModel());
        return res.toArray(new JoueurModel[0]);
    }

    /**
     * Permet d'obtenir le controlleur de tous les joueurs présents dans la partie
     *
     * @return un tableau contenant tous ces joueurs
     */
    public JoueurControlleur[] getLesJoueursControlleurs() {
        return this.joueurs.toArray(JoueurControlleur[]::new);
    }

    /**
     * Permet d'obtenir le joueur avant celui passé en paramètre
     *
     * @param joueur le joueur en question
     * @return le joueur qui joue avant
     */
    public JoueurControlleur getJoueurAvant(JoueurControlleur joueur) {
        List<JoueurControlleur> lesJoueursNonSorcieres = this.getJoueursNonSorcieres();
        JoueurControlleur prev = null;
        for (JoueurControlleur j : lesJoueursNonSorcieres) {
            if (Objects.nonNull(prev) && joueur.equals(j))
                return prev;
            prev = j;
        }
        // si le joueur n'a pas été trouvé dans la boucle, il s'agit du premier joueur
        return lesJoueursNonSorcieres.get(0);
    }

    /**
     * Permet de choisir manuellement le prochain joueur
     *
     * @param joueur le joueur qui doit jouer son prochain tour
     */
    public void setProchainJoueur(JoueurControlleur joueur) {
        // Doit prendre en compte l'éxécution de #joueurSuivant() à chaque tour
        // on choisit donc le joueur avant lui
        List<JoueurControlleur> lesJoueursNonSorcieres = this.getJoueursNonSorcieres();
        int index = Math.floorMod(lesJoueursNonSorcieres.indexOf(joueur) - 1, lesJoueursNonSorcieres.size());
        this.joueurCourant = lesJoueursNonSorcieres.get(index);
    }
}
