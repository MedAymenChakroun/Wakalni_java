/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import entities.reclamation;
import services.ReclamationService;
import entities.reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ReponseService;
import utilities.Javamail;
import javax.mail.MessagingException;
import org.controlsfx.control.Rating;
import services.ReviewService;

public class ReclamationUserController {
        private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TitledPane Reclamation;
    @FXML
    private Button Envoyer;

    @FXML
    private TextArea Contenu;

    @FXML
    private TextField Sujet;

    @FXML
    void EnvoyerReclamation(ActionEvent event) throws MessagingException {
        String Suj = Sujet.getText();
        String Cont = Contenu.getText();
        ReviewService rc = new ReviewService();
        ReclamationService cs = new ReclamationService();
        reclamation c1 = new reclamation(Suj, Cont, rc.getclientidtoken(), 97,"En cours");
        cs.ajouter(c1);
       // ReponseService rs = new ReponseService();
       // cs.update(c1.getReclamationid());
    
       //c1.setReclamationid(cs.getid(c1.getSujet(), c1.getContenu()));
       //reponse r1 = new reponse(c1.getReclamationid(), "En cours");
       //rs.ajouter(r1);
        Javamail.sendMailcc("ahmed.rahal@esprit.tn");
    }

    @FXML
    void Retourner(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("EspaceReclamation.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
