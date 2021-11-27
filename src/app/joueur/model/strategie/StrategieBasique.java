package app.joueur.model.strategie;

import app.cartes.CarteRumeur;
import app.joueur.model.JoueurModel;
import app.model.Role;
import app.model.action.IAction;

public class StrategieBasique implements IStrategieIA {


    @Override
    public IAction choisirAction(IAction[] actionsDisponibles) {
        return null;
    }

    @Override
    public CarteRumeur choisirCarte(CarteRumeur[] lesCartesDisponibles) {
        return null;
    }

    @Override
    public JoueurModel demanderProchainJoueur(JoueurModel[] lesJoueursSansCourant) {
        return null;
    }

    @Override
    public JoueurModel demanderCibleAccusation(JoueurModel[] lesJoueursSansCourant) {
        return null;
    }

    @Override
    public CarteRumeur demanderDefausseCarte(CarteRumeur[] cartesMain) {
        return null;
    }

    @Override
    public CarteRumeur demanderRepriseCarte(CarteRumeur[] lesCartesDisponibles) {
        return null;
    }

    @Override
    public Role demanderIdentite() {
        return null;
    }

    @Override
    public IAction demanderCibleAccusation(IAction[] actionsDisponibles) {
        return null;
    }

    @Override
    public IAction demanderReponseAccusation(IAction[] actionsDisponibles) {
        return null;
    }
}
