/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import utilities.sendSMS;
import utilities.datasource;
import services.OrganisationService;
import entities.Organisation;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AddOrganisationController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_adresse;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_numero;
    @FXML
    private ComboBox<Integer> id_left;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req="select leftoverid from leftovers";
            PreparedStatement pst = datasource.getInstance().getCnx()
                    .prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while(rs.next()){
                
                list.add(rs.getInt("leftoverid"));
                
            }
            id = FXCollections
                    .observableArrayList(list);
            id_left.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierOrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterOrganisation(ActionEvent event) {
        
        try {
            //// SAVE ORGANISATION IN DB
            String resNom = tf_nom.getText();
            String resAdresse = tf_adresse.getText();
            String resEmail = tf_email.getText();
            String resNumero = tf_numero.getText();
            String resIdlef = id_left.getSelectionModel().getSelectedItem().toString();

            if((resNom.equals(""))||(resAdresse.equals(""))||(resEmail.equals(""))||(resNumero.equals(""))||(resIdlef.equals(""))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez remplir les champs vides");
            alert.show();
        }
            else
            {
            
            Organisation o = new Organisation(20, resNom, resAdresse,resEmail,Integer.parseInt(resNumero),Integer.parseInt(resIdlef));

            OrganisationService pcd = new OrganisationService();
            pcd.ajouterOrganisation(o);
            JOptionPane.showMessageDialog(null, "Organisation ajouté");
           sendSMS sm = new sendSMS();
           sm.sendSMS(o);
            
            //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Main.fxml"));
            Parent root = loader.load(); 
            tf_nom.getScene().setRoot(root);}
        } catch (IOException ex) {
            Logger.getLogger(AddOrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root=loader.load();
            MainController aac=loader.getController();
            tf_nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AddOrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
