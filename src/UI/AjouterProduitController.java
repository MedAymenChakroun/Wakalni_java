/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import entities.produit;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author lacht
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField txttype;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtcrid;
    @FXML
    private TextField txtprix;
    @FXML
    private Button btnajou;
    @FXML
    private Button btannuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
          
        try {
            //// SAVE ORGANISATION IN DB
           String  nom = txtnom.getText();
           String type = txttype.getText();
           Integer crid = Integer.parseInt(txtcrid.getText());
           Float prix = Float.parseFloat(txtprix.getText());

            if((nom.equals(""))||(type.equals(""))||(crid.equals(""))||(prix.equals(""))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez remplir les champs vides");
            alert.show();
        }
            else
            {
        
        ProduitService ps = new ProduitService();
        produit p1 =  new produit(nom, type, crid, prix);
        ps.ajouter(p1);
            
          //  produit p = new produit(nom, type, crid, prix));

           // ProduitService ps = new ProduitService();
           // ps.ajouter(p);
            JOptionPane.showMessageDialog(null, "Produit ajouté");
            //sendSMS sm = new sendSMS();
            //sm.sendSMS(o);
            
            //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLproduit.fxml"));
            Parent root = loader.load(); 
            txtnom.getScene().setRoot(root);}
        } catch (IOException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Annuler(ActionEvent event) {
           try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLproduit.fxml"));
            Parent root=loader.load();
            FXMLproduitController pc=loader.getController();
            txtnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
