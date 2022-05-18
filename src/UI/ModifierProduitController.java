/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import entities.Offre;
import entities.produit;
import services.OffreService;
import services.ProduitService;
import utilities.datasource;

/**
 * FXML Controller class
 *
 * @author lacht
 */
public class ModifierProduitController implements Initializable {

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
    ProduitService ps = new ProduitService();
    produit p = new produit();
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
//         try {
//            String req="select crid from produit";
//            PreparedStatement pst = Datasource.getInstance().getCnx().prepareStatement(req);
//            ResultSet rs=pst.executeQuery();
//            ObservableList<Integer> id = null;
//            List<Integer> list = new ArrayList<>();
//            while(rs.next()){
//                
//                list.add(rs.getInt("crid"));
//                
//            }
//            id = FXCollections
//                    .observableArrayList(list);
//            combo_id.setItems(id);
//        } catch (SQLException ex) {
//            Logger.getLogger(ModifierProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
            
    }  
     @FXML
    private void Modifier(ActionEvent event) {
         try {
                produit  p1 =table.getSelectionModel().getSelectedItem();
        String nom,type;
      int crid;
      float prix;
      //Date datefin;
            nom = txtnom.getText();
            type = txttype.getText();
            crid = Integer.parseInt(txtcrid.getText());
            prix = Float.parseFloat(txtprix.getText());
           // Date = Date.valueOf(lsdate.getValue());
            //datee// 
            p1.setNom(nom);
            p1.setType(type);
            p1.setPrix(prix);
            //date
            ProduitService os = new ProduitService();
            os.modifier(p1.getProduitid(), p1);
        
      
        //ps.modifier(new produit(combo_id.getSelectionModel().getSelectedItem(), txtnom.getText(), txttype.getText(),Integer.parseInt(txtcrid.getText()) Float.parseFloat(txtprix.getText())));       
        JOptionPane.showMessageDialog(null, "Produit modifié");
        
        //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("FXMLproduit.fxml"));
            Parent root = loader.load(); 
            txtnom.getScene().setRoot(root);
        }catch (IOException ex) {
            Logger.getLogger(ModifierProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(ModifierProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


   
    
}
