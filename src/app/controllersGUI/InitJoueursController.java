package app.controllersGUI;

import app.joueur.JoueurControlleur;
import app.joueur.model.constructeur.IJoueurConstructeur;
import app.joueur.model.constructeur.JoueurConstructeurGUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InitJoueursController {

    @FXML
    private Spinner<Integer> nbJoueurs;

    @FXML
    private Spinner<Integer> nbJoueursIA;

    @FXML
    private GridPane grid;

    private List<JoueurControlleur> listejoueurs;

    private JoueurConstructeurGUI joueurConstructeur;


    public InitJoueursController(){
    }

    public void setJoueurConstructeur(JoueurConstructeurGUI joueurConstructeur){
        this.joueurConstructeur = joueurConstructeur;
    }

    @FXML
    public void initialize(){
        listejoueurs = new ArrayList<>();
    }

    public List<JoueurControlleur> getListejoueurs() {
        return listejoueurs;
    }

    @FXML
    public void handleNbJoueurs(){
        if (nbJoueursIA.getValue() > nbJoueurs.getValue()){
            // TODO 01/01/2022
            // montrer erreur
        }
        else {
            int nbj = this.nbJoueurs.getValue();
            int nbjIA = this.nbJoueursIA.getValue();
            nouvellePage(nbj,nbjIA);

            Button button = new Button("Suivant");
            button.setOnAction(e -> creationJoueurs(nbjIA));
            this.grid.add(button,0,2);
        }
    }

    public void nouvellePage(int j, int jIA){
        this.grid.getChildren().clear();
        VBox joueursH = new VBox(10);
        this.grid.add(joueursH,0,0);
        Label labelJ = new Label("Nom des Joueurs");
        joueursH.getChildren().add(labelJ);

        ajouterChampsNoms(joueursH,j-jIA);
    }

    public void ajouterChampsNoms(Pane parent, int joueurs){
        for (int i = 0; i < joueurs; ++i) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/NouveauJoueur.fxml"));
                HBox box = loader.load();
                parent.getChildren().add(box);
                CreationJoueurController controller = loader.getController();
                controller.initJoueur(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void creationJoueurs(int nbjIA){
        // TODO 01/01/2022
        // refuser les noms vide
        for (Node enfant:this.grid.getChildren()){
            if(enfant instanceof VBox){
                if (((VBox) enfant).getChildren().get(0) instanceof Label && ((Label) ((VBox) enfant).getChildren().get(0)).getText()=="Nom des Joueurs"){
                    for (int i = 0; i < ((VBox) enfant).getChildren().size()-1; i++) {
                        this.listejoueurs.add(this.joueurConstructeur.creerJoueurHumain( ((TextField) ( (HBox) ( (VBox) enfant).getChildren().get(i+1) ).getChildren().get(1)).getText()));
                    }
                }
            }
        }
        for (int i = 0; i < nbjIA; i++) {
            this.listejoueurs.add(this.joueurConstructeur.creerJoueurIA());
        }
        grid.getScene().getWindow().hide();
    }
}
