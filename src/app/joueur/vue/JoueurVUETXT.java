package app.joueur.vue;

import app.joueur.model.IJoueurVue;
import app.model.Action;
import app.model.JeuConstructreurTXT;
import app.model.Role;

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
        return (Role) this.obtenirObject(lesRolesDisponibles);
    }

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
        System.out.println("Vous avez choisi: " + JeuConstructreurTXT.gras("" + objects[rep - 1]));

        return objects[rep - 1];
    }

    /**
     * Affiche à l'utilisateur les rôles qu'il peut choisir
     *
     * @param lesRoles tous les roles disponibles
     */
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
        System.out.println("Quelle action désirez-vous effectuer ?");
        return (Action) this.obtenirObject(actionsDisponibles);

    }

    @Override
    public Action repondreAccusasion(Action[] actionsDisponibles) {
        System.out.println("Vous venez d'être accusé, quelle action désirez-vous effectuer ?");
        return (Action) this.obtenirObject(actionsDisponibles);
    }

    @Override
    public void faireAttendre() {

    }
}
