/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import entities.Offre;
import services.OffreService;

/**
 * FXML Controller class
 *
 * @author lacht
 */
public class SupprimerOffreController implements Initializable {

    @FXML
    private Button btnajou;
    @FXML
    private Button btannuler;
    @FXML
    private TableView<Offre> table_offre;
    @FXML
    private TableColumn<Offre, String> NomColmn;
    @FXML
    private TableColumn<Offre, String> typeColmn;
    @FXML
    private TableColumn<Offre, Integer> cridColmn;
    @FXML
    private TableColumn<Offre, Float> prixColmn;
    @FXML
    private TableColumn<Offre, String> dateclmn;
    
    int index = -1;
    ObservableList<Offre> oblist = FXCollections.observableArrayList() ; 
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtcrid;
    @FXML
    private TextField txtprix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        
        // TODO
    }    

    @FXML
    private void Supprimer(ActionEvent event) {
       OffreService os1 = new OffreService();
         os1.supprimer(table_offre.getSelectionModel().getSelectedItem().getOffreid());
         table_offre.getItems().removeAll(table_offre.getSelectionModel().getSelectedItem()); 
         try {
//        
//        //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLOffre2.fxml"));
            Parent root = loader.load(); 
            txtnom.getScene().setRoot(root);
        }catch (IOException ex) {
            Logger.getLogger(SupprimerOffreController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SupprimerOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
             index = table_offre.getSelectionModel().getSelectedIndex();
        if(index <=-1){
        return;
        }
        txtnom.setText(NomColmn.getCellData(index));
        txttype.setText(typeColmn.getCellData(index));
        txtcrid.setText(cridColmn.getCellData(index).toString());
        txtprix.setText(prixColmn.getCellData(index).toString());
       
    }
           void afficher() {

          table_offre.getItems().clear();


        OffreService ps = new OffreService();
        List<Offre> lp = ps.afficher();
        
        lp.forEach(e->oblist.add(e));
       NomColmn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeColmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        cridColmn.setCellValueFactory(new PropertyValueFactory<>("crid"));
        prixColmn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        dateclmn.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        table_offre.setItems(oblist);  
           }
}
