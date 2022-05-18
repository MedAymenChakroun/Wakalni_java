package UI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class EspaceReviewController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button Ecrire;

    @FXML
    private Button Afficher;

    @FXML
    void AfficherReview(ActionEvent event) throws IOException {
    
       Parent root = FXMLLoader.load(getClass().getResource("ListeReview.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }   
    @FXML
    void EcrireReview(ActionEvent event) throws IOException {
    Stage primaryStage= new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("ReviewUser.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
 
    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("accueil_user.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
