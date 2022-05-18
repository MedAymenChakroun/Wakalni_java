/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static UI.Sign_upController.code;
import static UI.Sign_upController.mail;
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
import services.SessionManager;
import services.userService;
import utilities.MailUtils;
import utilities.UserUtils;

/**
 * FXML Controller class
 *
 * @author malek guemri
 */
public class LoginController implements Initializable {

    @FXML
    private Button btn_inscri;
    @FXML
    private TextField username_login;
    @FXML
    private TextField password_login;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_reset;
    public static String mail;
    public static String code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void swap_inscri(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/sign_up.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void forget_pwd(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Reset_mail.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    private void login_user(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/profile.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void log_in(ActionEvent event) throws IOException, SQLException {

        UserUtils uUtils = new UserUtils();
        userService sU = new userService();
        String username = username_login.getText();
        String password = password_login.getText();

        int role = sU.verifierData(username, password);
        System.out.print(sU.getbyusernameandpassword(username, password));
        if (sU.getbyusernameandpassword(username, password)==false) {
            uUtils.alert_Box("Verification", "Veillez verifier votre mot de passe");
        } else if (sU.getbyusernameandpassword(username, password)) {
            if (role == -1) {
                uUtils.alert_Box("Verification", "Veillez verifier votre email");
            } else if (!sU.verifierCompteValide(username, password)) {
                code_verif();
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("inserer_code_login.fxml"));
                Stage primaryStage = new Stage();
                Parent root = loader.load();
                Scene homescene = new Scene(root);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(homescene);
                window.show();

            } else if (sU.verifierCompteValide(username, password)) {

                SessionManager.setId(sU.getiduser(username));
                if (role == 0) {
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("accueil.fxml"));
                    Stage primaryStage = new Stage();
                    Parent root = loader.load();
                    Scene homescene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();

                } else if (role == 1) {
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("accueil_user.fxml"));
                    Stage primaryStage = new Stage();
                    Parent root = loader.load();
                    Scene homescene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();

                } else if (role == 2) {
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("accueil_user.fxml"));
                    Stage primaryStage = new Stage();
                    Parent root = loader.load();
                    Scene homescene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();

                } else if (role == 3) {
                    FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("accueil_user.fxml"));
                    Stage primaryStage = new Stage();
                    Parent root = loader.load();
                    Scene homescene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                }
            }
        }
    }

    private void code_verif() throws SQLException, IOException {

        userService us = new userService();
        MailUtils mu = new MailUtils();
        UserUtils uUtils = new UserUtils();
        mail = username_login.getText();

        code = us.Random6Digits();
        mu.envoyerMail(username_login.getText(), "Valider votre compte Wakalni", "Bonjour," + username_login.getText() + " \n " + code + " est votre code de vérification");

    }

}
