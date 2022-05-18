/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import entities.produit;
import services.ProduitService;
import utilities.datasource;

/**
 * FXML Controller class
 *
 * @author lacht
 */
public class ListProduitController implements Initializable {

    @FXML
    private ListView<String> listV;
    @FXML
    private Button btnajou1;
    @FXML
    private Button btn_retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Connection connectDB = datasource.getInstance().getCnx();

        String req = "SELECT nom,type,prix FROM produit";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet req1 = statement.executeQuery(req);
            while (req1.next()) {
                String nom = req1.getString("nom");
                String type = req1.getString("type");
                float prix = req1.getShort("prix");
                String listV1 = "   " + nom + "     " + type + "    " + prix;

                listV.getItems().add(listV1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ListeOffre(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListOffre.fxml"));
            Parent root = loader.load();
            ListOffreController loc = loader.getController();
            listV.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
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
