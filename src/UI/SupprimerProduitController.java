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
import entities.produit;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author lacht
 */
public class SupprimerProduitController implements Initializable {

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
   @FXML
    private TableView<produit> table;
    @FXML
    private TableColumn<produit, String> NomColmn;
    @FXML
    private TableColumn<produit, String> typeColmn;
    @FXML
    private TableColumn<produit, Integer> cridColmn;
    @FXML
    private TableColumn<produit, Float> prixColmn;
    int index = -1;
    ObservableList<produit> oblist = FXCollections.observableArrayList() ; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        // TODO
    }    


      @FXML
    private void getSelected() {
      
          index = table.getSelectionModel().getSelectedIndex();
        if(index <=-1){
        return;
        }
        txtnom.setText(NomColmn.getCellData(index));
        txttype.setText(typeColmn.getCellData(index));
        txtcrid.setText(cridColmn.getCellData(index).toString());
        txtprix.setText(prixColmn.getCellData(index).toString());
       
        }
       void afficher() {
                table.getItems().clear();


        ProduitService ps = new ProduitService();
        List<produit> lp = ps.afficher();
        
        lp.forEach(e->oblist.add(e));
        NomColmn.setCellValueFactory(new PropertyValueFactory<produit, String>("nom"));
        typeColmn.setCellValueFactory(new PropertyValueFactory<produit, String>("type"));
        cridColmn.setCellValueFactory(new PropertyValueFactory<produit, Integer>("crid"));
        prixColmn.setCellValueFactory(new PropertyValueFactory<produit, Float>("prix"));
        
        table.setItems(oblist); 
    }
    @FXML
    private void Annuler(ActionEvent event) {
              try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLproduit.fxml"));
            Parent root=loader.load();
            FXMLproduitController pc=loader.getController();
            txtnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(SupprimerProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
      
      
        ProduitService ps1 = new ProduitService();
         ps1.supprimer(table.getSelectionModel().getSelectedItem().getProduitid());
         table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
         try {
//        txtnom.setText("");
//        txttype.setText("");
//        txtcrid.setText("");
//        txtprix.setText("");
//         JOptionPane.showMessageDialog(null, "produit supprimer");
//        
//        //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLproduit.fxml"));
            Parent root = loader.load(); 
            txtnom.getScene().setRoot(root);
        }catch (IOException ex) {
            Logger.getLogger(ModifierProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
       
    }
}
