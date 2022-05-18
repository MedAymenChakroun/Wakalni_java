package UI;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import entities.reclamation;
import services.ReclamationService;
import entities.reponse;

public class ListeReclamationsController {
        private Stage stage;
    private Scene scene;
    private Parent root;
    
    int index= -1;
    ObservableList<reclamation> oblist = FXCollections.observableArrayList();
    @FXML
    private Button Refresh;
     @FXML
    private Button Supprimer;
    
    @FXML
     private TableView<reclamation> table;
  
    private TableColumn<reclamation, String> col_sujet;

    private TableColumn<reclamation, String> col_contenu;

    @FXML
    private TableColumn<reclamation, Integer> col_nomclient;

    private TableColumn<reclamation, String> col_etat;
    @FXML
    private TableColumn<?, ?> col_nomproduit;
    @FXML
    private TableColumn<?, ?> col_note;
    @FXML
    private TableColumn<?, ?> col_comm;
    @FXML
    private Button Refresh1;

    

    @FXML
    void RefreshAction(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        System.out.print(rs.afficher());
        List<reclamation> r = rs.afficher();
        r.forEach(e->oblist.add(e));
        r.forEach(e -> System.out.println("res =>"+e));        
        col_sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        col_nomclient.setCellValueFactory(new PropertyValueFactory<>("nomclient"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        table.setItems(oblist);
      /*table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
         table.refresh();
        System.out.println("TABLE ===> "+table);*/
                                   
    }
      @FXML 
    void Retourner(ActionEvent event) throws IOException{
    Stage primaryStage= new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("EspaceReclamation.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    
    }
     @FXML
    private void SupprimerAction(ActionEvent event) {
     
      reclamation r=  table.getSelectionModel().getSelectedItem();
      ReclamationService rs = new ReclamationService();
      rs.supprimer(r.getReclamationid());
      table.getItems().clear();
      Alert alert = new Alert(AlertType.INFORMATION);     
      alert.setTitle("Delete");
      alert.setHeaderText(null);
      alert.setContentText("suppression avec succes!");
      alert.showAndWait();

    }
}

