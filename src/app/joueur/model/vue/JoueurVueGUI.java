package app.joueur.model.vue;

import app.Jeu;
import app.cartes.CarteRumeur;
import app.controllersGUI.*;
import app.joueur.JoueurControlleur;
import app.joueur.model.IJoueurVue;
import app.joueur.model.JoueurModel;
import app.joueur.model.constructeur.JoueurConstructeurGUI;
import app.model.Role;
import app.model.action.IAction;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class JoueurVueGUI implements IJoueurVue {

    /**
     * Joueur représenté par la vue
     */
    private final JoueurModel joueur;

    private GridPane vue;

    private InterfaceJoueurController vueController;
    private JoueurModel accuser;

    public JoueurVueGUI(JoueurModel joueurModel){
        this.joueur=joueurModel;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/InterfaceJoueur.fxml"));
        try {
            this.vue = loader.load();
            this.vueController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role demanderIdentite() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/DemandeRole.fxml"));
            VBox root = loader.load();
            Stage inner = new Stage();
            inner.setScene(new Scene(root));
            DemandeRoleController controller = loader.getController();
            controller.setJoueurNom( this.joueur.getNom());

            inner.showAndWait();
            return controller.getRole();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IAction demanderTourDeJeu(IAction[] actionsDisponibles) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/DemandeAction.fxml"));
            VBox root = loader.load();
            Stage inner = new Stage();
            inner.setScene(new Scene(root));
            DemandeActionController controller = loader.getController();
            controller.initChoix(actionsDisponibles);

            inner.showAndWait();
            return controller.getResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IAction repondreAccusation(IAction[] actionsDisponibles) {
        afficherInterface();
        afficherActions(actionsDisponibles);
        return null;
    }

    @Override
    public void faireAttendre() {

    }

    @Override
    public void afficherCartes(CarteRumeur[] cartes) {

    }

    @Override
    public CarteRumeur demanderCarte(CarteRumeur[] cartes) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/DemandeCarte.fxml"));
            VBox root = loader.load();
            Stage inner = new Stage();
            inner.setScene(new Scene(root));
            DemandeCarteController controller = loader.getController();
            controller.initChoix(cartes);

            inner.showAndWait();
            return controller.getResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JoueurModel demanderCibleAccusation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/DemandeJoueur.fxml"));
            VBox root = loader.load();
            Stage inner = new Stage();
            inner.setScene(new Scene(root));
            DemandeJoueurController controller = loader.getController();
            ArrayList<JoueurModel> adversaires = new ArrayList<>(Arrays.asList(Jeu.getInstance().getLesJoueurs()));
            adversaires.remove(adversaires.indexOf(this.joueur));
            controller.initChoix(adversaires);

            inner.showAndWait();
            return controller.getResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afficherJoueurs() {
    }

    @Override
    public void informerErreur(String msgErreur) {

    }

    @Override
    public void finirTour() {

    }

    @Override
    public JoueurControlleur demanderProchainJoueur() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/DemandeJoueur.fxml"));
            VBox root = loader.load();
            Stage inner = new Stage();
            inner.setScene(new Scene(root));
            DemandeJoueurController controller = loader.getController();
            ArrayList<JoueurModel> adversaires = new ArrayList<>(Arrays.asList(Jeu.getInstance().getLesJoueurs()));
            adversaires.remove(adversaires.indexOf(this.joueur));
            controller.initChoix(adversaires);

            inner.showAndWait();
            return JoueurControlleur.getControllerFromModel(controller.getResult());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afficherProchainJoueurTour(JoueurModel joueur) {

    }

    @Override
    public CarteRumeur demanderDefausseCarte() {
        return demanderCarte(this.joueur.getCartesMain());
    }

    @Override
    public CarteRumeur demanderRepriseCartePersonnelle(CarteRumeur[] lesCartesDisponibles) {
        return demanderCarte(lesCartesDisponibles);
    }

    @Override
    public CarteRumeur demanderRepriseCarteJoueur(CarteRumeur[] lesCartesDisponibles) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/DemandeCarteCachee.fxml"));
            VBox root = loader.load();
            Stage inner = new Stage();
            inner.setScene(new Scene(root));
            DemandeCarteCacheeController controller = loader.getController();
            controller.initChoix(lesCartesDisponibles);

            inner.showAndWait();
            return controller.getResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afficherRoleJoueur(JoueurModel joueur) {

    }

    public void afficherInterface(){
        ArrayList<JoueurModel> adversaires = new ArrayList<>(Arrays.asList(Jeu.getInstance().getLesJoueurs()));
        adversaires.remove(adversaires.indexOf(this.joueur));
        this.vueController.afficherAdversaires(adversaires);
        try {
            this.vueController.afficherMain(this.joueur);
            this.vueController.afficherDefausse(Jeu.getInstance().getDefausse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Jeu.getInstance().changerFenetre(this.vue);
    }

    public void afficherActions(IAction[] actionsDisponibles){
        this.vueController.afficherActions(actionsDisponibles);
    }

    public JoueurModel getAccuser() {
        return this.accuser;
    }

    public void setAccuser(JoueurModel joueurSource) {
        this.accuser = joueurSource;
    }
}
