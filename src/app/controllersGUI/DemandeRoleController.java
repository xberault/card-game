package app.controllersGUI;

import app.model.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class DemandeRoleController {

    @FXML
    private Label jName;

    @FXML
    private RadioButton villagerButton;

    @FXML
    private RadioButton witchButton;

    @FXML
    private Button nextButton;

    private Role role;


    public DemandeRoleController(){}

    public void setJoueurNom(String nom){
        this.jName.setText("Joueur: "+ nom);
    }

    public Role getRole(){
        return this.role;
    }

    public void choisirRole(){
        String choix = ((RadioButton) this.villagerButton.getToggleGroup().getSelectedToggle()).getText();
        switch (choix){
            case "Villageois":
                role =  Role.getRolesJouables()[0];
            case "Sorcière":
                role =  Role.getRolesJouables()[1];
        }
        jName.getScene().getWindow().hide();
    }


}
