package app;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.model.ChangementEtatException;
import app.joueur.model.JoueurModel;
import app.joueur.model.etat.EtatChoixIdentite;
import app.joueur.model.etat.EtatFinManche;
import app.model.constructeur.JeuConstructeur;
import app.model.constructeur.JeuConstructeurGUI;
import app.model.constructeur.JeuConstructreurTXT;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
     * Nombre maximum de points obtenable par une joueur par partie
     * Une fois ce nombre atteint/dépassé, la partie s'arrête
     *
     * @see #finirPartie()
     * @see #finirManche()
     */
    private static final int NB_POINTS_MAX = 5;

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
     * Indique si l'on est actuellement en train de déboguer l'application
     *
     * @see #printd(String)  pour l'utilisation effective de cette fonction
     */
    public static boolean DEBUG = false;

    /**
     * Indique si on utilise l'appilcation en mode interface graphique ou non
     */
    public static boolean GUI = false;

    /**
     * Affiche le string passé en paramètre uniquement si le mode de débogage est activé
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
        Jeu.GUI = Arrays.stream(args).anyMatch("gui"::equalsIgnoreCase);

        Jeu.printd("Le jeu est lancé en mode de débogage");
        Jeu jeu;

        if (Jeu.GUI){
            jeu = Jeu.getInstance(new JeuConstructeurGUI());
            Application.launch(JeuConstructeurGUI.class);
        }
        else{
            jeu = Jeu.getInstance(new JeuConstructreurTXT());
            jeu.demarrer();
        }
        // TODO voir une autre manière de lancer l'application
        // La méthode launch bloquais l'accès à la méthode démarrer
        // J'ai déplacé l'appel de la méthode dans le constructeur du GUI mais doit y avoir un manière moins sale de le faire
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

        // Il y a deux cartes dans la défausse dès le début quand il y a 5 joueurs
        if (this.joueurs.size()==5){
            this.defausse.add(lesCartes.get(lesCartes.size()-1));
            this.defausse.add(lesCartes.get(lesCartes.size()-2));
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

    /**
     * Indique si la manche de jeu actuelle est finie ou non
     * <p>
     * Elle est fini quand : // TODO: 01/12/2021
     *
     * @return un booléen à true quand la manche est finie
     */
    public boolean mancheFinie() {
        int nbReveles = 0;
        for (JoueurControlleur j : this.joueurs) {
            if (j.getModel().getPoints() > 5)
                return true;
            if (j.getModel().estRevele())
                ++nbReveles;
        }
        return nbReveles == (this.joueurs.size() - 1);
    }

    /**
     * Gère la fin d'une manche
     */
    public void finirManche() {
        if (this.partieFinie())
            this.finirPartie();
        this.attributionPointGagnant(this.joueurCourant);
        Jeu.printd("Manche fini gagnant " + joueurCourant);
        this.resetEtatJoueurs();
        // et on est maintenant reparti dans mainLoop

    }

    /**
     * Gère la fin de la partie
     */
    private void finirPartie() {
        Jeu.printd("Partie fini gagnant " + joueurCourant);
    }

    /**
     * Indique si la partie est actuellement finie
     * Ceci est uniquement le cas lorsque un des joueurs possède plus de {@value NB_POINTS_MAX}points
     *
     * @return un booléen à true lorsque la partie est effectivement finie, false sinon
     */
    private boolean partieFinie() {
        return this.joueurs.stream()
                .anyMatch(joueurControlleur -> joueurControlleur.getModel().getPoints() >= Jeu.NB_POINTS_MAX);
    }

    /**
     * Repasse tous les joueurs en état d'attente
     */
    private void resetEtatJoueurs() {
        try {
            for (JoueurControlleur j : this.joueurs) {
                j.getModel().changerEtat(new EtatChoixIdentite(j.getModel()));
                j.getModel().viderCartes();
                j.getModel().viderNonCiblage();
            }
            this.initialiserDefausse();
            this.initialiserCartesJoueurs();

            //noinspection ResultOfMethodCallIgnored
            System.in.read();
        } catch (IOException | ChangementEtatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attribue les points au joueur ayant gagné le round suivant les règles suivantes
     * <p>
     * Son rôle:
     * - Villageois : +1point
     * - Sorcière : +2points
     *
     * @param jGagnant le joueur ayant gagné la manche
     */
    private void attributionPointGagnant(JoueurControlleur jGagnant) {
        int nbPointsGagnes = 0;
        switch (jGagnant.getModel().getRole()) {
            case SORCIERE -> nbPointsGagnes += 2;
            case VILLAGEOIS -> ++nbPointsGagnes;
        }
        jGagnant.getModel().ajouterPoints(nbPointsGagnes);
    }

    /**
     * Initialise la défausse de la partie
     */
    private void initialiserDefausse() {
        this.defausse = new ArrayList<>();
    }

    public List<CarteRumeur> getDefausse(){
        return this.defausse;
    }

    public void ajouterADefausse(CarteRumeur carte){
        this.defausse.add(carte);
    }

    /**
     * Permet d'obtenir tous les joueurs encore présents dans la partie
     * Ceci implique qu'aucune sorcière s'étant révélée se soit présent
     *
     * @return une liste contenant tous ces joueurs non sorcieres révélées
     * @see EtatFinManche
     */
    public List<JoueurControlleur> getJoueursEncorePresents() {
        List<JoueurControlleur> lesJoueursPresents = new ArrayList<>();
        for (JoueurControlleur j : this.joueurs)
            if (!(j.getModel().getEtat() instanceof EtatFinManche))
                //if (!((j.getModel().estRevele()) && j.getModel().getRole().equals(Role.SORCIERE)))
                lesJoueursPresents.add(j);
        return lesJoueursPresents;
    }

    /**
     * Permet de passer au prochain joueur
     */
    public void joueurSuivant() {

        List<JoueurControlleur> lesJoueursPresents = this.getJoueursEncorePresents();

        int index = Math.floorMod(lesJoueursPresents.indexOf(this.joueurCourant) + 1, lesJoueursPresents.size());
        this.joueurCourant = lesJoueursPresents.get(index);
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
        List<JoueurControlleur> lesJoueursNonSorcieres = this.getJoueursEncorePresents();
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
        List<JoueurControlleur> lesJoueursNonSorcieres = this.getJoueursEncorePresents();
        int index = Math.floorMod(lesJoueursNonSorcieres.indexOf(joueur) - 1, lesJoueursNonSorcieres.size());
        this.joueurCourant = lesJoueursNonSorcieres.get(index);
    }

    /**
     * Permet de changer scene de la fenêtre de jeu
     *
     * @param root le fichier fxml charger avec FXMLLoader
     */
    public void changerFenetre(Parent root){
        if (Jeu.GUI){
            ((JeuConstructeurGUI)this.constructeur).changerFenetre(root);
        }
    }
}
