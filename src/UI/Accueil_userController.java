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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.userService;
import utilities.UserUtils;

/**
 * FXML Controller class
 *
 * @author malek guemri
 */
public class Accueil_userController implements Initializable {

    @FXML
    private Button btn_leftovers;
    @FXML
    private Button btn_produit;
    @FXML
    private ImageView Pr;
    @FXML
    private Button btn_panier;
    @FXML
    private Button btn_reviews;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_reclamation;
    @FXML
    private TextField fld_nom;
    @FXML
    private TextField fld_prenom;
    @FXML
    private TextField fld_email;
    @FXML
    private TextField fld_role;
    @FXML
    private TextField fld_num;
    @FXML
    private TextField fld_adr;
    @FXML
    private Button reset_pwd;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService us = new userService();
        int id = us.current_user();
        user user = new user();
        user = us.afficher_user(id);
        System.out.print(user);
        fld_nom.setText(user.getNom());
        fld_prenom.setText(user.getPrenom());
        fld_adr.setText(user.getAge());
        fld_num.setText(user.getTel());
        fld_email.setText(user.getEmail());
        String role = "";
        String role_num = user.getRole();

        if (role_num.equals("1")) {
            role = "Client";
        } else if (role_num.equals("2")) {
            role = "Chef/Resaurant";
        } else if (role_num.equals("3")) {
            role = "Livreur";
        }
        fld_role.setText(role);
    }

    @FXML
    private void leftovers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("organisationclient.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void produit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("ListProduit.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void panier(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("interfacepanier.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void reviews(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("EspaceReview.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void user(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
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

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("EspaceReclamation.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void reset_pwd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("reset_pwd.fxml"));
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
        UserUtils uu = new UserUtils();
        int id = us.current_user();
        user user = new user();
        user = us.afficher_user(id);
        String role = "";
        if (!us.checkUniqueEmail(fld_email.getText())) {
            uu.alert_Box("Email existant", "Cet Email existe dèja, veuillez utiliser un autre email");
        }
        if ("Client".equals(fld_role.getText())) {
            role = "1";
        } else if ("Chef".equals(fld_role.getText())) {
            role = "2";
        } else if ("Livreur".equals(fld_role.getText())) {
            role = "3";
        }
        user u = new user(id, fld_nom.getText(), fld_prenom.getText(), fld_email.getText(), fld_num.getText(), role, fld_adr.getText(), user.getPassword());
        us.modifier(u);
        us.update_roles();
    }

}
