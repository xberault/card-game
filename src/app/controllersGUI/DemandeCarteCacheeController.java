package app.controllersGUI;

import app.cartes.CarteRumeur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class DemandeCarteCacheeController {

    @FXML
    private VBox root;

    @FXML
    private Button nextButton;

    private CarteRumeur result;

    private CarteRumeur[] cartes;

    public DemandeCarteCacheeController(){}

    public void initChoix(CarteRumeur[] choix){
        this.cartes = choix;
        final ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < choix.length ; i++) {
            int index = i+1;
            RadioButton rd = new RadioButton("" + index);
            rd.setToggleGroup(group);
            root.getChildren().add(index, rd);
        }
    }

    public void handleNext(){
        String choix =  ( (RadioButton) ((RadioButton) this.root.getChildren().get(1)).getToggleGroup().getSelectedToggle()).getText();
        this.result=this.cartes[Integer.parseInt(choix)-1];
        root.getScene().getWindow().hide();
    }

    public CarteRumeur getResult(){
        return this.result;
    }
}
