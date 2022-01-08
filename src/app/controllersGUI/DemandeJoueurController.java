package app.controllersGUI;

import app.joueur.model.JoueurModel;
import app.model.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class DemandeJoueurController {

    @FXML
    private VBox root;

    @FXML
    private Button nextButton;

    private JoueurModel result;

    private ArrayList<JoueurModel> joueurs;

    public DemandeJoueurController(){}

    public void initChoix(ArrayList<JoueurModel> choix){
        this.joueurs = choix;
        final ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < choix.size() ; i++) {
            RadioButton rd = new RadioButton(choix.get(i).getNom());
            rd.setToggleGroup(group);
            root.getChildren().add(i+1, rd);
        }
    }

    public void handleNext(){
        if (!((((RadioButton) this.root.getChildren().get(1)).getToggleGroup().getSelectedToggle() ==null))){
            String choix =  ( (RadioButton) ((RadioButton) this.root.getChildren().get(1)).getToggleGroup().getSelectedToggle()).getText();
            for (int i = 0; i < this.joueurs.size(); i++) {
                if (this.joueurs.get(i).getNom()==choix){
                    this.result=this.joueurs.get(i);
                }
            }
            root.getScene().getWindow().hide();
        }
    }

    public JoueurModel getResult(){
        return this.result;
    }
}
