package app.joueur.model.vue;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.cartes.condition.ConditionNonCible;
import app.joueur.JoueurControlleur;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.IAction;
import app.model.constructeur.JeuConstructreurTXT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JoueurVUETXT implements IJoueurVue {

    /**
     * Permet de gérer les entrées sorties avec l'utilisateur
     */
    private final Scanner sc;

    /**
     * Joueur représenté par la vue
     */
    private final JoueurModel joueur;

    public JoueurVUETXT(JoueurModel joueurModel) {
        this.sc = new Scanner(System.in);
        this.joueur = joueurModel;
    }


    @Override
    public Role demanderIdentite() {
        this.afficherJoueurActuel();


        Role[] lesRolesDisponibles = Role.getRolesJouables();
        // Role rep = this.obtenirRole(lesRolesDisponibles);
        // System.out.println("Vous avez choisi d'incarner " + JeuConstructreurTXT.gras(rep.name()) + " pour la partie à venir");

        return this.obtenirRole(lesRolesDisponibles);
    }

    /**
     * Demande le role désiré à l'utilisateur
     *
     * @param lesRolesDisponibles tous les rôles disponibles à l'utilisateur
     * @return le role sélectionné par l'utilisateur
     */
    private Role obtenirRole(Role[] lesRolesDisponibles) {
        System.out.println("Veuillez choisir le rôle que vous souhaitez jouer pour la partie");
        return (Role) this.obtenirObject(lesRolesDisponibles);
    }

    /**
     * Permet de demander à l'utilisateur quel objet du tableau il souhaite choisir
     *
     * @param objects le tableau qui contient les objets à disposition de l'utilisateur
     * @return l'objet choisi
     */
    private Object obtenirObject(Object[] objects) {
        String msgErreur = "La réponse choisi est incorrect; vous devez entrer un nombre compris entre  1 et " + objects.length;

        Integer rep = null; // un int ne peut être null, un integer si
        while (Objects.isNull(rep) || (rep < 1 | rep > objects.length)) {
            if (Objects.nonNull(rep))
                System.out.println("\n\n" + msgErreur);
            this.afficherContenuObjetListe(objects);
            System.out.print("Entrez le numéro de votre réponse: ");
            rep = sc.nextInt();
        }
        System.out.println("Vous avez choisi: " + JeuConstructreurTXT.couleur(objects[rep - 1], "" + objects[rep - 1]));

        return objects[rep - 1];
    }

    /**
     * Permet d'obtenir un joueur à partir d'une liste de joueurs
     *
     * @param lesJoueurs un tableau contenant les modèles joueurs à représenter
     * @return le modèle du joueur choisi
     */
    private JoueurModel obtenirObjetJoueurs(JoueurModel[] lesJoueurs) {
        String[] affichageJoueurs = new String[lesJoueurs.length];
        for (int i = 0; i < lesJoueurs.length; ++i)
            affichageJoueurs[i] = "nom: " + JeuConstructreurTXT.gras(lesJoueurs[i].getNom())
                    + " identité : "
                    + (lesJoueurs[i].estRevele() ? lesJoueurs[i].getRole().toString() : "INCONNUE")
                    + " " + lesJoueurs[i].getPoints() + " points";


        String descCible = (String) this.obtenirObject(affichageJoueurs);

        int idCible = List.of(affichageJoueurs).indexOf(descCible);
        return lesJoueurs[idCible];
    }

//    /**
//     * Affiche à l'utilisateur les rôles qu'il peut choisir
//     *
//     * @param lesRoles tous les roles disponibles
//     */
//    @Deprecated
//    private void afficherRolesDisponibles(Role[] lesRoles) {
//        System.out.println("Quel rôle désirez-vous jouer ?");
//        this.afficherContenuObjetListe(lesRoles);
//    }

    private void afficherContenuObjetListe(Object[] objects) {
        for (int i = 0; i < objects.length; ++i)
            System.out.print(" ---  " + JeuConstructreurTXT.gras("" + (i + 1)) + ": " + objects[i].toString()); // TODO: 10/11/2021 mettre uniquement la première lettre en maj
        System.out.print("\n");
    }


    @Override
    public IAction demanderTourDeJeu(IAction[] actionsDisponibles) {
        this.afficherJoueurActuel();
        this.afficherLesJoueurs();
        System.out.println("Quelle action désirez-vous effectuer ?");
        return (IAction) this.obtenirObject(actionsDisponibles);

    }

    @Override
    public IAction repondreAccusation(IAction[] actionsDisponibles) {
        this.afficherJoueurActuel();
        this.afficherLesJoueurs();
        System.out.println("Vous venez d'être accusé, quelle action désirez-vous effectuer ?");

        return (IAction) this.obtenirObject(actionsDisponibles);
    }

    @Override
    public void faireAttendre() {
    }

    @Override
    public void afficherCartes(CarteRumeur[] cartes) {
        System.out.println("Vous possédez actuellement les cartes suivantes:");
        for (int i = 0; i < cartes.length; ++i) {
            CarteRumeur carte = cartes[i];
            System.out.println("" + (i + 1) + ": " +
                    JeuConstructreurTXT.couleur(cartes[i], carte.getNom()) + "\n" +
                    "Effet Hunt: " + carte.getDescriptionHunt() + "\n" +
                    "Effet Witch: " + carte.getDescriptionWitch() + "\n"
            );
        }
        // TODO: 13/11/2021 ou demander s'il veut faire retour en arrière et accuser à nouveau
    }

    @Override
    public CarteRumeur demanderCarte(CarteRumeur[] cartes) {
        return (CarteRumeur) this.obtenirObject(cartes);
    }

    @Override
    public JoueurModel demanderCibleAccusation() {
        // get tous les joueurs
        // enlever le joueur actuel
        List<JoueurModel> lesJoueurs = new ArrayList<>(List.of(getLesJoueursSansCourant()));
        System.out.println("Quel joueur souhaitez-vous accuser ?");

        List<JoueurModel> jARetirer = new ArrayList<>();
        for (JoueurModel j : lesJoueurs)
            if (j.estRevele())
                jARetirer.add(j);
        jARetirer.forEach(lesJoueurs::remove); // TODO: 01/12/2021 refactor ici


        return this.obtenirObjetJoueurs(lesJoueurs.toArray(JoueurModel[]::new));
    }

    /**
     * Permet d'obtenir tous les joueurs de la partie excepté du joueur courant
     *
     * @return un tableau contenant ces dits joueurs
     */
    protected JoueurModel[] getLesJoueursSansCourant() {
        List<JoueurControlleur> lesJoueurs = Jeu.getInstance().getJoueursEncorePresents();
        lesJoueurs = new ArrayList<>(lesJoueurs); // on effectue une copie des joueurs pour s'assurer de ne pas modifier des valeurs qu'on de devrait pas
        lesJoueurs.remove(JoueurControlleur.getControllerFromModel(this.joueur));
        return lesJoueurs.stream().map(JoueurControlleur::getModel).toArray(JoueurModel[]::new);
    }

    @Override
    public void afficherJoueurs() {
        // TODO: 17/11/2021 peut-être factoriser cette fonction
        this.afficherLesJoueurs();
    }

    @Override
    public void informerErreur(String msgErreur) {
        System.out.println(msgErreur);
    }

    @Override
    public void finirTour() {
        this.demanderContinuation();
    }

    /**
     * Affiche la personne à qui c'est actuellement le tour
     * Vide également l'affichage de la console
     */
    private void afficherJoueurActuel() {
        JeuConstructreurTXT.viderConsole();
        String nomJoueur = JeuConstructreurTXT.gras(this.joueur.getNom());
        System.out.println("Au tour de " + nomJoueur + " de jouer son tour...");
    }

    /**
     * affiche les joueurs associés à leur rôle
     */
    private void afficherLesJoueurs() {
        System.out.println("Joueurs présents dans la partie:");
        for (JoueurModel joueur : Jeu.getInstance().getLesJoueurs()) {
            this.affichageJoueur(joueur);
        }
    }

    /**
     * Permet l'affichage d'un seul joueur
     *
     * @param joueur celui qu'on veut afficher
     */
    private void affichageJoueur(JoueurModel joueur) {
        if (joueur.equals(this.joueur)) {
            System.out.println(JeuConstructreurTXT.gras("Vous: " + joueur.getRole()) +
                    (joueur.estRevele() ? " identité révélée" : " identité cachée")
                    + " et vous avez " + JeuConstructreurTXT.gras("" + joueur.getPoints()) + " points"
            );
        } else {
            System.out.print(
                    joueur.getNom() + " " +
                            (joueur.estRevele() ?
                                    "en tant que: " + JeuConstructreurTXT.gras(joueur.getRole().name())
                                    : JeuConstructreurTXT.gras("ROLE INCONNU")
                            ) + " possède " + joueur.getPoints() + " points"
            );
            List<ConditionNonCible> conditionNonCibleList = joueur.getConditionCiblage();
            if (!conditionNonCibleList.isEmpty())
                conditionNonCibleList.forEach(cdt -> System.out.print(" / " + cdt.getDescription()));
            System.out.println();
        }
    }

    @Override
    public JoueurControlleur demanderProchainJoueur() {
        JoueurModel[] lesJoueurs = getLesJoueursSansCourant();
        System.out.println("Quel joueur souhaitez-vous sélectionner ?");

        JoueurModel cible = obtenirObjetJoueurs(lesJoueurs);

        return JoueurControlleur.getControllerFromModel(cible);
    }

    @Override
    public void afficherProchainJoueurTour(JoueurModel joueur) {
        System.out.println(JeuConstructreurTXT.gras(joueur.getNom()) + " sera bien le prochain joueur à jouer son tour");
    }

    @Override
    public CarteRumeur demanderDefausseCarte() {
        System.out.println("Quelle carte souhaitez-vous défausser de votre main ?");
        CarteRumeur[] cartesDisponibles = this.joueur.getCartesMain();
        return (CarteRumeur) this.obtenirObject(cartesDisponibles);
    }

    @Override
    public CarteRumeur demanderRepriseCartePersonnelle(CarteRumeur[] lesCartesDisponibles) {
        System.out.println("Quelle carte souhaitez-vous reprendre parmi les suivantes ?");
        return (CarteRumeur) this.obtenirObject(lesCartesDisponibles);
    }

    @Override
    public CarteRumeur demanderRepriseCarteJoueur(CarteRumeur[] lesCartesDisponibles) {
        // int n'est pas un Object mais Integer si
        Integer[] lesNumeroCartes = new Integer[lesCartesDisponibles.length];
        for (int i = 0; i < lesCartesDisponibles.length; )
            lesNumeroCartes[i] = ++i;
        int indice = (int) this.obtenirObject(lesNumeroCartes);
        return lesCartesDisponibles[indice-1];
    }

    @Override
    public void afficherRoleJoueur(JoueurModel joueur) {
        System.out.println("Le rôle de " + JeuConstructreurTXT.gras(joueur.getNom()) + " est " +
                JeuConstructreurTXT.couleur(joueur.getRole(), joueur.getRole().toString()));
    }

    /**
     * Permet de faire attendre le joueur avant de terminer son tour
     */
    protected void demanderContinuation() {
        System.out.print("Appuyez sur entrée pour continuer...");
        try {
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
        } catch (IOException ignored) {
        }
    }
}
