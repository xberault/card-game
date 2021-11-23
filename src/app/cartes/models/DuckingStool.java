package app.cartes.models;

import app.cartes.CarteRumeur;
import app.joueur.JoueurControlleur;
import app.joueur.model.JoueurModel;
import app.model.Couleur;
import app.model.action.Action;
import app.model.action.action1.ReleverIdentite;
import app.model.action.action2.DefausserCarte;

import java.util.Objects;

public class DuckingStool extends CarteRumeur {

    private static final String descriptionHunt = """
            Choisissez un joueur. Il doit révéler son identité ou défausser une carte de sa main
            Sorcière: vous gagnez 1 point; jouez le prochain tour
            Villageois: vous perdez 1 point; il joue le prochain tour
            Defausse: il joue le prochain tour""";

    // TODO: 20/11/2021 voir comment implémenter ce choix d'action

    private static final String descriptionWitch = "Vous choisissez le prochain joueur";

    public DuckingStool() {
        super("Siège à plongeon", descriptionHunt, descriptionWitch);
    }

    @Override
    public Couleur getColor() {
        return Couleur.BLUE_BOLD;
    }

    @Override
    protected void pActiverEffetWitch() {
        JoueurModel jCible = super.joueur.getJoueurVue().demanderCibleAccusation();
        Action actionChoix = super.joueur.getJoueurVue().demanderTourDeJeu(new Action[]{
                new ReleverIdentite(jCible),
                new DefausserCarte(jCible)
        });
        int nbPointsGagnes = 0;
        if (actionChoix instanceof ReleverIdentite) {
            jCible.seRevele();
            super.joueur.getJoueurVue().afficherRoleJoueur(jCible);
            switch (jCible.getRole()) {
                case SORCIERE -> --nbPointsGagnes;
                case VILLAGEOIS -> ++nbPointsGagnes;
            }
            super.joueur.getModel().ajouterPoints(nbPointsGagnes);
        } else if (actionChoix instanceof DefausserCarte) {
            CarteRumeur aDefausser = Objects.requireNonNull(JoueurControlleur.getControllerFromModel(jCible)).getJoueurVue().demanderDefausseCarte();
            jCible.defausserCarte(aDefausser);
        }
    }

    @Override
    protected void pActiverEffetHunt() {
        this.pActiverEffetWitch();
    }
}
