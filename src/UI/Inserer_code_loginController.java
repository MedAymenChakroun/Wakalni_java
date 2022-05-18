/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static UI.Reset_mailController.mail;
import entities.user;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.userService;
import utilities.NotificationUtils;
import utilities.UserUtils;

/**
 * FXML Controller class
 *
 * @author malek guemri
 */
public class Inserer_code_loginController implements Initializable {
    @FXML
    private TextField fld_valide;
    @FXML
    private Button btn_valid;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valider(ActionEvent event) throws IOException {
        NotificationUtils nu = new NotificationUtils();
        UserUtils uUtils = new UserUtils();
        userService us = new userService();
        LoginController sc = new LoginController();
        user user = us.getUserByMail(sc.mail);
        System.out.print(sc.code);
        if ("".equals(fld_valide.getText()) || !fld_valide.getText().equals(sc.code)) {
            uUtils.alert_Box("Verification", "Veillez verifier le code");
        } else {
            us.validerCompte(sc.mail);
            nu.Notification("Félicitation", "Bienvenue à Wakalni");
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("login.fxml"));
            Stage primaryStage = new Stage();
            Parent root = loader.load();
            Scene homescene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homescene);
            window.show();
        }
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("login.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
    
    
}
