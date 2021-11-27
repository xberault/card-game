package app.joueur.model.constructeur;

import app.joueur.JoueurControlleur;
import app.joueur.JoueurHumain;
import app.joueur.JoueurIA;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.joueur.model.strategie.IStrategieIA;
import app.joueur.model.strategie.StrategieAleatoire;
import app.joueur.model.vue.JoueurVUETXT;
import app.joueur.model.vue.JoueurVueIATXT;
import app.model.constructeur.JeuConstructreurTXT;

import java.util.Scanner;

public class JoueurConstructeurTXT implements IJoueurConstructeur {
    private final Scanner sc;

    public JoueurConstructeurTXT() {
        this.sc = new Scanner(System.in);
    }

    @Override
    public JoueurControlleur creerJoueurIA() {
        JoueurControlleur joueurControlleur = new JoueurControlleur();

        IStrategieIA strategieIA = new StrategieAleatoire(); // TODO: 08/11/2021 Gérer la stratégie que l'IA doit adopter

        JoueurIA joueurModel = new JoueurIA(strategieIA);
        JoueurVueIATXT joueurVue = new JoueurVueIATXT(joueurModel);

        joueurModel.ajouterObserverEtat(joueurControlleur);

        joueurControlleur.setModel(joueurModel);
        joueurControlleur.setVue(joueurVue);

        return joueurControlleur;
    }

    @Override
    // S'occupe de gérer la création d'un joueur humain
    public JoueurControlleur creerJoueurHumain() {
        System.out.println("Quel est le nom de votre humain ?");
        System.out.print("Entrez le nom: ");
        String nom = this.sc.nextLine();

        JoueurControlleur joueur = this.creationJoueurHumain(nom);

        System.out.println("\nTrès bien votre humain " + JeuConstructreurTXT.gras(nom) + " a été crée");
        return joueur;
    }

    /**
     * Permet de créer un joueur humain et de relier ses composants au controlleur
     *
     * @param nom le nom du joueur à créer
     * @return le nouveau joueur
     */
    private JoueurControlleur creationJoueurHumain(String nom) {
        JoueurControlleur joueurControlleur = new JoueurControlleur();
        JoueurModel joueurModel = new JoueurHumain(nom);
        IJoueurVue joueurVue = new JoueurVUETXT();
        joueurControlleur.setModel(joueurModel);
        joueurControlleur.setVue(joueurVue);

        joueurModel.ajouterObserverEtat(joueurControlleur);

        return joueurControlleur;
    }

}
