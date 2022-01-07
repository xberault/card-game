package app.controllersGUI;

import app.cartes.CarteRumeur;
import app.cartes.EffetNonJouableException;
import app.joueur.model.JoueurModel;
import app.model.action.Action2;
import app.model.action.IAction;
import app.model.action.action1.ReleverIdentite;
import app.model.action.action2.Accusation;
import app.model.action.action2.DefausserCarte;
import app.model.action.action2.JouerCarteHunt;
import app.model.action.action2.JouerCarteWitch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InterfaceJoueurController {

    @FXML
    private VBox j1, j2, j3, j4, j5, cartes, actions;

    @FXML
    private HBox defausse;

    private ArrayList<JoueurModel> joueurs;

    private JoueurModel joueur;

    public InterfaceJoueurController(){
        this.joueur = null;
        this.joueurs = null;
    }

    public void afficherAdversaires(ArrayList<JoueurModel> joueurs){
        this.joueurs = joueurs;
        this.j1.getChildren().clear();
        this.j2.getChildren().clear();
        this.j3.getChildren().clear();
        this.j4.getChildren().clear();
        this.j5.getChildren().clear();
        switch (joueurs.size()){
            case 2:
                try {
                    this.j1.getChildren().add(afficherUnAdversaire(joueurs.get(0)));
                    this.j2.getChildren().add(afficherUnAdversaire(joueurs.get(1)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    this.j1.getChildren().add(afficherUnAdversaire(joueurs.get(0)));
                    this.j2.getChildren().add(afficherUnAdversaire(joueurs.get(1)));
                    this.j3.getChildren().add(afficherUnAdversaire(joueurs.get(2)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    this.j1.getChildren().add(afficherUnAdversaire(joueurs.get(0)));
                    this.j2.getChildren().add(afficherUnAdversaire(joueurs.get(1)));
                    this.j4.getChildren().add(afficherUnAdversaire(joueurs.get(2)));
                    this.j5.getChildren().add(afficherUnAdversaire(joueurs.get(3)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                try {
                    this.j1.getChildren().add(afficherUnAdversaire(joueurs.get(0)));
                    this.j2.getChildren().add(afficherUnAdversaire(joueurs.get(1)));
                    this.j3.getChildren().add(afficherUnAdversaire(joueurs.get(2)));
                    this.j4.getChildren().add(afficherUnAdversaire(joueurs.get(3)));
                    this.j5.getChildren().add(afficherUnAdversaire(joueurs.get(4)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public VBox afficherUnAdversaire(JoueurModel joueur) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/Adversaire.fxml"));
        VBox adversaire = loader.load();
        AdversaireController controller = loader.getController();
        controller.initJoueurAdversaire(joueur);
        return adversaire;
    }

    public void afficherMain(JoueurModel joueur) throws IOException {
        this.joueur = joueur;
        this.cartes.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/Adversaire.fxml"));
        VBox main = loader.load();
        AdversaireController controller = loader.getController();
        controller.initJoueur(joueur);
        this.cartes.getChildren().add(main);
    }

    public void afficherDefausse(List<CarteRumeur> defausse) throws IOException {
        this.defausse.getChildren().clear();
        for (CarteRumeur carteD:defausse) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/Carte.fxml"));
            VBox carte = loader.load();
            CarteController controller = loader.getController();
            controller.initCarte(carteD);
            this.defausse.getChildren().add(carte);
        }
    }

    public void afficherActions(IAction[] actionsDisponibles){
        for (IAction action:actionsDisponibles) {
            Button button = new Button();
            if (action instanceof Accusation){
                button.setText("Accuser un joueur");
            }
            else if (action instanceof JouerCarteHunt){
                JoueurModel proprio = this.joueur;
                button.setText("Jouer l'effet Hunt d'une carte");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {

                            selectionCartesMain(action,proprio);
                    }});

            }
            else if (action instanceof JouerCarteWitch){
                button.setText("Jouer l'effet Witch d'une carte");

            }
            else if (action instanceof DefausserCarte){
                button.setText("Defausser une carte");

            }
            else if (action instanceof ReleverIdentite){
                button.setText("Révéler votre identité");

            }

            this.actions.getChildren().add(button);
        }
    }

    public void selectionCartesMain(IAction action, JoueurModel proprietaire){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/DemandeCarte.fxml"));
            VBox root = loader.load();
            Stage inner = new Stage();
            inner.setScene(new Scene(root));
            DemandeCarteController controller = loader.getController();
            controller.initChoix(this.joueur.getCartesMain());

            inner.showAndWait();
            ((Action2) action).executerAction(controller.getResult());
        }catch (Exception e){
            e.printStackTrace();
        }


        /* HBox cartes = (HBox) ((VBox) (this.cartes.getChildren().get(0))).getChildren().get(1);
        for (Node carte:cartes.getChildren()) {
            String nomCarte = ((Label) (((VBox) carte).getChildren().get(0))).getText();
            Button button = new Button(nomCarte);

            CarteRumeur carteRumeur=null;
            for (CarteRumeur cMain:proprietaire.getCartesMain()) {
                if(cMain.getNom()==nomCarte){
                    carteRumeur = cMain;
                }
            }
            if (carteRumeur!=null){
                ((Action2) action).setCible(carteRumeur);
            }

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    try {
                        action.executerAction();
                        retirerBoutonsCartes(cartes);
                    } catch (EffetNonJouableException ex) {
                        ex.printStackTrace();
                    }
                }});
            ((VBox) carte).getChildren().add(button);

        }*/

    }

    public void retirerBoutonsCartes(HBox root){
        for (Node node:root.getChildren()) {
            if(node instanceof Button){
                root.getChildren().remove(node);
            }
        }
    }
}
