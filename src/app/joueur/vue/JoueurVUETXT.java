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
        Role rep = this.obtenirRole(lesRolesDisponibles);

        System.out.println("Vous avez choisi d'incarner " + JeuConstructreurTXT.gras(rep.name()) + " pour la partie à venir");

        return rep;
    }

    /**
     * Demande le role désiré à l'utilisateur
     *
     * @param lesRolesDisponibles tous les rôles disponibles à l'utilisateur
     * @return le role sélectionné par l'utilisateur
     */
    private Role obtenirRole(Role[] lesRolesDisponibles) {
        int rep = 0;

        while (rep < 1 | rep > lesRolesDisponibles.length) {
            if (Objects.nonNull(rep))
                System.out.println("\n\nLa réponse choisi est incorrect; vous devez entrer un nombre compris entre  1 et " + lesRolesDisponibles.length);
            this.afficherRolesDisponibles(lesRolesDisponibles);
            System.out.print("Entrez le numéro du role désiré: ");
            rep = sc.nextInt();
        }
        return lesRolesDisponibles[rep - 1];
    }

    /**
     * Affiche à l'utilisateur les rôles qu'il peut choisir
     *
     * @param lesRoles tous les roles disponibles
     */
    private void afficherRolesDisponibles(Role[] lesRoles) {
        System.out.println("Quel rôle désirez-vous jouer ?");
        for (int i = 0; i < lesRoles.length; ++i)
            System.out.print(" ---  " + (i + 1) + ": " + lesRoles[i].toString()); // TODO: 10/11/2021 mettre uniquement la première lettre en maj
        System.out.print("\n");
    }

    @Override
    public Action demanderTourDeJeu(Action[] actionsDisponibles) {
        return null;
    }

    @Override
    public Action repondreAccusasion(Action[] actionsDisponibles) {
        return null;
    }

    @Override
    public void faireAttendre() {

    }
}
