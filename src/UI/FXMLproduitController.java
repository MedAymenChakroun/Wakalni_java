/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;



import com.mysql.jdbc.MySQLConnection;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import entities.produit;
import services.ProduitService;
import utilities.datasource;


public class FXMLproduitController implements Initializable{
    private Connection conn= datasource.getInstance().getCnx();
  private Statement ste;
  private PreparedStatement pste;


    private TextField txtnom;
  
    private TextField txttype;

    private TextField txtcrid;

    private TextField txtprix;

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

    @FXML
    private Button btnajou;

    @FXML
    private Button btnMod;

    @FXML
    private Button btnSupp;



     int index = -1;
    @FXML
    private TextField txtrech;
    ObservableList<produit> oblist = FXCollections.observableArrayList() ; 
    ObservableList<produit> datalist = FXCollections.observableArrayList();  
    @FXML
    private Button btnajou1;
    @FXML
    private Button btn_retour;

     
    @FXML
    void Ajouter(ActionEvent event) {
//        String nom,type;
//      int crid;
//      float prix;
//            nom = txtnom.getText();
//            type = txttype.getText();
//            crid = Integer.parseInt(txtcrid.getText());
//            prix = Float.parseFloat(txtprix.getText());
//            
//        ProduitService ps = new ProduitService();
//        produit p1 =  new produit(nom, type, crid, prix);
//        ps.ajouter(p1);
//        table.getItems().clear();
//        afficher();
//        
//       
  try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterProduit.fxml"));
            Parent root=loader.load();
            AjouterProduitController apc=loader.getController();
            table.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Modiffier(ActionEvent event) {
     try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierProduit.fxml"));
            Parent root=loader.load();
            ModifierProduitController mpc=loader.getController();
            table.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void retour(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("accueil.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();

    }

    @FXML
    void Supprimer(ActionEvent event) {
      try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("SupprimerProduit.fxml"));
            Parent root=loader.load();
            SupprimerProduitController spc=loader.getController();
            table.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
       
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
    
    
//     void search_Formation() {
//       // produit p = new produit();
//        NomColmn.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        typeColmn.setCellValueFactory(new PropertyValueFactory<>("type"));
//        cridColmn.setCellValueFactory(new PropertyValueFactory<>("crid"));
//        prixColmn.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        //datalist = mysqlconncet.getDatasource();
//        table.setItems(oblist);
//        // liste filtre                  // esm list mte3na                    
//        FilteredList<Produit> filteredData = new FilteredList<>(oblist, b -> true);
//
//        txtrech.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate((produit pp) -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (pp.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches first name.
//                } else if (pp.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches last name.
//               } else {
//                    return false; // Does not match.
//                }
//            });
//        });
//        // lehne sna3t tableau jdid + affichage
//        SortedList<Produit> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(table.comparatorProperty());
//        table.setItems(sortedData);
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficher();
       //search_Formation();
         ProduitService ps = new ProduitService();
        List<produit> lp = ps.afficher();
        ObservableList<produit> data=FXCollections.observableArrayList(lp);
    
        NomColmn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeColmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        cridColmn.setCellValueFactory(new PropertyValueFactory<>("crid"));
        prixColmn.setCellValueFactory(new PropertyValueFactory<>("prix"));
       
    table.setItems(data);
    
    SortedList<produit> sortedData=tableViewSearchFilter(data);
    table.setItems(sortedData);  
    }    
    
    private SortedList<produit> tableViewSearchFilter(ObservableList<produit> olist){
          
             // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<produit> filteredData = new FilteredList<>(olist, b -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            txtrech.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(pp -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (pp.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    } else if (pp.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
//                    } else if (pp.getPrix().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches last name.

                    } else
                        return false; // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<produit> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(table.comparatorProperty());
                 // 5. Add sorted (and filtered) data to the table. 
                 return sortedData;
   
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

//    @FXML
//    private void recherche(ActionEvent event) {
//       
//    }

    @FXML
    private void search_Formation(ActionEvent event) {
    }

    @FXML
    private void Offre(ActionEvent event) {
         try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLOffre2.fxml"));
            Parent root=loader.load();
            FXMLOffre2Controller oc=loader.getController();
            table.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      

    }



