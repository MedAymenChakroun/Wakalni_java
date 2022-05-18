/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import utilities.NotificationUtils;
import entities.Offre;
import services.OffreService;

/**
 * FXML Controller class
 *
 * @author lacht
 */
public class AjouterOffreController implements Initializable {

    @FXML
    private TextField txttype;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtcrid;
    @FXML
    private TextField txtprix;
    @FXML
    private DatePicker lsdate;
    @FXML
    private Button btnajou;
    @FXML
    private Button btannuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        NotificationUtils nu = new NotificationUtils();
                 try {
            //// SAVE ARTICLE IN DB
            String nom = txtnom.getText();
            String type = txttype.getText();
            Integer crid = Integer.parseInt(txtcrid.getText());
            Date dateE = Date.valueOf(lsdate.getValue());
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
            OffreService os = new OffreService();
            Offre l =new Offre(1, 10, nom, type, prix, dateE);
            os.ajouter(l);
            nu.Notification("felicitation", "offre ajouter");
            JOptionPane.showMessageDialog(null, "Offre ajouté");

            
            //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLOffre2.fxml"));
            Parent root = loader.load(); 
            txtnom.getScene().setRoot(root);}
        } catch (IOException ex) {
            Logger.getLogger(AjouterOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Annuler(ActionEvent event) {
              try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLOffre2.fxml"));
            Parent root=loader.load();
            FXMLOffre2Controller pc=loader.getController();
            txtnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjouterOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
