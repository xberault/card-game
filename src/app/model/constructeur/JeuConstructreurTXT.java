package app.model.constructeur;

import app.Jeu;
import app.joueur.JoueurControlleur;
import app.joueur.model.constructeur.IJoueurConstructeur;
import app.joueur.model.constructeur.JoueurConstructeurTXT;
import app.model.IColorable;

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

    /**
     * Permet de gérer la construction des joueurs humains et IA
     */
    private final IJoueurConstructeur joueurConstructeur;

    public JeuConstructreurTXT() {
        this.sc = new Scanner(System.in);
        this.joueurConstructeur = new JoueurConstructeurTXT();
    }

    /**
     * permet de passer une chaine de charactère en gras lors de l'affichage
     * requiert un terminal qui interprète les codes ANSI
     *
     * @param string le texte à mettre en gras
     * @return le texte en gras
     */
    public static String gras(String string) {
        return JeuConstructreurTXT.CODE_TEXTE_GRAS + string + JeuConstructreurTXT.CODE_TEXTE_NEUTRE;
    }

    /**
     * Permet de coloriser un texte
     * Si celui-ci n'en est pas capable, il sera afficher en gras
     *
     * @param obj        l'objet qui est concerné par l'affichage
     * @param aColoriser le texte que l'on veut mettre en couleur
     * @return un texte contenant les codes adéquats pour l'affichage
     */
    public static String couleur(Object obj, String aColoriser) {
        if (obj instanceof IColorable)
            return ((IColorable) obj).getStringColore(aColoriser);
        Jeu.printd("Couleur de " + obj.getClass() + " n'est pas implémentée");
        return gras(aColoriser);
    }

    /**
     * Permet de vider l'affichage de System.out
     */
    public static void viderConsole() {
        if(Jeu.DEBUG)
            return;
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public List<JoueurControlleur> initJoueur() {
        List<JoueurControlleur> joueurs = new ArrayList<>();
        int nbJoueur = this.demandeNombreJoueur();
        int nbJoueurIA = this.demandeNombreJoueurIA(nbJoueur);
        System.out.println("\nTrès bien, la partie va démarrer avec " + JeuConstructreurTXT.gras("" + nbJoueur) + " joueurs dont " + nbJoueurIA + " IA");

        for (int i = 0; i < nbJoueur - nbJoueurIA; ++i) {
            System.out.println("---------------");
            System.out.println("Création du joueur numéro " + (i + 1));
            joueurs.add(this.joueurConstructeur.creerJoueurHumain());
            System.out.println("---------------");
        }
        for (int i = 0; i < nbJoueurIA; ++i)
            joueurs.add(this.joueurConstructeur.creerJoueurIA());

        return joueurs;
    }

    /**
     * Demande le nombre de joueurs à mettre à la partie
     *
     * @return un entier correspondant au nombre de joueurs humain
     */
    private int demandeNombreJoueur() {
        System.out.println("Combien de joueurs êtes-vous ?\nLe jeu se joue de 3 à 6 joueurs");
        System.out.print("Insérez un nombre: ");
        int nbJoueur = this.sc.nextInt();
        while (nbJoueur < 3 || nbJoueur > 6) {
            System.out.println(gras("\nLe nombre de joueur doit être compris entre 3 et 6 inclus"));
            System.out.print("Insérez un nombre: ");
            nbJoueur = this.sc.nextInt();
        }
        return nbJoueur;
    }

    private int demandeNombreJoueurIA(int nbJoueurHumain) {
        if (nbJoueurHumain == 6)
            return 0;
        int nbMinimumJoueur = nbJoueurHumain > 2 ? 0 : 3 - nbJoueurHumain;
        System.out.println("Combien d'" + gras("IA") + " voulez-vous dans votre partie ? (compris entre " + gras("" + nbMinimumJoueur) + " et " + gras("" + (6 - nbJoueurHumain)) + " )");
        System.out.print("Insérez un nombre: ");
        int nbJoueurIA = this.sc.nextInt();
        while (nbJoueurIA > 6 - nbJoueurHumain) {
            System.out.println(gras("\nLe nombre de joueur doit être compris entre " + nbMinimumJoueur + " et " + (6 - nbJoueurHumain) + " inclus"));
            System.out.print("Insérez un nombre: ");
            nbJoueurIA = this.sc.nextInt();
        }
        return nbJoueurIA;
    }

}
