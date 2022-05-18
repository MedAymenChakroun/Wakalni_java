/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import services.LeftoversService;
import services.OrganisationService;
import entities.Leftovers;
import entities.Organisation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AddLeftoversController implements Initializable {

    @FXML
    private TextField tf_sujet;
    @FXML
    private TextField tf_type;

    @FXML
    private DatePicker dateexp;
    @FXML
    private TextField tf_quantite;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterLeftovers(ActionEvent event) {
        
        try {
            AccueilController ac = new AccueilController();
            //// SAVE ARTICLE IN DB
            String resSujet = tf_sujet.getText();
            String resType = tf_type.getText();
            String resQuantite = tf_quantite.getText();
            Date dateE = Date.valueOf(dateexp.getValue());
            
            String resIdchef = String.valueOf(ac.ID_mod);

            if((resSujet.equals(""))||(resType.equals(""))||(resQuantite.equals(""))||(resIdchef.equals(""))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez remplir les champs vides");
            alert.show();
        }
            else
            {
            
            Leftovers l = new Leftovers(20, resSujet, resType,Integer.parseInt(resQuantite),dateE,Integer.parseInt(resIdchef));

            LeftoversService pcd = new LeftoversService();
            pcd.ajouterLeftover(l);
            JOptionPane.showMessageDialog(null, "Leftovers ajouté");

            
            //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Leftovers.fxml"));
            Parent root = loader.load(); 
            tf_sujet.getScene().setRoot(root);}
        } catch (IOException ex) {
            Logger.getLogger(AddLeftoversController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Leftovers.fxml"));
            Parent root=loader.load();
            LeftoversController aac=loader.getController();
            tf_sujet.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddLeftoversController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
