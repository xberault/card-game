package app.controllersGUI;

import app.cartes.CarteRumeur;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CarteController {

    @FXML
    private Label titre, witch, hunt;

    @FXML
    VBox root;

    private CarteRumeur carte;

    public CarteController(){
        this.carte = null;
    }

    public void initCarte(CarteRumeur carte){{
        this.titre.setText(carte.getNom());
        this.witch.setText(carte.getDescriptionWitch());
        this.hunt.setText(carte.getDescriptionHunt());
        if(carte.estRevelee())
            this.root.setStyle("-fx-border-color: black;-fx-border-width: 1;-fx-border-style: dashed; -fx-background-color: lightblue");
        }
        this.carte = carte;
    }

    public void initCarteAdversaire(CarteRumeur carte){
        if(carte.estRevelee()){
            this.titre.setText(carte.getNom());
            this.witch.setText(carte.getDescriptionWitch());
            this.hunt.setText(carte.getDescriptionHunt());
            this.root.setStyle("-fx-border-color: black;-fx-border-width: 1;-fx-border-style: dashed; -fx-background-color: lightblue");
            this.carte = carte;
        }
        else{
            root.setStyle("-fx-background-color: black;");
        }
    }



    public CarteRumeur getCarte(){
        return this.carte;
    }
}
