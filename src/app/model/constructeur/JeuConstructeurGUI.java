package app.model.constructeur;

import app.Jeu;
import app.controllersGUI.InitJoueursController;
import app.joueur.JoueurControlleur;
import app.joueur.model.constructeur.IJoueurConstructeur;
import app.joueur.model.constructeur.JoueurConstructeurGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class JeuConstructeurGUI extends Application implements JeuConstructeur  {

    /**
     * Contient le stage qui sera modifié au long de la partie
     */
    private static Stage stage;

    /**
     * Permet de gérer la construction des joueurs humains et IA
     */
    private final IJoueurConstructeur joueurConstructeur;

    @Override
    public List<JoueurControlleur> initJoueur() {
        // root contient ce qui va être à l'intérieur de la scene
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/vuesGUI/InitPartie.fxml"));
            GridPane root = loader.load();
            Stage inner = new Stage();
            inner.setScene(new Scene(root));
            InitJoueursController controller = loader.getController();
            controller.setJoueurConstructeur((JoueurConstructeurGUI) this.joueurConstructeur);

            inner.showAndWait();
            return controller.getListejoueurs();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JeuConstructeurGUI(){
        this.joueurConstructeur = new JoueurConstructeurGUI();
    }


    /**
     * Est appellée dans le constructeur de la classe
     * Permet de lancer la fenêtre de l'application
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Witch Hunt");
        this.stage = stage;
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1920, 1080);
        this.stage.setScene(scene);
        stage.show();
        Jeu.getInstance().demarrer();
    }

    /**
     * Permet de changer scene de la fenêtre de jeu
     */
    public void changerFenetre(Parent root){
        this.stage.getScene().setRoot(root);
        this.stage.show();
    }
}
