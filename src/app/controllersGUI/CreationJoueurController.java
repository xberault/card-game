package app.controllersGUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CreationJoueurController {

    @FXML
    private Text joueur;

    @FXML
    private TextField nom;

    public CreationJoueurController(){}

    public void initJoueur(int i){
        joueur.setText("Nom du joueur humain N°"+i);
    }

}
