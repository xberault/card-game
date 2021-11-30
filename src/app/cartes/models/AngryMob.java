package app.cartes.models;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.cartes.condition.ConditionVillageois;
import app.joueur.JoueurControlleur;
import app.joueur.model.JoueurModel;
import app.model.Couleur;

public class AngryMob extends CarteRumeur {

    private static final String descriptionHunt = """
            Révélez l'identité d'un joueur
            Sorcière : Vous gagnez 2 points. Vous jouez le prochain tour
            Villageois : Vous perdez 2 points. Il joue le prochain tour""";
    private static final String descriptionWitch = "Vous jouez le prochain tour";

    public AngryMob() {
        super("Foule en colère", descriptionHunt, descriptionWitch);
        super.ajouterConditionHunt(new ConditionVillageois());
    }

    @Override
    public Couleur getColor() {
        return Couleur.RED;
    }

    @Override
    protected void pActiverEffetWitch() {
        Jeu.getInstance().setProchainJoueur(super.joueur);
        super.joueur.getJoueurVue().afficherProchainJoueurTour(super.joueur.getModel());
    }

    @Override
    protected void pActiverEffetHunt() {
        JoueurModel cible = super.joueur.getJoueurVue().demanderCibleAccusation();
        cible.seRevele();
        int nbPointGagne = 0;
        switch (cible.getRole()) {
            case VILLAGEOIS -> {
                nbPointGagne -= 2;
                Jeu.getInstance().setProchainJoueur(super.joueur);
            }
            case SORCIERE -> {
                nbPointGagne += 2;
                Jeu.getInstance().setProchainJoueur(JoueurControlleur.getControllerFromModel(cible));
            }
        }
        super.joueur.getModel().ajouterPoints(nbPointGagne);
    }
}
