/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import entities.Offre;
import entities.produit;
import services.OffreService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author lacht
 */
public class FXMLOffre2Controller implements Initializable {

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
    @FXML
    private Button btnajou;
    @FXML
    private Button btnMod;
    @FXML
    private Button btnSupp;
    @FXML
    private TextField txtrech;

    int index = -1;
        ObservableList<Offre> oblist = FXCollections.observableArrayList() ; 
    ObservableList<Offre> datalist = FXCollections.observableArrayList();  
    @FXML
    private Button btnajou1;
    @FXML
    private Button btn_retour;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
                OffreService os = new OffreService();
        List<Offre> lo = os.afficher();
        ObservableList<Offre> data=FXCollections.observableArrayList(lo);
    
        NomColmn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeColmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        cridColmn.setCellValueFactory(new PropertyValueFactory<>("crid"));
        prixColmn.setCellValueFactory(new PropertyValueFactory<>("prix"));
       
    table_offre.setItems(data);
    
    SortedList<Offre> sortedData=tableViewSearchFilter(data);
    table_offre.setItems(sortedData);  
    }    
    
    private SortedList<Offre> tableViewSearchFilter(ObservableList<Offre> olist){
          
             // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Offre> filteredData = new FilteredList<>(olist, b -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            txtrech.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(oo -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (oo.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    } else if (oo.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
//                    } else if (pp.getPrix().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches last name.

                    } else
                        return false; // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Offre> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(table_offre.comparatorProperty());
                 // 5. Add sorted (and filtered) data to the table. 
                 return sortedData;
   
 
    }    

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        
         try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterOffre.fxml"));
            Parent root=loader.load();
            AjouterOffreController aoc=loader.getController();
            table_offre.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLOffre2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Modiffier(ActionEvent event) {
         try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierOffre.fxml"));
            Parent root=loader.load();
            ModifierOffreController soc=loader.getController();
            table_offre.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLOffre2Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("SupprimerOffre.fxml"));
            Parent root=loader.load();
            SupprimerOffreController soc=loader.getController();
            table_offre.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLOffre2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void search_Formation(ActionEvent event) {
    }
    
    void afficher() {
                table_offre.getItems().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        OffreService os = new OffreService();
        List<Offre> lo = os.afficher();
        
        lo.forEach(e->oblist.add(e));
        NomColmn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeColmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        cridColmn.setCellValueFactory(new PropertyValueFactory<>("crid"));
        prixColmn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        dateclmn.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        table_offre.setItems(oblist); 

    }

    @FXML
    private void Produit(ActionEvent event){ 
                try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLproduit.fxml"));
            Parent root=loader.load();
            FXMLproduitController pc=loader.getController();
            table_offre.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLOffre2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("accueil.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
}
