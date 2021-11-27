package app.joueur.model.strategie;

import app.cartes.CarteRumeur;
import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.IAction;

import java.util.Random;

public class StrategieAleatoire implements IStrategieIA {

    /**
     * Objet permettant la génération d'objets aléatoires
     */
    private final Random rand;

    public StrategieAleatoire() {
        this.rand = new Random();
    }

    /**
     * Permet d'obtenir un objet aléatoire à partir d'un tableau
     *
     * @param lesObjets le tableau contenant les objets
     * @return un objet appartement au tableau
     */
    private Object choisirObjet(Object[] lesObjets) {
        return lesObjets[rand.nextInt(lesObjets.length)];
    }

    @Override
    public IAction choisirAction(IAction[] actionsDisponibles) {
        return (IAction) choisirObjet(actionsDisponibles);
    }

    @Override
    public CarteRumeur choisirCarte(CarteRumeur[] lesCartesDisponibles) {
        return (CarteRumeur) choisirObjet(lesCartesDisponibles);
    }

    @Override
    public JoueurModel demanderProchainJoueur(JoueurModel[] lesJoueursSansCourant) {
        return (JoueurModel) choisirObjet(lesJoueursSansCourant);
    }

    @Override
    public JoueurModel demanderCibleAccusation(JoueurModel[] lesJoueursSansCourant) {
        return (JoueurModel) choisirObjet(lesJoueursSansCourant);
    }

    @Override
    public CarteRumeur demanderDefausseCarte(CarteRumeur[] cartesMain) {
        return (CarteRumeur) choisirObjet(cartesMain);
    }

    @Override
    public CarteRumeur demanderRepriseCarte(CarteRumeur[] lesCartesDisponibles) {
        return (CarteRumeur) choisirObjet(lesCartesDisponibles);
    }

    @Override
    public Role demanderIdentite() {
        if (Math.random() > 0.5)
            return Role.SORCIERE;
        else
            return Role.VILLAGEOIS;
    }

    @Override
    public IAction demanderCibleAccusation(IAction[] actionsDisponibles) {
        return (IAction) choisirObjet(actionsDisponibles);
    }

    @Override
    public IAction demanderReponseAccusation(IAction[] actionsDisponibles) {
        return (IAction) this.choisirObjet(actionsDisponibles);
    }
}
