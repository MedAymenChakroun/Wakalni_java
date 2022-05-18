/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import entities.user;
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
import javafx.scene.control.ComboBox;
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
public class ModUser_backendController implements Initializable {

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
    private Button back;
    @FXML
    private Button modifier;
    @FXML
    private PasswordField nv_pwd;
    @FXML
    private PasswordField nv_pwd1;
    @FXML
    private ComboBox<String> cmb_role;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService us = new userService();
        AccueilController bc = new AccueilController();
        user user = new user();
        user = us.afficher_user(bc.ID_mod);
        cmb_role.getItems().add("Admin");
        cmb_role.getItems().add("Client");
        cmb_role.getItems().add("Chef");
        cmb_role.getItems().add("Livreur");
        fld_nom.setText(user.getNom());
        fld_prenom.setText(user.getPrenom());
        fld_adr.setText(user.getAge());
        fld_num.setText(user.getTel());
        fld_email.setText(user.getEmail());
        String role = "";
        String role_num = user.getRole();
        System.out.print(user.getRole());

        if (role_num.equals("0")) {
            role = "Admin";
        } else if (role_num.equals("1")) {
            role = "Client";
        } else if (role_num.equals("2")) {
            role = "Chef/Resaurant";
        } else if (role_num.equals("3")) {
            role = "Livreur";
        }
        cmb_role.setValue(role);
        cmb_role.setPromptText(role);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("accueil.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {

        userService us = new userService();

        UserUtils uUtils = new UserUtils();
        AccueilController bc = new AccueilController();
        user user = new user();
        user = us.afficher_user(bc.ID_mod);
        String role = "";
        if (!us.checkUniqueEmail(fld_email.getText())) {
            uUtils.alert_Box("Email existant", "Cet Email existe dèja, veuillez utiliser un autre email");
        }
        if ("Admin".equals(cmb_role.getValue())) {
            role = "0";
        } else if ("Client".equals(cmb_role.getValue())) {
            role = "1";
        } else if ("Chef".equals(cmb_role.getValue())) {
            role = "2";
        } else if ("Livreur".equals(cmb_role.getValue())) {
            role = "3";
        } else if (!nv_pwd.getText().equals(nv_pwd1.getText())) {
            uUtils.alert_Box("Verification", "Veillez verifier que les mots de passe sont identiques");
        } else {

            if (!"".equals(nv_pwd.getText()) || nv_pwd.getText().equals(nv_pwd1.getText())) {
                user u = new user(bc.ID_mod, fld_nom.getText(), fld_prenom.getText(), fld_email.getText(), fld_num.getText(), role, fld_adr.getText(), nv_pwd.getText());
                us.modifier(u);
                us.update_roles();
            }else {
                user u = new user(bc.ID_mod, fld_nom.getText(), fld_prenom.getText(), fld_email.getText(), fld_num.getText(), role, fld_adr.getText(), user.getPassword());
                us.modifier2(u);
                us.update_roles();
            }
            
        }

    }

    @FXML
    private void log_out(ActionEvent event) throws IOException {
        userService us = new userService();
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
