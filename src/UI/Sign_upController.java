/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static UI.Reset_mailController.code;
import static UI.Reset_mailController.mail;
import entities.user;
import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.userService;
import utilities.MailUtils;
import utilities.UserUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author malek guemri
 */
public class Sign_upController implements Initializable {

    @FXML
    private Button btn_login;
    @FXML
    private Button btn_inscri;
    @FXML
    private Button btn_quit;
    @FXML
    private TextField fld_nom;
    @FXML
    private TextField fld_prenom;
    @FXML
    private TextField fld_email;
    @FXML
    private TextField fld_num;
    @FXML
    private TextField fld_adr;
    @FXML
    private PasswordField fld_pwd;
    @FXML
    private PasswordField fld_pwd2;
    @FXML
    private ComboBox<String> cmb_role;
    public static String code;
    public static String mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_role.getItems().add("Client");
        cmb_role.getItems().add("Chef");
        cmb_role.getItems().add("Livreur");
    }

    @FXML
    private void swap_login(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/login.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    private void code_verif() throws SQLException, IOException {

        userService us = new userService();
        MailUtils mu = new MailUtils();
        UserUtils uUtils = new UserUtils();
        mail = fld_email.getText();

        code = us.Random6Digits();
        mu.envoyerMail(fld_email.getText(), "Valider votre compte Wakalni", "Bonjour," + fld_email.getText() + " \n " + code + " est votre code de vérification");

    }

    @FXML
    private void inscrire(ActionEvent event) throws SQLException, IOException {
        userService uService = new userService();
        UserUtils uUtiles = new UserUtils();
        MailUtils mu = new MailUtils();
        String nom = fld_nom.getText();
        String prenom = fld_prenom.getText();
        String mail = fld_email.getText();
        String password = fld_pwd.getText();
        String cpassword = fld_pwd2.getText();
        String adresse = fld_adr.getText();
        String tel = fld_num.getText();
        String role = "";
        if(uService.checkUniqueEmail(mail)){
            uUtiles.alert_Box("Email existant", "Cet Email existe dèja, veuillez utiliser un autre email");
        }
        if (cmb_role.getValue() == "Client") {
            role = "1";
        } else if (cmb_role.getValue() == "Chef") {
            role = "2";
        } else if (cmb_role.getValue() == "Livreur") {
            role = "3";
        }

        //controle de saisie
        if (nom.isEmpty()) {
            uUtiles.alert_Box("Verifier votre nom", "Votre nom ne doit pas être vide");
        } else if (!uUtiles.testEmail(mail)) {
            uUtiles.alert_Box("Verifier votre mail", "veillez saisir une adresse mail valide");
        } else if (!password.equals(cpassword)) {
            uUtiles.alert_Box("Verifier mot de passe", "Veillez verifier votre mot de passe ");
        } else {
            user u = new user(nom, prenom, mail, tel, role, adresse, password);
            mu.envoyerMail(u.getEmail(), "Welcome to wakalni", "Bonjour," + u.getNom()
                    + "");
            uService.ajouter2(u);
            uService.update_roles();
            uUtiles.information_Box("Compte créer avec succès", "Vous venez de recevoir un e-mail de confirmation");
            code_verif();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/inserer_code.fxml"));
            Stage primaryStage = new Stage();
            Parent root = loader.load();
            Scene homescene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homescene);
            window.show();

        }
    }

}
