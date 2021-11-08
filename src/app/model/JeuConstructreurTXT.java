package app.model;

import app.joueur.JoueurControlleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JeuConstructreurTXT implements JeuConstructeur {

    /**
     * String permettant d'afficher un texte sans formattage
     */
    private static final String CODE_TEXTE_NEUTRE = "\033[0;0m";
    /**
     * String permettant d'afficher un texte en gras
     */
    private static final String CODE_TEXTE_GRAS = "\033[0;1m";
    /**
     * Permet d'effectuer les entrées utilisateurs avec la console
     */
    private final Scanner sc;

    public JeuConstructreurTXT() {
        this.sc = new Scanner(System.in);
    }

    /**
     * permet de passer une chaine de charactère en gras lors de l'affichage
     * requiert un terminal qui interprète les codes ANSI
     *
     * @param string le texte à mettre en gras
     * @return le texte en gras
     */
    private static String gras(String string) {
        return JeuConstructreurTXT.CODE_TEXTE_GRAS + string + JeuConstructreurTXT.CODE_TEXTE_NEUTRE;
    }

    @Override
    public List<JoueurControlleur> initJoueur() {
        List<JoueurControlleur> joueurs = new ArrayList<>();
        int nbJoueur = this.demandeNombreJoueur();
        System.out.println("\nTrès bien, la partie va démarrer avec " + JeuConstructreurTXT.gras("" + nbJoueur) + " joueurs");

        for (int i = 0; i < nbJoueur; ++i) {
            i = i;
            // TODO: Ajouter la prise en compte du nombre d'IA 08/11/2021
        }


        return null;
    }

    private int demandeNombreJoueur() {
        System.out.println("Combien de joueurs êtes-vous ?");
        System.out.print("Insérez un nombre: ");
        int nbJoueur = this.sc.nextInt();
        while (nbJoueur < 3 || nbJoueur > 6) {
            System.out.println(gras("\nLe nombre de joueur doit être compris entre 3 et 6 inclus"));
            System.out.print("Insérez un nombre: ");
            nbJoueur = this.sc.nextInt();
        }
        return nbJoueur;
    }

    @Override
    public void demarrerPartie() {
        // TODO: 08/11/2021  
    }
}
