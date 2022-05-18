/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import utilities.datasource;
import services.LeftoversService;
import entities.Leftovers;
import entities.Organisation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ModifierLeftoversController implements Initializable {

    @FXML
    private TextField tf_sujet;
    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_quantite;
    @FXML
    private ComboBox<Integer> combo_id;
    @FXML
    private DatePicker dateexp;

    /**
     * Initializes the controller class.
     */
    Leftovers l = new Leftovers();
    LeftoversService ls = new LeftoversService();
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
            combo_id.setItems(id);
        } catch (SQLException ex) {
            Logger.getLogger(ModifierLeftoversController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void modifier(ActionEvent event) {
        try {
            
        ls.modifierLeftovers(new Leftovers(combo_id.getSelectionModel().getSelectedItem(), tf_sujet.getText(), tf_type.getText(), Integer.parseInt(tf_quantite.getText()),Date.valueOf(dateexp.getValue()),12));       
        JOptionPane.showMessageDialog(null, "Leftovers modifié");
        
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Leftovers.fxml"));
            Parent root = loader.load(); 
            tf_sujet.getScene().setRoot(root);
        }catch (IOException ex) {
            Logger.getLogger(ModifierLeftoversController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getId(MouseEvent event) {
        combo_id.setOnAction(e ->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
            String req="select sujet,type,quantite,dateexpiration,chefrestoid from leftovers where leftoverid=?";
            try {
                    PreparedStatement pst = datasource.getInstance().getCnx()
                    .prepareStatement(req);             
                    pst.setInt(1,(Integer)combo_id.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    tf_sujet.setText(rs.getString("sujet"));
                    tf_type.setText(rs.getString("type"));
                    tf_quantite.setText(rs.getString("quantite"));
                    dateexp.setValue(LocalDate.parse(rs.getDate("dateexpiration").toString(),formatter));
                 
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifierLeftoversController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void retour(ActionEvent event) {
          try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Leftovers.fxml"));
            Parent root=loader.load();
            LeftoversController aac=loader.getController();
            tf_sujet.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierLeftoversController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
