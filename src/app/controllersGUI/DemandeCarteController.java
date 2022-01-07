package app.controllersGUI;

import app.cartes.CarteRumeur;
import app.joueur.model.JoueurModel;
import app.model.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class DemandeCarteController {

    @FXML
    private VBox root;

    @FXML
    private Button nextButton;

    private CarteRumeur result;

    private CarteRumeur[] cartes;

    public DemandeCarteController(){}

    public void initChoix(CarteRumeur[] choix){
        this.cartes = choix;
        final ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < choix.length ; i++) {
            RadioButton rd = new RadioButton(choix[i].getNom());
            rd.setToggleGroup(group);
            root.getChildren().add(i+1, rd);
        }
    }

    public void handleNext(){
        String choix =  ( (RadioButton) ((RadioButton) this.root.getChildren().get(1)).getToggleGroup().getSelectedToggle()).getText();
        for (int i = 0; i < this.cartes.length; i++) {
            if (this.cartes[i].getNom()==choix){
                this.result=this.cartes[i];
            }
        }
        root.getScene().getWindow().hide();
    }

    public CarteRumeur getResult(){
        return this.result;
    }
}
