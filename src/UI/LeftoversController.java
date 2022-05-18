/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import services.LeftoversService;
import entities.Leftovers;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class LeftoversController implements Initializable {

    @FXML
    private TableView<Leftovers> table_Leftovers;
    @FXML
    private TableColumn<Leftovers, String> col_sujet;
    @FXML
    private TableColumn<Leftovers, String> col_type;
    @FXML
    private TableColumn<Leftovers, Integer> col_quantite;
    @FXML
    private TableColumn<Leftovers, String> col_dateexp;
    @FXML
    private TextField tf_recherche;
    ObservableList<Leftovers> listM;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LeftoversService ls = new LeftoversService();
        List<Leftovers> ll = ls.afficher();
        ObservableList<Leftovers> data=FXCollections.observableArrayList(ll);
    col_sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
    col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
    col_dateexp.setCellValueFactory(new PropertyValueFactory<>("dateexpiration"));
       
    table_Leftovers.setItems(data);
    
    SortedList<Leftovers> sortedData=tableViewSearchFilter(data);
    table_Leftovers.setItems(sortedData);  
    }    
    
    private SortedList<Leftovers> tableViewSearchFilter(ObservableList<Leftovers> olist){
          
             // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Leftovers> filteredData = new FilteredList<>(olist, b -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            tf_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(lef -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (lef.getSujet().toLowerCase().indexOf(lowerCaseFilter) != -1||lef.getType().toLowerCase().indexOf(lowerCaseFilter) != -1||Integer.valueOf(lef.getQuantite()).toString().toLowerCase().indexOf(lowerCaseFilter)!=-1||lef.getDateexpiration().toString().toLowerCase().indexOf(lowerCaseFilter)!=-1 ) {
                        return true; // Filter matches first name.
                    } else
                        return false; // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Leftovers> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(table_Leftovers.comparatorProperty());
                 // 5. Add sorted (and filtered) data to the table. 
                 return sortedData;
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("AddLeftovers.fxml"));
            Parent root=loader.load();
            AddLeftoversController aac=loader.getController();
            table_Leftovers.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierLeftovers.fxml"));
            Parent root=loader.load();
            ModifierLeftoversController aac=loader.getController();
            table_Leftovers.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerLeftovers(ActionEvent event) {
        Leftovers L = new Leftovers();
        L = table_Leftovers.getSelectionModel().getSelectedItem();
        if (L == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez Choisir un leftover à supprimer");
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("vous êtes sûr de supprimer leftover ?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                LeftoversService LS= new LeftoversService();
                LS.supprimerLeftovers(L);
                JOptionPane.showMessageDialog(null, "Leftover supprimé");
                loadLeftovers();
            }

        }
    }
    public void loadLeftovers() {
        LeftoversService LS = new LeftoversService();
        ArrayList<Leftovers> listeLeftovers = (ArrayList<Leftovers>) LS.afficher();

        ObservableList observableList = FXCollections.observableArrayList(listeLeftovers);
        table_Leftovers.setItems(observableList);

    }

    @FXML
    private void Organisation(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Main.fxml"));
            Parent root=loader.load();
            MainController aac=loader.getController();
            table_Leftovers.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Stats(ActionEvent event) {
        try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXBarChart.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Statistiques Leftovers");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Window primaryStage = null;
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        FXBarChartController controller = loader.getController();
        LeftoversService as = new LeftoversService();
        List<Leftovers> la = as.afficher();
        ObservableList<Leftovers> data=FXCollections.observableArrayList(la);
    
        controller.setLeftoversData(data);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("Main.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
    
}
