/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.userService;
import utilities.UserUtils;

/**
 * FXML Controller class
 *
 * @author malek guemri
 */
public class Reset_codeController implements Initializable {

    @FXML
    private Button annuler;
    private Button valider_code;
    @FXML
    private Label lbl_code;
    private TextField fld_code;
    private Label lbl_pwd;
    private PasswordField nv_pwd;
    private PasswordField nv_pwd1;
    private Button valider_pwd;
    @FXML
    private TextField fld_valider;
    @FXML
    private Button btn_valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_pwd.setVisible(false);
        nv_pwd.setVisible(false);
        nv_pwd1.setVisible(false);
        valider_pwd.setVisible(false);

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

    private void reset_pwd(ActionEvent event) {
        UserUtils uUtils = new UserUtils();
        userService us = new userService();
        Reset_mailController rc = new Reset_mailController();
        user user = us.getUserByMail(rc.mail);
        System.out.print(rc.code);
        if ("".equals(fld_code.getText()) || !fld_code.getText().equals(rc.code)) {
            uUtils.alert_Box("Verification", "Veillez verifier le code");
        } else {
            fld_code.setVisible(false);
            lbl_code.setVisible(false);
            valider_code.setVisible(false);
            lbl_pwd.setVisible(true);
            nv_pwd.setVisible(true);
            nv_pwd1.setVisible(true);
            valider_pwd.setVisible(true);

        }

    }

    private void valider(ActionEvent event) throws IOException {

        UserUtils uUtils = new UserUtils();
        userService us = new userService();
        Reset_mailController rc = new Reset_mailController();
        user user = us.getUserByMail(rc.mail);
        if ("".equals(nv_pwd.getText()) || "".equals(nv_pwd1.getText())) {
            uUtils.alert_Box("Verification", "Veillez verifier que les champs ne sont pas vide");
        } else if (!nv_pwd.getText().equals(nv_pwd1.getText())) {
            uUtils.alert_Box("Verification", "Veillez verifier que les mots de passe sont identiques");
        } else {

            user.setPassword(nv_pwd.getText());
            us.modifier(user);
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
    private void valider_compte(ActionEvent event) {
    }

}
