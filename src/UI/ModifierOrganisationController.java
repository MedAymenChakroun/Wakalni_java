/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ModifierOrganisationController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_adresse;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_numero;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ComboBox<Integer> combo_id;
    
    OrganisationService os = new OrganisationService();
    Organisation o = new Organisation();
    @FXML
    private AnchorPane retour;
    @FXML
    private TextField id_left;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req="select organisationid from organisation";
            PreparedStatement pst = datasource.getInstance().getCnx()
                    .prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            ObservableList<Integer> id = null;
            List<Integer> list = new ArrayList<>();
            while(rs.next()){
                
                list.add(rs.getInt("organisationid"));
                
            }
            id = FXCollections
                    .observableArrayList(list);
            combo_id.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierOrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }


    @FXML
    private void modifier(ActionEvent event) {
        try {
            
        os.modifierOrganisation(new Organisation(combo_id.getSelectionModel().getSelectedItem(), tf_nom.getText(), tf_adresse.getText(), tf_email.getText(), Integer.parseInt(tf_numero.getText()),Integer.parseInt(id_left.getText())));       
        JOptionPane.showMessageDialog(null, "Organisation modifié");
        
        //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Main.fxml"));
            Parent root = loader.load(); 
            tf_nom.getScene().setRoot(root);
        }catch (IOException ex) {
            Logger.getLogger(ModifierOrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @FXML
    private void getId(MouseEvent event) {
         combo_id.setOnAction(e ->{
            String req="select nom,adresse,email,numero,leftoverid from organisation where organisationid=?";
            try {
                    PreparedStatement pst = datasource.getInstance().getCnx()
                    .prepareStatement(req);             
                    pst.setInt(1,(Integer)combo_id.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    tf_nom.setText(rs.getString("nom"));
                    tf_adresse.setText(rs.getString("adresse"));
                    tf_email.setText(rs.getString("email"));
                    tf_numero.setText(rs.getString("numero"));
                    id_left.setText(rs.getString("leftoverid"));
                 
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifierOrganisationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
   
    }

    @FXML
    private void retour(ActionEvent event) {
                try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root=loader.load();
            MainController aac=loader.getController();
            tf_nom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierOrganisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
}
