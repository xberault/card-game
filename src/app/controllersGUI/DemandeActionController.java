package app.controllersGUI;

import app.cartes.CarteRumeur;
import app.model.action.IAction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class DemandeActionController {

    @FXML
    private VBox root;

    @FXML
    private Button nextButton;

    private IAction result;

    private IAction[] actions;

    public DemandeActionController(){}

    public void initChoix(IAction[] choix){
        this.actions = choix;
        final ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < choix.length ; i++) {
            int index = i+1;
            RadioButton rd = new RadioButton(choix[i].toString());
            rd.setToggleGroup(group);
            root.getChildren().add(index, rd);
        }
    }

    public void handleNext(){
        String choix =  ( (RadioButton) ((RadioButton) this.root.getChildren().get(1)).getToggleGroup().getSelectedToggle()).getText();
        for (int i = 0; i < this.actions.length; i++) {
            if (this.actions[i].getClass().toString()==choix){
                this.result=this.actions[i];
            }
        }
        root.getScene().getWindow().hide();
    }

    public IAction getResult(){
        return this.result;
    }
}
