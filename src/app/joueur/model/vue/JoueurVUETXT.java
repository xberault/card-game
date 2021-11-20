package app.joueur.model.vue;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.Action;
import app.model.constructeur.JeuConstructreurTXT;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class JoueurVUETXT implements IJoueurVue {

    /**
     * Permet de gérer les entrées sorties avec l'utilisateur
     */
    private final Scanner sc;

    public JoueurVUETXT() {
        this.sc = new Scanner(System.in);
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
     * Affiche à l'utilisateur les rôles qu'il peut choisir
     *
     * @param lesRoles tous les roles disponibles
     */
    @Deprecated
    private void afficherRolesDisponibles(Role[] lesRoles) {
        System.out.println("Quel rôle désirez-vous jouer ?");
        this.afficherContenuObjetListe(lesRoles);
    }

    private void afficherContenuObjetListe(Object[] objects) {
        for (int i = 0; i < objects.length; ++i)
            System.out.print(" ---  " + JeuConstructreurTXT.gras("" + (i + 1)) + ": " + objects[i].toString()); // TODO: 10/11/2021 mettre uniquement la première lettre en maj
        System.out.print("\n");
    }


    @Override
    public Action demanderTourDeJeu(Action[] actionsDisponibles) {
        this.afficherJoueurActuel();
        this.afficherLesJoueurs();
        System.out.println("Quelle action désirez-vous effectuer ?");
        return (Action) this.obtenirObject(actionsDisponibles);

    }

    @Override
    public Action repondreAccusasion(Action[] actionsDisponibles) {
        this.afficherJoueurActuel();
        this.afficherLesJoueurs();
        System.out.println("Vous venez d'être accusé, quelle action désirez-vous effectuer ?");
        return (Action) this.obtenirObject(actionsDisponibles);
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
        // TODO: 13/11/2021 demander action sur une carte -- regarder pour avoir des infos -- activer effet hunt de la carte
        // TODO: 13/11/2021 ou demander s'il veut faire retour en arrière et accuser à nouveau 
    }

    @Override
    public CarteRumeur demanderCarte(CarteRumeur[] cartes) {
        // TODO: 17/11/2021 améliorer affichage?
        return (CarteRumeur) this.obtenirObject(cartes);
    }

    @Override
    public JoueurModel demanderCibleAccusation() {
        // get tous les joueurs
        // enlever le joueur actuel
        List<JoueurModel> lesJoueurs = List.of(Jeu.getInstance().getLesJoueurs());
        lesJoueurs = new ArrayList<>(lesJoueurs); // on effectue une copie des joueurs pour s'assurer de ne pas modifier des valeurs qu'on de devrait pas
        lesJoueurs.remove(Jeu.getInstance().getJoueurCourant());
        System.out.println("Quel joueur souhaitez-vous accuser ?");
        JoueurModel joueur = (JoueurModel) this.obtenirObject(lesJoueurs.toArray());
        return joueur;
    }

    @Override
    public void afficherJoueurs() {
        // TODO: 17/11/2021 peut-être factoriser cette fonction
        this.afficherJoueurActuel();
    }

    /**
     * Affiche la personne à qui c'est actuellement le tour
     * Vide également l'affichage de la console
     */
    private void afficherJoueurActuel() {
        JeuConstructreurTXT.viderConsole();
        String nomJoueur = JeuConstructreurTXT.gras(Jeu.getInstance().getJoueurCourant().getNom());
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
        if (joueur.equals(Jeu.getInstance().getJoueurCourant())) {
            System.out.println(JeuConstructreurTXT.gras("Vous: " + joueur.getRole()) +
                    (joueur.estRevele() ? " identité révélée" : " identité cachée")
            );
        } else {
            System.out.println(
                    joueur.getNom() + " " +
                            (joueur.estRevele() ?
                                    "en tant que: " + JeuConstructreurTXT.gras(joueur.getRole().name())
                                    : JeuConstructreurTXT.gras("ROLE INCONNU")
                            )
            );
        }
    }

    @Override
    public JoueurControlleur demanderProchainJoueur() {
        return null;
    }

    @Override
    public void afficherProchainJoueur() {

    }

    @Override
    public CarteRumeur demanderDefausseCarte() {
        return null;
    }

    @Override
    public CarteRumeur demanderRepriseCarte(CarteRumeur[] lesCartesDisponibless) {
        return null;
    }
}
