/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import utilities.MailUtils;
import utilities.UserUtils;

/**
 * FXML Controller class
 *
 * @author malek guemri
 */
public class Reset_mailController implements Initializable {
    @FXML
    private Button annuler;
    @FXML
    private Button valider;
    @FXML
    private TextField email;
    public static String code ;
    public static String mail = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void valider(ActionEvent event) throws SQLException, IOException {
        
        userService us = new userService();
        MailUtils mu = new MailUtils();
        UserUtils uUtils = new UserUtils();
        mail=email.getText();
        if(us.check_email(email.getText())==false){
            uUtils.alert_Box("Email Introuvable", "Veillez verifier l'Email");
        }
        else{
            code = us.Random6Digits();
            mu.envoyerMail(email.getText(), "Réinitialiser votre mot de passe", "Bonjour,"+email.getText()+" \n "
                    +code+ " est votre code de réinitialisation");
            FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("Reset_Code.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
            
        }
    }
    
}
