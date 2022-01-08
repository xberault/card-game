package app.controllersGUI;

import app.cartes.CarteRumeur;
import app.joueur.model.JoueurModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AdversaireController {

    @FXML
    private Label joueur;

    @FXML
    private HBox cartes;

    public AdversaireController(){}

    public void initJoueur(JoueurModel joueur) {
        initInfo(joueur);
        initCartes(joueur);

    }

    public void initJoueurAdversaire(JoueurModel joueur) {
        initInfoAdversaire(joueur);
        initCartesAdversaire(joueur);
    }

    public void initInfo(JoueurModel joueur){
        this.joueur.setText(joueur.getNom()+ " Role: " + joueur.getRole().name()+" Points: "+joueur.getPoints());
    }

    public void initInfoAdversaire(JoueurModel joueur){
        String role;
        if (!joueur.estRevele()){
            role = "Inconnu";
        }
        else {
            role = joueur.getRole().name();
        }
        String points = " Points: "+joueur.getPoints();

        this.joueur.setText(joueur.getNom()+ " Role: " +role+points);
    }

    public void initCartes(JoueurModel joueur){
        ArrayList<CarteRumeur> cartesJoueur = new ArrayList<>();
        cartesJoueur.addAll(Arrays.asList(joueur.getCartesMain()));
        cartesJoueur.addAll(Arrays.asList(joueur.getCartesRumeursRevelees()));

        for (CarteRumeur carteJ:cartesJoueur){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/Carte.fxml"));
                VBox carte = loader.load();
                CarteController controller = loader.getController();
                controller.initCarte(carteJ);
                cartes.getChildren().add(carte);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initCartesAdversaire(JoueurModel joueur){
        ArrayList<CarteRumeur> cartesJoueur = new ArrayList<>();
        cartesJoueur.addAll(Arrays.asList(joueur.getCartesMain()));
        cartesJoueur.addAll(Arrays.asList(joueur.getCartesRumeursRevelees()));
        for (CarteRumeur carteJ:cartesJoueur){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/Carte.fxml"));
                VBox carte = loader.load();
                CarteController controller = loader.getController();
                controller.initCarteAdversaire(carteJ);
                cartes.getChildren().add(carte);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

