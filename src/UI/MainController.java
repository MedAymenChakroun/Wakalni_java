/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;


import utilities.datasource;
import services.OrganisationService;
import entities.Leftovers;
import entities.Organisation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class MainController implements Initializable {

    @FXML
    private ListView<Organisation> table_Organisation;
    ObservableList<Organisation> listM;
    @FXML
    private TextField tf_recherche;
    
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OrganisationService os = new OrganisationService();
        List<Organisation> lo = os.afficher();
        
        ObservableList<Organisation> data=FXCollections.observableArrayList(lo);
        
        data.remove("leftoverid");
           
        table_Organisation.setItems(data);
     

        //table_Organisation.getItems().add(os.afficher()) ;
       
    
    SortedList<Organisation> sortedData=tableViewSearchFilter(table_Organisation.getItems());//recherche 
    table_Organisation.setItems(sortedData);    
    
    
    }
    
    
    private SortedList<Organisation> tableViewSearchFilter(ObservableList<Organisation> olist){
          
             // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Organisation> filteredData = new FilteredList<>(olist, b -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            tf_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(org -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (org.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ||org.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1||org.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1||Integer.valueOf(org.getNumero()).toString().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                        return true; // Filter matches first name.
                    } else
                        return false; // Does not match.
                });
            });
            //3. Wrap the FilteredList in a SortedList.
           SortedList<Organisation> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            	  //Otherwise, sorting the TableView would have no effect.
          //sortedData.comparatorProperty().bind(table_Organisation.comparatorProperty());
                 // 5. Add sorted (and filtered) data to the table. 
                return sortedData;
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AddOrganisation.fxml"));
            Parent root=loader.load();
            AddOrganisationController aac=loader.getController();
            table_Organisation.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierOrganisation.fxml"));
            Parent root=loader.load();
            ModifierOrganisationController aac=loader.getController();
            table_Organisation.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerOrganisation(ActionEvent event) {
        Organisation O = new Organisation();
        O = table_Organisation.getSelectionModel().getSelectedItem();
        if (O == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir une organisation à supprimer");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer l'organisation ?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                OrganisationService OS= new OrganisationService();
                OS.supprimerOrganisation(O);
                JOptionPane.showMessageDialog(null, "Organisation supprimé");
                loadOrganisations();
            }

        }
    }
    
    public void loadOrganisations() {
        OrganisationService OS = new OrganisationService();
        ArrayList<Organisation> listeOrganisation = (ArrayList<Organisation>) OS.afficher();

        ObservableList observableList = FXCollections.observableArrayList(listeOrganisation);
        table_Organisation.setItems(observableList);

    }

    @FXML
    private void Leftover(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Leftovers.fxml"));
            Parent root=loader.load();
            LeftoversController aac=loader.getController();
            table_Organisation.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
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

